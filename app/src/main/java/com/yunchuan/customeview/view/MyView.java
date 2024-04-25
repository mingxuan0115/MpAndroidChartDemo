package com.yunchuan.customeview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyView extends View {

    private Paint paint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();

        drawRoundRect(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        switch (MeasureSpec.getMode(widthMeasureSpec)){
            case MeasureSpec.AT_MOST://在布局中指定了wrap_content
                Log.e("View的测量模式","测量模式：AT_MOST   测量实际大小width："+width);
                break;

            case MeasureSpec.EXACTLY: //在布居中指定了确切的值 100dp match_parent fill_parent
                Log.e("View的测量模式","测量模式：EXACTLY   测量实际大小width："+width);
                break;

            case MeasureSpec.UNSPECIFIED:
                Log.e("View的测量模式","测量模式：UNSPECIFIED   测量实际大小width："+width);
                break;
        }

        switch (MeasureSpec.getMode(heightMeasureSpec)){
            case MeasureSpec.AT_MOST:
                Log.e("View的测量模式","测量模式：AT_MOST   测量实际大小height："+height);
                break;
            case MeasureSpec.EXACTLY:
                Log.e("View的测量模式","测量模式：EXACTLY   测量实际大小height："+height);
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.e("View的测量模式","测量模式：UNSPECIFIED   测量实际大小height："+height);
                break;
        }

        setMeasuredDimension(width,height);

    }

    private void drawCircle(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);//填充模式，就是把整个圆填充为这个颜色
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 40, paint);

        paint.reset();

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);//描边模式,就在在圆的外围划出此颜色
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 40, paint);

        paint.reset();


        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);//描边模式/填充与描边模式 实际显示半径 r = 圆半径 + （画笔的宽度 / 2）,效果上是跟填充模式一样的，只是半径会大些，大了画笔宽度的一半
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 40, paint);
    }


    private void drawAngle(Canvas canvas){
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF=new RectF(100,100,500,500);
        canvas.drawArc(rectF,0,360,false,paint);
    }

    private void drawProgress(Canvas canvas){
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF rectF=new RectF(100,100,500,500);
        canvas.drawArc(rectF,0,90,false,paint);
    }


    private void drawRoundRect(Canvas canvas){
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF rectF=new RectF(100,100,500,500);
        canvas.drawRoundRect(rectF,0,90,paint);
    }

}
