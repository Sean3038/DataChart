package com.datachart.sean.datachart.lib;

import android.graphics.PointF;

import com.datachart.sean.datachart.lib.data.LineDataSet;
import com.datachart.sean.datachart.lib.render.Chart;
import com.datachart.sean.datachart.lib.render.Entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 計算圖表位置
 * */
public class ChartDataSchema {

    //縱軸單位數
    private int lableCount;

    ArrayList<LineDataSet> dataSets;

    private ArrayList<Entry> dataEntry;
    private ArrayList<Entry> xEntry;
    private ArrayList<Entry> yEntry;

    private int xinterval;              //橫軸區間
    private float xintervalWidth;       //橫軸區間長度
    private float yintervalHeight;      //縱軸區間長度
    private long maxValue = 0;          //資料最大值

    private Chart chart;

    float width;
    float height;

    ChartDataSchema() {
        dataSets = new ArrayList<>();
        dataEntry = new ArrayList<>();
        xEntry = new ArrayList<>();
        yEntry = new ArrayList<>();
    }

    public void setDataSet(ArrayList<LineDataSet> dataSets) {
        this.dataSets.clear();
        this.dataSets.addAll(dataSets);
        getMaxValue();
        sort();
    }

    private void sort() {
        Collections.sort(dataSets, new Comparator<LineDataSet>() {
            @Override
            public int compare(LineDataSet o1, LineDataSet o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void getMaxValue() {
        for (LineDataSet dataSet : dataSets) {
            maxValue = maxValue < dataSet.getY() ? dataSet.getY() : maxValue;
        }
    }

    public void setLabelCount(int count) {
        this.lableCount = count;
    }

    public void compute() {
        xinterval = dataSets.size() + 1;

        //圖表邊界
        float chartTopMargin = height / 10;
        float chartDownMargin = height / 10;
        float chartLeftMargin = height / 10;
        float chartRightMargin = height / 10;

        //資料邊界
        float dataTopMargin = 0f;
        float dataDownMargin = 20f;
        float dataLeftMargin = 0f;
        float dataRightMargin = 0f;

        chart = new Chart();
        chart.restrainChart(chartLeftMargin, chartTopMargin, chartRightMargin, chartDownMargin);
        chart.restrainData(dataLeftMargin, dataTopMargin, dataRightMargin, dataDownMargin);
        chart.setChartSize(width, height);
        xintervalWidth = (chart.dataWidth()) / xinterval;
        yintervalHeight = (chart.dataHeight()) / (lableCount + 1);

        clearEntry();
        calculateEntry();
    }

    private void calculateEntry() {
        float[] table = computeTable();

        for (LineDataSet dataSet : dataSets) {
            Entry entry = null;

            for (int index = 0; index < table.length; index++) {
                if (table[index] > dataSet.getY()) {
                    float levelRange = table[index] - table[index - 1];

                    float x = (dataSets.indexOf(dataSet) + 1) * xintervalWidth;
                    float y = yintervalHeight * index
                            + yintervalHeight * (dataSet.getY() - table[index]) / levelRange;

                    entry = new Entry(x, y, String.valueOf(dataSet.getY()));
                    break;
                } else if (table[index] == dataSet.getY()) {

                    float x = (dataSets.indexOf(dataSet) + 1) * xintervalWidth;
                    float y = yintervalHeight * index;

                    entry = new Entry(x, y, String.valueOf(dataSet.getY()));
                    break;
                }
            }

            dataEntry.add(entry);
            dataSet.setEntry(entry);
        }


        for (int x = 0; x < dataSets.size(); x++) {
            xEntry.add(new Entry((x + 1) * xintervalWidth, 0, dataSets.get(x).getX()));
        }

        for (int y = 0; y < table.length; y++) {
            yEntry.add(new Entry(0, yintervalHeight * y, String.valueOf(table[y])));
        }

    }

    private float[] computeTable() {
        float[] table = new float[lableCount+2];
        table[table.length - 1] = maxValue;
        float range = maxValue / (lableCount + 1);
        for (int i = 0; i < table.length-1; i++) {
            table[i] = range * i;
        }
        return table;
    }

    private void clearEntry() {
        dataEntry.clear();
        xEntry.clear();
        yEntry.clear();
    }

    public Entry[] getDataEntry() {
        Entry[] vector = new Entry[dataEntry.size()];
        dataEntry.toArray(vector);
        return vector;
    }

    public Entry[] getYEntry() {
        Entry[] vector = new Entry[yEntry.size()];
        yEntry.toArray(vector);
        return vector;
    }

    public Entry[] getXEntry() {
        Entry[] vector = new Entry[xEntry.size()];
        xEntry.toArray(vector);
        return vector;
    }

    public LineDataSet getDataSetByEntry(Entry entry){
        for(LineDataSet dataSet:dataSets){
            if(dataSet.getEntry().equals(entry)){
                return dataSet;
            }
        }
        return null;
    }

    public Entry getSelectEntry(PointF select) {
        float range = getXIntervalWidth() / 2;
        for(Entry entry:getDataEntry()){
            if (chart.contentLeft() + entry.getX() + range > select.x && chart.contentLeft() + entry.getX() - range < select.x) {
                return entry;
            }
        }
        return null;
    }

    public float getXIntervalWidth() {
        return xintervalWidth;
    }

    public boolean isEmpty() {
        return dataEntry.size() < 1;
    }

    public void setChartSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Chart getChart() {
        return chart;
    }
}