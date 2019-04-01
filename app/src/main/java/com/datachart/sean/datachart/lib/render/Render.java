package com.datachart.sean.datachart.lib.render;

import android.graphics.Canvas;

import com.datachart.sean.datachart.lib.ChartDataSchema;

public interface Render {
    void onRender(Canvas canvas, ChartDataSchema chartDataSchema, float progress);
}
