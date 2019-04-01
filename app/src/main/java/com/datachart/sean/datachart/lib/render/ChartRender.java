package com.datachart.sean.datachart.lib.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.datachart.sean.datachart.lib.ChartDataSchema;

import java.util.ArrayList;

public class ChartRender implements Render {

    private XAxisRender xAxisRender = new XAxisRender();
    private YAxisRender yAxisRender = new YAxisRender();
    private DataRender dataRender = new DataRender();

    private ArrayList<Render> renders = new ArrayList<>();

    private Paint chartPaint=new Paint();

    public ChartRender(){
        chartPaint.setColor(Color.BLACK);
        chartPaint.setStrokeCap(Paint.Cap.SQUARE);
        chartPaint.setStrokeWidth(5.0f);
        chartPaint.setTextSize(32.f);
        chartPaint.setStyle(Paint.Style.FILL);
        chartPaint.setAntiAlias(true);

        renders.add(xAxisRender);
        renders.add(dataRender);
    }

    private void drawNoData(Canvas canvas, ChartDataSchema chartDataSchema){
        Chart chart = chartDataSchema.getChart();
        PointF center = chart.contentCenter();
        canvas.drawText("Require Data",center.x,center.y,chartPaint);
    }

    @Override
    public void onRender(Canvas canvas, ChartDataSchema chartDataSchema, float progress) {
        if(chartDataSchema.isEmpty()){
            drawNoData(canvas,chartDataSchema);
            return;
        }
        for(Render render:renders){
            render.onRender(canvas,chartDataSchema,progress);
        }
    }

    public void setXAxisShow(boolean isShow){
        xAxisRender.setVisible(isShow);
    }

    public void setYAxisShow(boolean isShow){
        yAxisRender.setVisible(isShow);
    }

    public void setSelectDataEntry(Entry entry){
        dataRender.setSelectEntry(entry);
    }

    public void cleanStatus(){
        dataRender.clean();
    }
}
