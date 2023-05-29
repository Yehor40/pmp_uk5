package com.example.uk5_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private CustomGraphView graphView;
    private EditText series1EditText;
    private EditText series2EditText;
    private Button addValuesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = findViewById(R.id.graphView);
        series1EditText = findViewById(R.id.series1EditText);
        series2EditText = findViewById(R.id.series2EditText);
        addValuesButton = findViewById(R.id.addValuesButton);

        addValuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValuesToSeries();
            }
        });
    }

    private void addValuesToSeries() {
        String series1Input = series1EditText.getText().toString();
        String series2Input = series2EditText.getText().toString();

        // Parse input values to lists
        List<Integer> series1Values = parseValues(series1Input);
        List<Integer> series2Values = parseValues(series2Input);

        // Set values to CustomGraphView
        graphView.setSeries1(series1Values);
        graphView.setSeries2(series2Values);
    }

    private List<Integer> parseValues(String input) {
        List<Integer> values = new ArrayList<>();

        String[] valueStrings = input.split(",");
        for (String valueString : valueStrings) {
            try {
                int value = Integer.parseInt(valueString.trim());
                values.add(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return values;
    }
}
