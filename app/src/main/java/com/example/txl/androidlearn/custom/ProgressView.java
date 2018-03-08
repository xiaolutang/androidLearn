package com.example.txl.androidlearn.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.txl.androidlearn.R;

/**
 * Copyright (c) 2018, 唐小陆 All rights reserved.
 * author：txl
 * date：2018/3/7
 * description：
 */
public class ProgressView extends View {
    private static String TAG = ProgressView.class.getSimpleName();

    /**圆心*/
    private float centerX;
    private float centerY;
    /**内部圆半径*/
    private float mRadius;
    private int mRadiusColor;
    /**圆弧半径*/
    private float arcRadius;
    private int arcRadiusColor;

    String text;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final TypedArray typedArray  = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mRadius = typedArray.getDimension(R.styleable.ProgressView_mRadius,50);
        mRadiusColor = typedArray.getColor(R.styleable.ProgressView_mRadiusColor,Color.BLACK);
        arcRadius = typedArray.getDimension(R.styleable.ProgressView_arcRadius, 100);
        arcRadiusColor = typedArray.getColor(R.styleable.ProgressView_arcRadiusColor, Color.BLUE);
        text = typedArray.getString(R.styleable.ProgressView_text);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCenterCircle(canvas);
        drawArc(canvas);
        super.onDraw(canvas);
    }

    /**
     * 画圆
     * */
    private void drawCenterCircle(Canvas canvas){
        centerX = getWidth()/2+getX();
        centerY = getHeight()/2+getY();
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(false);
        mPaint.setColor(mRadiusColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        float radius = Math.min(getWidth()/2, getHeight()/2);
        mRadius = Math.min(radius,mRadius);
        canvas.drawCircle(centerX,centerY,mRadius,mPaint);
        canvas.save();
    }

    /**
     * 画圆弧
     * */
    private void drawArc(Canvas canvas){
        centerX = getWidth()/2+getX();
        centerY = getHeight()/2+getY();
        RectF rect = new RectF(centerX-arcRadius,centerY-arcRadius,centerX+arcRadius,centerY+arcRadius);
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        mPaint.setAntiAlias(false);
        mPaint.setColor(arcRadiusColor);
        canvas.drawArc(rect,0,180,false,mPaint);
        canvas.save();
    }

    private void drawText(Canvas canvas){}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("mRadius = "+mRadius);
        return builder.toString();
    }
}
