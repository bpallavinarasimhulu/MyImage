package com.example.myi;



import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoader extends AsyncTaskLoader<String> {
    String myurl;
    public ImageLoader(ImageActivity imageActivity, String key) {
        super(imageActivity);
        myurl=key;

    }

    @Override
    public String loadInBackground() {
        try {
            URL u=new URL(myurl);
            HttpsURLConnection connection= (HttpsURLConnection) u.openConnection();
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
}