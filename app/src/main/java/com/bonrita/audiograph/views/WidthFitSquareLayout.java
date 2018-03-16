package com.bonrita.audiograph.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class WidthFitSquareLayout extends FrameLayout {

    private boolean mForceSquare = true;

    public WidthFitSquareLayout(@NonNull Context context) {
        super(context);
    }

    public WidthFitSquareLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WidthFitSquareLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WidthFitSquareLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, mForceSquare ? widthMeasureSpec : heightMeasureSpec);
    }

    public void forceSquare(boolean forceSquare) {
        mForceSquare = forceSquare;
        requestLayout();
    }
}
