package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class survey extends AppCompatActivity {

    EditText tvName, tvAge, tvGender;
    Button btnNext;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        btnNext = findViewById(R.id.btnNext);
        db = new MyDBHelper(survey.this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tvName.getText().toString().trim();
                String age = tvAge.getText().toString().trim();
                String gender = tvGender.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    tvName.setError("Name is required. ");
                    return;
                }
                if(TextUtils.isEmpty(age)){
                    tvAge.setError("Age is required. ");
                    return;
                }
                if(TextUtils.isEmpty(gender)){
                    tvGender.setError("Gender is required. ");
                    return;
                }

                db.inputInfo(name, age, gender);
                Intent i = new Intent(survey.this, survey2.class);
                startActivity(i);
                finish();
            }
        });
    }
}