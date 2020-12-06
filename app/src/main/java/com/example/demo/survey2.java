package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class survey2 extends AppCompatActivity {

    RadioGroup g1,g2,g3;
    Button btnSubmit;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey2);
        btnSubmit = findViewById(R.id.btnSubmit);
        db = new MyDBHelper(survey2.this);
        g1 = findViewById(R.id.group1);
        g2 = findViewById(R.id.group2);
        g3 = findViewById(R.id.group3);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g1.getCheckedRadioButtonId() == 0){
                    Toast.makeText(survey2.this, "Question 1 is empty. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(g2.getCheckedRadioButtonId() == 0){
                    Toast.makeText(survey2.this, "Question 2 is empty. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(g3.getCheckedRadioButtonId() == 0){
                    Toast.makeText(survey2.this, "Question 3 is empty. ", Toast.LENGTH_SHORT).show();
                    return;
                }

                String answer1 = ((RadioButton)findViewById(g1.getCheckedRadioButtonId())).getText().toString();
                String answer2 = ((RadioButton)findViewById(g2.getCheckedRadioButtonId())).getText().toString();
                String answer3 = ((RadioButton)findViewById(g3.getCheckedRadioButtonId())).getText().toString();

                Boolean insert = db.inputSurvey(answer1, answer2, answer3);
                if(insert == true){
                    Toast.makeText(survey2.this, "Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(survey2.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(survey2.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}