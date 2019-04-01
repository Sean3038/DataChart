package com.datachart.sean.datachart.lib.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.datachart.sean.datachart.lib.ChartDataSchema;

/**
 * X軸繪製
 * */
public class XAxisRender implements Render{

    private Paint textPaint = new Paint();

    private boolean isRender = true;

    XAxisRender() {
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32f);
        textPaint.setStrokeWidth(5.f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }

    public void setTextSize(float size) {
        textPaint.setTextSize(size);
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    @Override
    public void onRender(Canvas canvas, ChartDataSchema chartDataSchema, float progress) {
        if(isRender) {
            Chart chart = chartDataSchema.getChart();
            for (Entry entry : chartDataSchema.getXEntry()) {
                canvas.drawText(entry.getExtra(), chart.contentLeft() + entry.getX(), chart.contentBottom()+30 - 1, textPaint);
            }
            canvas.drawLine(chart.contentLeft(), chart.contentBottom(), chart.contentRight(), chart.contentBottom(), textPaint);
        }
    }


    public void setVisible(boolean visible){
        isRender = visible;
    }
}
