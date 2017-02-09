package com.warrantix.main.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class UnPressableLinearLayout extends LinearLayout
{

    public UnPressableLinearLayout(Context context) {
        super(context);
    }

    public UnPressableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnPressableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnPressableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setPressed(boolean pressed)
    {
        // Do nothing here. Specifically, do not propagate this message along
        // to our children so they do not incorrectly display a pressed state
        // just because one of their ancestors got pressed.
    }
}