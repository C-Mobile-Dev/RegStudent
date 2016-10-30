package com.dev.kgt.regstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //take a picture
    public void capturePic(View view){
        Intent camInt = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(camInt,0);
    }
}
