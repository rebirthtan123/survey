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

public class Q3Graph extends AppCompatActivity {

    BarChart barchart;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3_graph);
        barchart = findViewById(R.id.barchart3);
        db = new MyDBHelper(this);
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;

        Cursor cursor = db.view();
        if(cursor.getCount() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(Q3Graph.this);
            builder.setCancelable(true);
            builder.setTitle("Message / Information");
            builder.setMessage("No data in the database. ");
            builder.show();
        }

        for(int x = 0; x < db.q3YData().size(); x++){
            if (db.q3YData().get(x).equals("1"))
                a++;
            if (db.q3YData().get(x).equals("2"))
                b++;
            if(db.q3YData().get(x).equals("3"))
                c++;
            if(db.q3YData().get(x).equals("4"))
                d++;
            if(db.q3YData().get(x).equals("5"))
                e++;
        }

        ArrayList<BarEntry> graph = new ArrayList<>();
        graph.add(new BarEntry(1, a));
        graph.add(new BarEntry(2, b));
        graph.add(new BarEntry(3, c));
        graph.add(new BarEntry(4, d));
        graph.add(new BarEntry(5, e));

        BarDataSet barDataSet = new BarDataSet(graph, "Rating : 1     2     3     4     5 ");
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);


        BarData barData = new BarData(barDataSet);
        barchart.setFitBars(true);
        barchart.setData(barData);
        barchart.getDescription().setEnabled(false);
        barchart.animateY(2000);
    }
}