package com.example.uk5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> heightPoints;
    private XYPlot plot;
    private EditText heightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightPoints = new ArrayList<>();

        heightInput = findViewById(R.id.editText_height);

        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHeightPoint();
            }
        });

        plot = findViewById(R.id.plot);
        plot.setRangeLabel("Height");
        plot.setDomainLabel("Index");

        Button plotButton = findViewById(R.id.button_plot);
        plotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plotHeightPoints();
            }
        });
    }

    private void saveHeightPoint() {
        try {
            int height = Integer.parseInt(heightInput.getText().toString());
            heightPoints.add(height);
            heightInput.setText("");
            System.out.println("Height point saved: " + height);
        } catch (NumberFormatException e) {
            System.out.println("Invalid height value!");
        }
    }

    private void plotHeightPoints() {
        plot.clear();

        Number[] seriesIndex = new Number[heightPoints.size()];
        Number[] seriesHeight = new Number[heightPoints.size()];

        for (int i = 0; i < heightPoints.size(); i++) {
            seriesIndex[i] = i;
            seriesHeight[i] = heightPoints.get(i);
        }

        XYSeries heightSeries = new SimpleXYSeries(
                Arrays.asList(seriesIndex),
                Arrays.asList(seriesHeight),
                "Height Series");

        LineAndPointFormatter formatter = new LineAndPointFormatter(
                Color.BLUE,                   // line color
                Color.GREEN,                  // point color
                null,                         // fill color (none)
                null);                        // text color

        plot.addSeries(heightSeries, formatter);
        plot.redraw();
    }
}