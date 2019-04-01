package com.datachart.sean.datachart.lib.render.strategy;

import android.graphics.Path;

import com.datachart.sean.datachart.lib.ChartDataSchema;

public interface DataRenderStrategy {
    Path link(ChartDataSchema chartDataSchema);
}
