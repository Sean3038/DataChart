package com.datachart.sean.datachart.lib.render.strategy;

import android.graphics.Path;

import com.datachart.sean.datachart.lib.ChartDataSchema;
import com.datachart.sean.datachart.lib.render.Chart;
import com.datachart.sean.datachart.lib.render.Entry;

public class LinearRenderStrategy implements DataRenderStrategy{

    @Override
    public Path link(ChartDataSchema chartDataSchema) {
        Path path = new Path();
        Chart chart = chartDataSchema.getChart();
        Entry[] entries = chartDataSchema.getDataEntry();
        path.moveTo(chart.dataLeft() + entries[0].getX(), chart.dataBottom() - entries[0].getY());
        for (int i = 1; i < entries.length; i++) {
            path.lineTo(chart.dataLeft() + entries[i].getX(), chart.dataBottom() - entries[i].getY());
        }
        return path;
    }
}
