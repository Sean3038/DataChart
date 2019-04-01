package com.datachart.sean.datachart.lib.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;

import com.datachart.sean.datachart.lib.ChartDataSchema;
import com.datachart.sean.datachart.lib.render.strategy.CubicRenderStrategy;
import com.datachart.sean.datachart.lib.render.strategy.DataRenderStrategy;

public class DataRender implements Render {
    private Paint textPaint = new Paint();
    private Paint linePaint = new Paint();
    private Paint lineAreaPaint;
    private Paint dotPaint = new Paint();
    private Paint dotStrokePaint = new Paint();
    private Paint selectPaint = new Paint();
    private PathMeasure pathMeasure = new PathMeasure();

    private DataRenderStrategy strategy;

    private float dotRadius = 10;

    private float textPaddingX = -10;
    private float textPaddingY = -25;

    private boolean isShowData = false;

    private Entry selectEntry;

    DataRender() {
        linePaint.setStrokeWidth(5.0f);
        linePaint.setColor(Color.parseColor("#14796c"));
        linePaint.setStrokeCap(Paint.Cap.SQUARE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);

        dotPaint.setAntiAlias(true);
        dotPaint.setColor(Color.parseColor("#14796c"));
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setAntiAlias(true);

        dotStrokePaint.setAntiAlias(true);
        dotStrokePaint.setColor(Color.WHITE);
        dotStrokePaint.setStrokeWidth(2.5f);
        dotStrokePaint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        selectPaint.setStyle(Paint.Style.FILL);
        selectPaint.setColor(Color.parseColor("#45a99d"));
        selectPaint.setAntiAlias(true);

        strategy = new CubicRenderStrategy();
    }

    @Override
    public void onRender(Canvas canvas, ChartDataSchema chartDataSchema, float progress) {
        Chart chart = chartDataSchema.getChart();
        Entry[] entries = chartDataSchema.getDataEntry();
        Path path = strategy.link(chartDataSchema);
        Path dst = new Path();
        Path area = new Path();


        pathMeasure.setPath(path, false);
        pathMeasure.getSegment(0, pathMeasure.getLength() * progress, dst, true);

        float[] currentPoint = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength() * progress, currentPoint, null);

        area.moveTo(chart.dataLeft() + entries[0].getX(), chart.dataBottom());
        area.lineTo(chart.dataLeft() + entries[0].getX(), chart.dataBottom() - entries[0].getY());
        area.addPath(dst);
        area.lineTo(chart.dataLeft() + currentPoint[0], chart.dataBottom());
        area.lineTo(chart.dataLeft() + entries[0].getX(), chart.dataBottom());

        canvas.drawPath(dst, linePaint);
        if (lineAreaPaint != null) {
            canvas.drawPath(area, lineAreaPaint);
        }
        for (Entry entry : entries) {
            if (currentPoint[0] + 1 >= chart.dataLeft() + entry.getX()) {
                if(selectEntry!=null){
                    if(selectEntry == entry){
                        selectEntry = entry;
                        canvas.drawCircle(chart.dataLeft() + entry.getX(), chart.dataBottom() - entry.getY(), dotRadius + 10, selectPaint);
                    }
                }
                canvas.drawCircle(chart.dataLeft() + entry.getX(), chart.dataBottom() - entry.getY(), dotRadius + 5, dotStrokePaint);
                canvas.drawCircle(chart.dataLeft() + entry.getX(), chart.dataBottom() - entry.getY(), dotRadius, dotPaint);
                if(isShowData) {
                    canvas.drawText(entry.getExtra(), chart.dataLeft() + entry.getX() + textPaddingX, chart.dataBottom() - entry.getY() + textPaddingY, textPaint);
                }
            }
        }
    }

    public void setSelectEntry(Entry selectEntry){
        this.selectEntry = selectEntry;
    }

    public Entry getSelectEntry(){
        return selectEntry;
    }

    public void clean(){
        selectEntry = null;
    }

    public void setAreaGradient(LinearGradient gradient) {
        lineAreaPaint = new Paint();
        lineAreaPaint.setShader(gradient);
        lineAreaPaint.setColor(Color.parseColor("#FFFFFF"));
        lineAreaPaint.setAntiAlias(true);
        lineAreaPaint.setStyle(Paint.Style.FILL);
        lineAreaPaint.setStrokeWidth(5f);
    }

    public void setAreaColor(int color) {
        lineAreaPaint = new Paint();
        lineAreaPaint.setColor(color);
        lineAreaPaint.setShader(null);
        lineAreaPaint.setAntiAlias(true);
        lineAreaPaint.setStyle(Paint.Style.FILL);
        lineAreaPaint.setStrokeWidth(5f);
    }

    public void setLineColor(int lineColor) {
        linePaint.setColor(lineColor);
    }

    public void setDataTextSize(float size) {
        textPaint.setTextSize(size);
    }
}
