package com.datachart.sean.datachart.lib;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.LinearInterpolator;

import com.datachart.sean.datachart.lib.render.ChartRender;

public class ChartAnimation {

    private ValueAnimator valueAnimator;
    private float progress = 100f;
    private OnAnimationUpdate listener;

    ChartAnimation(OnAnimationUpdate listener){
        this.listener = listener;
        init();
    }

    private void init(){
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1500)
                .setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                listener.onUpdate();
            }
        });
    }

    public void onDraw(Canvas canvas, ChartDataSchema schema, ChartRender chartRender) {
        chartRender.onRender(canvas, schema, progress);
    }

    public void onStartAnimation(){
        valueAnimator.start();
    }

    public void onStopAnimation(){
        valueAnimator.cancel();
    }

    interface OnAnimationUpdate{
        void onUpdate();
    }
}
