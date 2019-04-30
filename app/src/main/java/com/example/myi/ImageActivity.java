package com.example.myi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ImageActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    RecyclerView rv;
    String myurl="https://pixabay.com/api/?key=11580613-4d5bfbdc5f1492d1b0dec0879&amp;q=";
    String myet;
    String URL_String;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        rv=findViewById(R.id.rv);
        Bundle bundle=new Bundle();
        myet=getIntent().getStringExtra("key");
        Log.i("resultInputData",myet);
        bundle.putString("key",myet);
        URL_String=myurl+myet;
        getSupportLoaderManager().initLoader(1,bundle,this);
        if (getSupportLoaderManager().getLoader(1)!=null) {

            getSupportLoaderManager().initLoader(1, null, this);
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
       /* return new ImageLoader(this,
                myurl+bundle.getString("key"));*/
       return new AsyncTaskLoader<String>(this) {
           @Nullable
           @Override
           public String loadInBackground() {
               try {
                   URL u=new URL(URL_String);
                   Log.i("url",u.toString());
                   HttpsURLConnection connection= (HttpsURLConnection) u.openConnection();
                   connection.setRequestMethod("GET");
                   connection.connect();
                   InputStream is = connection.getInputStream();
                   BufferedReader br=new BufferedReader(new InputStreamReader(is));
                   String line="";
                   StringBuilder stringBuilder=new StringBuilder();
                   while ((line=br.readLine())!=null)
                   {
                       stringBuilder.append(line+"\n");
                   }
                   return   stringBuilder.toString();
               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }

               return null;
           }

           @Override
           protected void onStartLoading() {
               super.onStartLoading();
               forceLoad();
           }
       };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        ArrayList<ImageModel> list = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray array=jsonObject.getJSONArray("hits");
            for (int i = 0; i < jsonObject.length(); i++) {
              JSONObject object=array.getJSONObject(i);
              String image=object.getString("previewURL");
             ImageModel imageModel=new ImageModel(image);
             list.add(imageModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rv.setAdapter(new ImageAdapter(this,list));
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

        @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
