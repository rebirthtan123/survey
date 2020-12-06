package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    Button btnRegister;
    EditText edtUser;
    EditText edtPw;
    TextView tvLoginBtn;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        edtUser = findViewById(R.id.edtUser);
        edtPw = findViewById(R.id.edtPW);
        tvLoginBtn = findViewById(R.id.tvLogin);
        db = new MyDBHelper(register.this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String password = edtPw.getText().toString().trim();

                if(TextUtils.isEmpty(user)){
                    edtUser.setError("Username is empty. ");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    edtPw.setError("Password is empty. ");
                    return;
                }
                if(password.length() < 5){
                    edtPw.setError("Password required 5 digit number. ");
                    return;
                }

                Boolean chkUser = db.checkUsername(user);
                if(chkUser == false){
                    Boolean insert = db.insertData(user, password);
                    if(insert == true){
                        Toast.makeText(register.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(register.this, "Register failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(register.this, "User account already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}