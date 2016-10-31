package com.dev.kgt.regstudent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(v);
            }
        });
    }

    //take a picture
    public void capturePic(View view){
        Intent camInt = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(camInt,0);
    }

    public void onActivityResult(int reqCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK){

            Bundle extra = data.getExtras();
            bmp = (Bitmap)extra.get("data");

            ImageView imv = (ImageView)findViewById(R.id.imageTaken);
            imv.setImageBitmap(bmp);
        }
    }

    //method triggers when the submit button is pressed
    public void passData(View view){

        EditText edName = (EditText)findViewById(R.id.nameText);
        String strName = edName.getText().toString();

        EditText edStdNum = (EditText)findViewById(R.id.stdNumText);
        String strStdNum = edStdNum.getText().toString();

        EditText edCourse = (EditText)findViewById(R.id.courseText);
        String strCourse = edCourse.getText().toString();

        //Validate fields is not empty
        if(strName.isEmpty() || strName.equals("Name") || strStdNum.isEmpty() || strStdNum.equals("Student Number")
                || strCourse.isEmpty() || strCourse.equals("Course") || bmp.equals(null)){

            Toast.makeText(this,"Some Fields are missing!",Toast.LENGTH_LONG).show();
        }else{

            Intent dataInt = new Intent(MainActivity.this,Summary.class);

            dataInt.putExtra("strName",strName);
            dataInt.putExtra("strStdNum",strStdNum);
            dataInt.putExtra("strCourse",strCourse);
            dataInt.putExtra("bmp",bmp);

            startActivityForResult(dataInt,0);
        }
    }


}
