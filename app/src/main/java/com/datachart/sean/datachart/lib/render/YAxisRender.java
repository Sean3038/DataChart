package com.datachart.sean.datachart.lib.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.datachart.sean.datachart.lib.ChartDataSchema;

/**
 * Y軸繪製
 * */
public class YAxisRender implements Render{

    private Paint textPaint = new Paint();
    private boolean isRender = true;

    YAxisRender() {
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32f);
        textPaint.setStrokeWidth(5.f);
        textPaint.setTextAlign(Paint.Align.RIGHT);
    }

    public void setTextSize(float size) {
        textPaint.setTextSize(size);
    }

    @Override
    public void onRender(Canvas canvas, ChartDataSchema chartDataSchema, float progress) {
        if(isRender) {
            Chart chart =chartDataSchema.getChart();
            for (Entry entry : chartDataSchema.getYEntry()) {
                canvas.drawText(entry.getExtra(),chart.contentLeft()+ entry.getX(), chart.contentBottom() - entry.getY(), textPaint);
            }
            canvas.drawLine(chart.contentLeft(), chart.contentBottom(), chart.contentLeft(), chart.contentTop(),textPaint);
        }
    }

    public void setVisible(boolean visible){
        this.isRender = visible;
    }
}