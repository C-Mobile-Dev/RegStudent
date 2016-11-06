package com.dev.kgt.regstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restoreProfile(null);

        Button submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(v);
            }
        });

        //Get the EditText Obj and validate the contents have been entered
        final EditText name_Text = (EditText)findViewById(R.id.nameText);
        name_Text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && name_Text.getText().toString().equals("Name")){
                    name_Text.setText("");
                }else if(!hasFocus && name_Text.getText().toString().equals("")){
                        name_Text.setText("Name");
                }
            }
        });
        //Get the EditText Obj and validate the contents have been entered
        final EditText studNum_Text = (EditText)findViewById(R.id.stdNumText);
        studNum_Text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && studNum_Text.getText().toString().equals("Student Number")){
                    studNum_Text.setText("");
                }else if(!hasFocus && studNum_Text.getText().toString().equals("")){
                    studNum_Text.setText("Student Number");
                }
            }
        });
        //Get the EditText Obj and validate the contents have been entered
        final EditText course_Text = (EditText)findViewById(R.id.courseText);
        course_Text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && course_Text.getText().toString().equals("Course")){
                    course_Text.setText("");
                }else if(!hasFocus && course_Text.getText().toString().equals("")){
                    course_Text.setText("Course");
                }
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

        //Validate fields are not empty before data submission
        if(strName.isEmpty() || strName.equals("Name") || strStdNum.isEmpty() || strStdNum.equals("Student Number")
                || strCourse.isEmpty() || strCourse.equals("Course") || bmp == null){

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

    //not working correctly (!!! check communication between activities!!! )
    public void restoreProfile(View view){

        SharedPreferences settings = getPreferences(0);

        EditText name = (EditText)findViewById(R.id.nameText);
        name.setText(settings.getString("name","Name"), TextView.BufferType.EDITABLE);
        EditText stdNum = (EditText)findViewById(R.id.stdNumText);
        stdNum.setText(settings.getString("stdNum","Student Number"), TextView.BufferType.EDITABLE);
        EditText course = (EditText)findViewById(R.id.courseText);
        course.setText(settings.getString("course","Course"), TextView.BufferType.EDITABLE);

    }
}
