package com.example.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by wpc on 7/2/14.
 */
public class SlideCutListView extends ListView {
    private View item;
    private int downX;

    public SlideCutListView(Context context) {
        super(context);
    }

    public SlideCutListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideCutListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:{
                int x = (int)ev.getX();
                int y = (int)ev.getY();
                downX = x;
                item = getChildAt(pointToPosition(x,y)-getFirstVisiblePosition());
                break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                int x = (int)ev.getX();
                int d = downX - x;
                downX = x;
                item.scrollBy(d,0);
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
