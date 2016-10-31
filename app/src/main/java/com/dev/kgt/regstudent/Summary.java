package com.dev.kgt.regstudent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Kyle on 30/10/2016.
 *
 * Prompts the user to accept the photo taken
 * or retry.
 */

public class Summary extends AppCompatActivity{

    private Bitmap bmp;
    private String name,stdNum,course;

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
        edStdNum.setText("Student Number : " + stdNum);

        TextView edCourse = (TextView)findViewById(R.id.courseText);
        edCourse.setText("Course : \t\t\t\t\t " + course);

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






}
