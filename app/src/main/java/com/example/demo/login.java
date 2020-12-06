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

public class login extends AppCompatActivity {

    Button btnLogin;
    EditText edtUser;
    EditText edtPw;
    TextView tvRegisterBtn;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        edtUser = findViewById(R.id.edtUser);
        edtPw = findViewById(R.id.edtPW);
        tvRegisterBtn = findViewById(R.id.tvRegister);
        db = new MyDBHelper(login.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
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

                Boolean chk = db.checkUsernamePassword(user, password);
                if(chk == true){
                    Toast.makeText(login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tvRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}