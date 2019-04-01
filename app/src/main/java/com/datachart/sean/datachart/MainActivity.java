package com.datachart.sean.datachart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.datachart.sean.datachart.lib.LineChart;
import com.datachart.sean.datachart.lib.PayLogDataSet;
import com.datachart.sean.datachart.lib.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LineChart lineChart;
    TextView result;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.chart);
        result = findViewById(R.id.result);
        lineChart.setLabelCount(6);
//        lineChart.showYAxis(false);
        lineChart.setSelectDataSetListener(new LineChart.SelectDataSetListener() {
            @Override
            public void onSelected(LineDataSet dataSet) {
                PayLogDataSet payLogDataSet = (PayLogDataSet) dataSet;
                result.setText(payLogDataSet.getDate().getTime()+"\n"+payLogDataSet.getY());
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lineChart.setDataSet(onChange());
                    lineChart.refresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.button).performClick();
    }

    public ArrayList<LineDataSet> onChange() throws ParseException {
        if(flag){
            flag=false;
            return get6DataSet();
        }else{
            flag=true;
            return get12DataSet();
        }
    }

    public ArrayList<LineDataSet> get12DataSet() throws ParseException {
        ArrayList<LineDataSet> result = new ArrayList<>();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMM");
        result.add(new PayLogDataSet(0, sdf.parse("201908")));
        result.add(new PayLogDataSet(0, sdf.parse("201907")));
        result.add(new PayLogDataSet(840, sdf.parse("201906")));
        result.add(new PayLogDataSet(200, sdf.parse("201905")));
        result.add(new PayLogDataSet(300, sdf.parse("201904")));
        result.add(new PayLogDataSet(600, sdf.parse("201903")));
        result.add(new PayLogDataSet(1000, sdf.parse("201902")));
        result.add(new PayLogDataSet(0, sdf.parse("201901")));
        result.add(new PayLogDataSet(840, sdf.parse("201812")));
        result.add(new PayLogDataSet(500, sdf.parse("201811")));
        result.add(new PayLogDataSet(0, sdf.parse("201810")));
        result.add(new PayLogDataSet(0, sdf.parse("201809")));
        return result;
    }

    public ArrayList<LineDataSet> get6DataSet() throws ParseException {
        ArrayList<LineDataSet> result = new ArrayList<>();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMM");
        result.add(new PayLogDataSet(1000, sdf.parse("201908")));
        result.add(new PayLogDataSet(750, sdf.parse("201907")));
        result.add(new PayLogDataSet(840, sdf.parse("201906")));
        result.add(new PayLogDataSet(200, sdf.parse("201905")));
        result.add(new PayLogDataSet(300, sdf.parse("201904")));
        result.add(new PayLogDataSet(600, sdf.parse("201903")));
        return result;
    }
}
