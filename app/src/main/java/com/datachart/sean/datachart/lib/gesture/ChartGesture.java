package com.datachart.sean.datachart.lib.gesture;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.datachart.sean.datachart.lib.ChartDataSchema;
import com.datachart.sean.datachart.lib.LineChart;
import com.datachart.sean.datachart.lib.data.LineDataSet;
import com.datachart.sean.datachart.lib.render.ChartRender;
import com.datachart.sean.datachart.lib.render.Entry;

public class ChartGesture {

    private ChartRender render;
    private ChartDataSchema schema;

    public ChartGesture(ChartRender render, ChartDataSchema schema){
        this.render = render;
        this.schema = schema;
    }

    public boolean onClick(MotionEvent event, LineChart.SelectDataSetListener listener){
        Entry entry =schema.getSelectEntry(new PointF(event.getX(),event.getY()));
        render.setSelectDataEntry(entry);
        LineDataSet dataSet = schema.getDataSetByEntry(entry);
        if(dataSet!=null && listener !=null){
            listener.onSelected(dataSet);
        }
        return true;
    }
}