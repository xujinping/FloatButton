package com.example.xjp.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class RTLayout extends LinearLayout {
    public RTLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                MyLog.e("RTLayout---dispatchTouchEvent---DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                MyLog.e("RTLayout---dispatchTouchEvent---MOVE");
                break;
            case MotionEvent.ACTION_UP:
                MyLog.e("RTLayout---dispatchTouchEvent---UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                MyLog.e("RTLayout---onTouchEvent---DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                MyLog.e("RTLayout---onTouchEvent---MOVE");
                break;
            case MotionEvent.ACTION_UP:
                MyLog.e("RTLayout---onTouchEvent---UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}