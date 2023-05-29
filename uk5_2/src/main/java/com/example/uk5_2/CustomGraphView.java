package com.example.uk5_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomGraphView extends View {
    private List<Integer> series1;
    private List<Integer> series2;
    private Paint paintSeries1;
    private Paint paintSeries2;

    public CustomGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        series1 = new ArrayList<>();
        series2 = new ArrayList<>();

        // Nastavte barvy pro série
        paintSeries1 = new Paint();
        paintSeries1.setColor(Color.RED);

        paintSeries2 = new Paint();
        paintSeries2.setColor(Color.BLUE);
    }

    public void setSeries1(List<Integer> series1) {
        this.series1 = series1;
        invalidate(); // Aktualizovat graf
    }

    public void setSeries2(List<Integer> series2) {
        this.series2 = series2;
        invalidate(); // Aktualizovat graf
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int series1Size = series1.size();
        int series2Size = series2.size();

        if (series1Size == 0 || series2Size == 0) {
            // Handle empty series
            return;
        }

        int barWidth = width / Math.max(series1Size, series2Size);

        int maxValue = Math.max(Collections.max(series1), Collections.max(series2));
        float ratio = (float) height / maxValue;

        Paint paint = new Paint();

        for (int i = 0; i < Math.min(series1Size, series2Size); i++) {
            int value1 = series1.get(i);
            int value2 = series2.get(i);

            // Handle division by zero scenario
            if (value2 == 0) {
                // Set a default value or handle the situation as desired
                continue;
            }

            int barHeight1 = (int) (value1 * ratio);
            int barHeight2 = (int) (value2 * ratio);

            int left = i * barWidth;
            int right = left + barWidth;

            // Draw series 1 bar
            int top1 = height - barHeight1;
            int bottom1 = height;
            paint.setColor(Color.RED);
            canvas.drawRect(left, top1, right, bottom1, paint);

            // Draw series 2 bar
            int top2 = height - barHeight1 - barHeight2;
            int bottom2 = height - barHeight1;
            paint.setColor(Color.BLUE);
            canvas.drawRect(left, top2, right, bottom2, paint);
        }
    }

}
