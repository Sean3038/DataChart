package com.datachart.sean.datachart.lib.render.strategy;

import android.graphics.Path;

import com.datachart.sean.datachart.lib.ChartDataSchema;
import com.datachart.sean.datachart.lib.render.Chart;
import com.datachart.sean.datachart.lib.render.Entry;

public class CubicSplineRenderStrategy implements DataRenderStrategy {

    private float mCubicIntensity = 0.15f;

    @Override
    public Path link(ChartDataSchema chartDataSchema) {
        Path path = new Path();
        Chart chart = chartDataSchema.getChart();
        Entry[] entries = chartDataSchema.getDataEntry();

        float prevDx = 0f;
        float prevDy = 0f;
        float curDx = 0f;
        float curDy = 0f;
        float dx = 0f;
        float dy = 0f;

        Entry prevPrev;
        Entry prev = entries[0];
        Entry cur = entries[0];
        Entry next = cur;
        int nextIndex = -1;

        if (cur == null) {
            return path;
        }

        path.moveTo(chart.dataLeft() + entries[0].getX(), chart.dataBottom() - entries[0].getY());

        for (int i = 1; i < entries.length; i++) {
            prevPrev = prev;
            prev = cur;
            cur = nextIndex == i ? next : entries[i];

            nextIndex = i + 1 < entries.length ? i + 1 : i;
            next = entries[nextIndex];



            prevDx = (cur.getX() - prevPrev.getX())*mCubicIntensity;
            prevDy = (cur.getY() - prevPrev.getY()) *mCubicIntensity;
            curDx = (next.getX() - prev.getX()) *mCubicIntensity;
            curDy = (next.getY() - prev.getY())*mCubicIntensity;
            path.cubicTo(
                    chart.dataLeft() + (prev.getX() + prevDx),
                    chart.dataBottom() - (prev.getY() + prevDy),
                    chart.dataLeft() + (cur.getX() - curDx),
                    chart.dataBottom() - (cur.getY() - curDy),
                    chart.dataLeft() + cur.getX(),
                    chart.dataBottom() - cur.getY()
            );

        }
        return path;
    }
}
