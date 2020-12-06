package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Q2Graph extends AppCompatActivity {

    BarChart barchart;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2_graph);
        barchart = findViewById(R.id.barchart2);
        db = new MyDBHelper(this);
        int valX = 0;
        int valY = 0;

        Cursor cursor = db.view();
        if(cursor.getCount() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(Q2Graph.this);
            builder.setCancelable(true);
            builder.setTitle("Message / Information");
            builder.setMessage("No data in the database. ");
            builder.show();
        }

        for(int x = 0; x < db.q2YData().size(); x++){
            if (db.q2YData().get(x).equals("Brand X"))
                valX++;
            if (db.q2YData().get(x).equals("Brand Y"))
                valY++;

        }

        ArrayList<BarEntry> graph = new ArrayList<>();
        graph.add(new BarEntry(1, valX));
        graph.add(new BarEntry(2, valY));

        BarDataSet barDataSet = new BarDataSet(graph, "1. Brand X     2. Brand Y ");
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barchart.setFitBars(true);
        barchart.setData(barData);
        barchart.getDescription().setEnabled(false);
        barchart.animateY(2000);
    }
}