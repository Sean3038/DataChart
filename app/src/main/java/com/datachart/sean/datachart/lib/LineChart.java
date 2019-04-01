package com.datachart.sean.datachart.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.datachart.sean.datachart.lib.data.LineDataSet;
import com.datachart.sean.datachart.lib.gesture.ChartGesture;
import com.datachart.sean.datachart.lib.render.ChartRender;

import java.util.ArrayList;

public class LineChart extends View {

    ChartRender chartRender;
    ChartAnimation chartAnimation;
    ChartDataSchema chartDataSchema;
    ChartGesture chartGesture;
    SelectDataSetListener listener;

    float density;

    public LineChart(Context context) {
        this(context,null,0,0);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0,0);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context,attrs,defStyleAttr,0);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        chartRender = new ChartRender();
        chartAnimation = new ChartAnimation(new ChartAnimation.OnAnimationUpdate() {
            @Override
            public void onUpdate() {
                invalidate();
            }
        });
        chartDataSchema =new ChartDataSchema();
        chartGesture = new ChartGesture(chartRender,chartDataSchema);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        chartAnimation.onDraw(canvas,chartDataSchema,chartRender);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        chartDataSchema.setChartSize(w,h);
        chartDataSchema.compute();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        chartAnimation.onStartAnimation();

    }

    @Override
    protected void onDetachedFromWindow() {
        chartAnimation.onStopAnimation();
        super.onDetachedFromWindow();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                chartGesture.onClick(event,listener);
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void setDataSet(ArrayList<LineDataSet> dataSets){
        chartDataSchema.setDataSet(dataSets);
        chartRender.cleanStatus();
    }

    public void setLabelCount(int count){
        chartDataSchema.setLabelCount(count);
    }

    public void showXAxis(boolean isShow){
        chartRender.setXAxisShow(isShow);
    }

    public void showYAxis(boolean isShow){
        chartRender.setYAxisShow(isShow);
    }

    public void refresh(){
        chartDataSchema.compute();
        chartAnimation.onStartAnimation();
    }

    public void setSelectDataSetListener(SelectDataSetListener listener) {
        this.listener = listener;
    }

    public interface SelectDataSetListener{
        void onSelected(LineDataSet dataSet);
    }

}
