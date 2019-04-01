package com.datachart.sean.datachart.lib;

import android.support.annotation.NonNull;

import com.datachart.sean.datachart.lib.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PayLogDataSet extends LineDataSet {

    SimpleDateFormat sdf = new SimpleDateFormat("MM");

    Date date;

    public PayLogDataSet(long y, Date x) {
        setX(sdf.format(x));
        setY(y);
        this.date = x;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        PayLogDataSet o2= (PayLogDataSet) o;
        return date.compareTo(o2.getDate());
    }
}