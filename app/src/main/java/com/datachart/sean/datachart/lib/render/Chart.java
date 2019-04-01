package com.datachart.sean.datachart.lib.render;

import android.graphics.PointF;
import android.graphics.RectF;

public class Chart {

    private RectF mRect = new RectF();
    private RectF mDataRect = new RectF();

    private float mChartHeight;
    private float mChartWidth;

    public Chart(){}

    public void setChartSize(float width,float height){
        float offsetLeft = this.offsetLeft();
        float offsetTop = this.offsetTop();
        float offsetRight = this.offsetRight();
        float offsetBottom = this.offsetBottom();

        float dataOffsetLeft = this.dataOffsetLeft();
        float dataOffsetTop = this.dataOffsetTop();
        float dataOffsetRight = this.dataOffsetRight();
        float dataOffsetBottom = this.dataOffsetBottom();

        mChartHeight = height;
        mChartWidth = width;

        restrainChart(offsetLeft, offsetTop, offsetRight, offsetBottom);
        restrainData(dataOffsetLeft,dataOffsetTop,dataOffsetRight,dataOffsetBottom);
    }

    public float dataOffsetLeft() {
        return mDataRect.left - mRect.left;
    }

    public float dataOffsetRight() {
        return mRect.right-mDataRect.right;
    }

    public float dataOffsetTop() {
        return mDataRect.top-mRect.top;
    }

    public float dataOffsetBottom() {
        return mRect.bottom-mDataRect.bottom;
    }

    public float dataLeft() {
        return mDataRect.left;
    }

    public float dataRight() {
        return mDataRect.right;
    }

    public float dataTop() {
        return mDataRect.top;
    }

    public float dataBottom() {
        return mDataRect.bottom;
    }

    public float dataWidth(){
        return mDataRect.width();
    }

    public float dataHeight(){
        return mDataRect.height();
    }

    public float offsetLeft() {
        return mRect.left;
    }

    public float offsetRight() {
        return mChartWidth - mRect.right;
    }

    public float offsetTop() {
        return mRect.top;
    }

    public float offsetBottom() {
        return mChartHeight - mRect.bottom;
    }

    public float contentTop() {
        return mRect.top;
    }

    public float contentLeft() {
        return mRect.left;
    }

    public float contentRight() {
        return mRect.right;
    }

    public float contentBottom() {
        return mRect.bottom;
    }

    public PointF contentCenter(){
        return new PointF(mRect.centerX(),mRect.centerY());
    }

    public float contentWidth() {
        return mRect.width();
    }

    public float contentHeight() {
        return mRect.height();
    }

    public RectF getContentRect() {
        return mRect;
    }

    public RectF getDataRect() {
        return mDataRect;
    }

    public void restrainChart(float offsetLeft,float offsetTop,float offsetRight,float offsetBottom){
        mRect.set(offsetLeft, offsetTop, mChartWidth - offsetRight, mChartHeight
                - offsetBottom);
    }

    public void restrainData(float dataoffsetLeft,float dataoffsetTop,float dataoffsetRight,float dataoffsetBottom){
        mDataRect.set(contentLeft()+dataoffsetLeft, contentTop()+dataoffsetTop, contentRight()- dataoffsetRight, contentBottom()
                - dataoffsetBottom);
    }
}
