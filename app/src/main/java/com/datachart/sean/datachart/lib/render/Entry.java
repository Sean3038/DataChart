package com.datachart.sean.datachart.lib.render;

public class Entry {

    private float x;
    private float y;
    private String extra;

    public Entry(float x, float y,String extra){
        this.x = x;
        this.y = y;
        this.extra = extra;
    }

    public Entry(float x, float y) {
        this.x = x;
        this.y = y;
        this.extra = "";
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
