package com.dev.kgt.regstudent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Bitmap retBmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getInt = getIntent();
        retBmp = (Bitmap)getInt.getParcelableExtra("bmp");

        if(retBmp != null){
            ImageView proPic = (ImageView)findViewById(R.id.imageTaken);
            proPic.setImageBitmap(retBmp);
        }else{
            Toast.makeText(this,"Please Take a picture",Toast.LENGTH_SHORT).show();
        }
    }

    //take a picture
    public void capturePic(View view){
        Intent camInt = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(camInt,0);

    }


    public void onActivityResult(int reqCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            Bitmap bmp = (Bitmap)extra.get("data");

            //if the bit map is not empty
            if(bmp != null){
                //open the accept or reject photo
                Intent m = new Intent(MainActivity.this,PhotoCheck.class);
                m.putExtra("bmp",bmp);
                startActivityForResult(m,0);
            }else{
                Toast.makeText(this, "The bit map has not data",Toast.LENGTH_LONG).show();
                ImageView imv = (ImageView)findViewById(R.id.imageTaken);
                imv.setImageBitmap(bmp);
            }

        }
    }
}
