package com.dev.kgt.regstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Kyle on 30/10/2016.
 *
 */

public class Summary extends AppCompatActivity{

    private Bitmap bmp;
    private String name,stdNum,course;
    private String fileName = "bmp.PNG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        Bundle getData = getIntent().getExtras();
        Intent getBmp = getIntent();

        bmp = (Bitmap)getBmp.getParcelableExtra("bmp");
        name = getData.getString("strName");
        stdNum = getData.getString("strStdNum");
        course = getData.getString("strCourse");

        ImageView imv = (ImageView)findViewById(R.id.imageView);
        imv.setImageBitmap(bmp);

        TextView edName = (TextView)findViewById(R.id.nameText);
        edName.setText("Name : \t\t\t\t\t" + name);

        TextView edStdNum = (TextView)findViewById(R.id.stdNumText);
        edStdNum.setText("Student Number:\t" + stdNum);

        TextView edCourse = (TextView)findViewById(R.id.courseText);
        edCourse.setText("Course :\t\t\t\t\t" + course);

        //back button function
        Button backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Summary.this,MainActivity.class);
                startActivityForResult(intent,0);
            }
        });



    }

    @Override
    public void onStop(){
        super.onStop();
        saveProfile(null);
    }

    //Saves all the users profile data to shared-prefs and saves the image to file
    public void saveProfile(View view){

        SharedPreferences profile = getPreferences(0);
        SharedPreferences.Editor editor = profile.edit();

        editor.putString("name",name);
        editor.putString("StdNum",stdNum);
        editor.putString("course",course);
        System.out.print( "Got this far");

        //NOT WORKING :(
        try{

            File file = Environment.getExternalStorageDirectory();
            File dest = new File(file,fileName);
            FileOutputStream fout = new FileOutputStream(dest);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fout);
            fout.flush();
            fout.close();

            Toast.makeText(this,"Profile settings have been saved!",Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error Saving the Picture",Toast.LENGTH_LONG).show();
        }

        editor.apply();
    }
}
