package com.dev.kgt.regstudent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Kyle on 30/10/2016.
 *
 * Prompts the user to accept the photo taken
 * or retry.
 */

public class PhotoCheck  extends AppCompatActivity{

    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_check);

        Intent intent = getIntent();
        bmp = (Bitmap)intent.getParcelableExtra("bmp");

        ImageView imv = (ImageView)findViewById(R.id.imageView);
        imv.setImageBitmap(bmp);
        //if the Accept button is pressed
        Button btnAccept = (Button)findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //pass th image back to the mainActivity
                Intent returnImg = new Intent(PhotoCheck.this,MainActivity.class);
                returnImg.putExtra("bmp",bmp);
                startActivityForResult(returnImg,0);
            }
        });

        //if the retry button is pressed
        Button btnRetry = (Button)findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                captureRetry(view);
            }
        });
    }
    //re-capture the picture
    public void captureRetry(View view){
        Intent camInt = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(camInt,0);

    }
    //show the recaptured picture on the screen
    public void onActivityResult(int reqCode,int resultCode,Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            bmp = (Bitmap) extra.get("data");

            ImageView imv = (ImageView) findViewById(R.id.imageView);
            imv.setImageBitmap(bmp);
        }
    }




}
