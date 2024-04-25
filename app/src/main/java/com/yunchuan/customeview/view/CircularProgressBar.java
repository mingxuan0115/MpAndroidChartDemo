package com.yunchuan.customeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.yunchuan.customeview.R;

public class CircularProgressBar extends View {
    /**
     * 半径
     */
    private int mRadius;
    /**
     * 进度条宽度
     */
    private int mStrokeWidth;
    /**
     * 进度条背景颜色
     */
    private int mProgressbarBgColor;
    /**
     * 进度条颜色
     */
    private int mProgressColor;
    /**
     * 开始角度
     */
    private int mStartAngle = 0;
    /**
     * 当前角度
     */
    private float mCurrentAngle = 0;
    /**
     * 结束角度
     */
    private int mEndAngle = 360;

    /**
     * 最大进度
     */
    private float mMaxProgress;
    /**
     * 当前进度
     */
    private float mCurrentProgress;
    /**
     * 文字
     */
    private String mText;
    /**
     * 文字颜色
     */
    private int mTextColor;
    /**
     * 文字大小
     */
    private float mTextSize;
    /**
     * 动画的执行时长
     */
    private long mDuration = 1000;
    /**
     * 是否执行动画
     */
    private boolean isAnimation = false;


    public CircularProgressBar(Context context) {
        this(context,null);
    }

    public CircularProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircularProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressBar);
        mRadius = array.getDimensionPixelSize(R.styleable.CircularProgressBar_radius, 80);
        mStrokeWidth = array.getDimensionPixelSize(R.styleable.CircularProgressBar_strokeWidth, 8);
        mProgressbarBgColor = array.getColor(R.styleable.CircularProgressBar_progressbarBackgroundColor, ContextCompat.getColor(context, R.color.teal_700));
        mProgressColor = array.getColor(R.styleable.CircularProgressBar_progressbarColor, ContextCompat.getColor(context, R.color.teal_200));
        mMaxProgress = array.getInt(R.styleable.CircularProgressBar_maxProgress, 100);
        mCurrentProgress = array.getInt(R.styleable.CircularProgressBar_progress, 0);
        String text = array.getString(R.styleable.CircularProgressBar_text);
        mText = text == null ? "" : text;
        mTextColor = array.getColor(R.styleable.CircularProgressBar_textColor, ContextCompat.getColor(context, R.color.black));
        mTextSize = array.getDimensionPixelSize(R.styleable.CircularProgressBar_textSize, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));

        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.UNSPECIFIED://wrap_content
            case MeasureSpec.AT_MOST:
                width = mRadius * 2;
                break;
            case MeasureSpec.EXACTLY://match_parent
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;

        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      int centerX=  getWidth()/2;
        RectF rectF = new RectF();
        rectF.left = mStrokeWidth;
        rectF.top = mStrokeWidth;
        rectF.right = centerX * 2 - mStrokeWidth;
        rectF.bottom = centerX * 2 - mStrokeWidth;
        //绘制进度条背景
        drawProgressbarBg(canvas, rectF);
        //绘制进度
        drawProgress(canvas, rectF);

    }

    /**
     * 绘制进度条背景
     * @param canvas
     * @param rectF
     */
    private void drawProgressbarBg(Canvas canvas,RectF rectF){
        Paint mPaint=new Paint();
        //画笔的填充样式，Paint.Style.STROKE 描边
        mPaint.setStyle(Paint.Style.STROKE);
        //圆弧的宽度
        mPaint.setStrokeWidth(mStrokeWidth);
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(mProgressbarBgColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawArc(rectF,mStartAngle,mEndAngle,false,mPaint);
    }

    /**
     * 绘制进度
     * @param canvas
     * @param rectF
     */
    private void drawProgress(Canvas canvas,RectF rectF){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setColor(mProgressColor);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        if (!isAnimation){
            mCurrentAngle=360*(mCurrentProgress/mMaxProgress);
        }
        canvas.drawArc(rectF,mStartAngle,mCurrentAngle,false,paint);
    }
}
