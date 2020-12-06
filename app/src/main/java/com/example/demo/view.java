package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    RecyclerView rv;
    MyDBHelper db;
    ArrayList<String> id,q1,q2,q3;
    viewAdapter vAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        rv = findViewById(R.id.rv);
        db = new MyDBHelper(this);
        id = new ArrayList<>();
        q1 = new ArrayList<>();
        q2 = new ArrayList<>();
        q3 = new ArrayList<>();

        Cursor cursor = db.view();
        if(cursor.getCount() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.this);
            builder.setCancelable(true);
            builder.setTitle("Message / Information");
            builder.setMessage("No data in the database. ");
            builder.show();
        }
        while (cursor.moveToNext()){
            id.add(cursor.getString(0));
            q1.add(cursor.getString(1));
            q2.add(cursor.getString(2));
            q3.add(cursor.getString(3));
        }

        vAdapter = new viewAdapter(view.this, id, q1, q2, q3);
        rv.setAdapter(vAdapter);
        rv.setLayoutManager(new LinearLayoutManager(view.this));

    }
}