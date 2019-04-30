package com.example.myi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.edittext);
    }

    public void Submit(View view) {
        String string=et.getText().toString();
        if(et.getText().toString().length()>0){
            Intent intent=new Intent(this,ImageActivity.class);
            intent.putExtra("key",string);
            Log.i("inpuData",string);
            startActivity(intent);
        }else {
            Toast.makeText(this, "please enter any name", Toast.LENGTH_SHORT).show();
        }
    }
}
