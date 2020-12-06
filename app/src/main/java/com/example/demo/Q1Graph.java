package com.example.demo;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS;

public class Q1Graph extends AppCompatActivity {

    BarChart barchart;
    MyDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1_graph);
        barchart = findViewById(R.id.barchart1);
        db = new MyDBHelper(this);
        int good = 0;
        int med = 0;
        int bad = 0;

        Cursor cursor = db.view();
        if(cursor.getCount() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(Q1Graph.this);
            builder.setCancelable(true);
            builder.setTitle("Message / Information");
            builder.setMessage("No data in the database. ");
            builder.show();
        }

        for(int x = 0; x < db.q1YData().size(); x++){
            if (db.q1YData().get(x).equals("Good"))
                good++;
            if (db.q1YData().get(x).equals("Medium"))
                med++;
            if(db.q1YData().get(x).equals("Bad"))
                bad++;
        }

        ArrayList<BarEntry> graph = new ArrayList<>();
        graph.add(new BarEntry(1, good));
        graph.add(new BarEntry(2, med));
        graph.add(new BarEntry(3, bad));

        BarDataSet barDataSet = new BarDataSet(graph, "1. Good     2. Medium     3. Bad");
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barchart.setFitBars(true);
        barchart.setData(barData);
        barchart.getDescription().setEnabled(false);
        barchart.animateY(2000);
    }
}