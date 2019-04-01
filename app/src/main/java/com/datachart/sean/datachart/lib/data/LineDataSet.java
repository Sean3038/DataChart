package com.datachart.sean.datachart.lib.data;

import com.datachart.sean.datachart.lib.render.Entry;

public abstract class LineDataSet implements Comparable {

    long y;
    String x;

    Entry entry;

    public LineDataSet(){
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
