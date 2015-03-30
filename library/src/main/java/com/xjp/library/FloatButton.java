package com.xjp.library;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Description:随着手指上下滑动，浮动按钮动画隐藏或者消失
 * User: xjp
 * Date: 2015/3/28
 * Time: 15:23
 */

public class FloatButton extends RelativeLayout {

    private View floatView;
    private float moveY;
    private boolean isDown = false;
    private Context context;
    private String STICKY_TAG = "sticky";
    private int screenH;
    private int animTime = 1000; // 动画时间
    private int position[] = new int[2]; //当前浮动按钮所处屏幕

    public FloatButton(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public FloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        screenH = dm.heightPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            floatView = findStickyViews(this);
            floatView.getLocationInWindow(position);
        }
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        findStickyViews(child);
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
        findStickyViews(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        findStickyViews(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
        findStickyViews(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        findStickyViews(child);
    }

    private View findStickyViews(View v) {
        View view = null;
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                String tag = getStringTagForView(vg.getChildAt(i));
                if (tag != null && tag.contains(STICKY_TAG)) {
                    view = vg.getChildAt(i);
                } else if (vg.getChildAt(i) instanceof ViewGroup) {
                    findStickyViews(vg.getChildAt(i));
                }
            }
        } else {
            String tag = (String) v.getTag();
            if (tag != null && tag.contains(STICKY_TAG)) {
                view = v;
            }
        }
        return view;
    }

    private String getStringTagForView(View v) {
        Object tagObject = v.getTag();
        return String.valueOf(tagObject);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int disY = (int) (moveY - ev.getRawY());
                if (disY < -20 && !isDown) {
                    isDown = true;
                    startAnimDown();
                }
                if (disY > 20 && isDown) {
                    isDown = false;
                    startAnimUp();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                moveY = ev.getRawY();
                break;
        }

        return true;
    }

    /**
     * 浮动按钮出现动画
     */
    private void startAnimUp() {
        Animation animTranlate = new TranslateAnimation(0, 0, screenH - position[1], 0);
        animTranlate.setDuration(animTime * (screenH - position[1]) / screenH);
        animTranlate.setFillAfter(true);
        animTranlate.setInterpolator(context, android.R.interpolator.accelerate_decelerate);
        floatView.startAnimation(animTranlate);
    }

    /**
     * 浮动按钮隐藏动画
     */
    private void startAnimDown() {
        Animation animTranlate = new TranslateAnimation(0, 0, 0, screenH - position[1]);
        animTranlate.setDuration(animTime * (screenH - position[1]) / screenH);
        animTranlate.setFillAfter(true);
        animTranlate.setInterpolator(context, android.R.interpolator.accelerate_decelerate);
        floatView.startAnimation(animTranlate);
    }
}
