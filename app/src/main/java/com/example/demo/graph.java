package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class graph extends AppCompatActivity {

    Button q1,q2,q3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        q1 = findViewById(R.id.btnQ1);
        q2 = findViewById(R.id.btnQ2);
        q3 = findViewById(R.id.btnQ3);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(graph.this, Q1Graph.class);
                startActivity(i);
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(graph.this, Q2Graph.class);
                startActivity(i);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(graph.this, Q3Graph.class);
                startActivity(i);
            }
        });
    }
}