package com.eros.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.eros.framework.R;

/**
 * Created by liuyuanxiao on 2018/6/21.
 */

public class CircleTextView extends View {

    private static final String TAG = CircleTextView.class.getSimpleName();
    /**
     * 文本
     */
    private String titleText = "";
    /**
     * 字体颜色
     */
    private int titleColor = Color.BLACK;
    /**
     * 背景颜色,设置默认颜色
     */
    private int backColor = Color.GRAY;

    /**
     * 色值
     */
    private int titleSize;

    /**
     * 控制文本的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircleView, defStyleAttr, 0);
        /**
         * 这个获得是你实际在layout中设置的属性个数
         */
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CustomCircleView_titleText) {
                titleText = a.getString(attr);

            } else if (attr == R.styleable.CustomCircleView_titleTextColor) {
                titleColor = a.getColor(attr, Color.BLACK);

            } else if (attr == R.styleable.CustomCircleView_backColor) {
                backColor = a.getColor(attr, Color.GREEN);

            } else if (attr == R.styleable.CustomCircleView_titleTextSize) {
                titleSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

            }
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(titleSize);
        mBound = new Rect();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint.getTextBounds(titleText, 0, titleText.length(), mBound);
    }


    public void setText(String text) {
        int textInt = 0;
        if (!TextUtils.isEmpty(text.trim())) {
            try {
                textInt = Integer.parseInt(text);
            } catch (Exception e) {
                Log.e(TAG, "setText() error text not int , text is -> " + text);
            }
        }
        if (textInt == 0) {
            setVisibility(GONE);
        } else if (textInt == 1) {
            setVisibility(VISIBLE);
        } else if (textInt >= 100) {
            setVisibility(VISIBLE);
            text = "⋅⋅⋅";
        }
        titleText = text;
        invalidate();
    }


    public void setTextColor(int titleColor) {
        this.titleColor = titleColor;
        invalidate();
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
        invalidate();
    }

    public void setViewColor(int titleColor, int backColor) {
        this.backColor = backColor;
        this.titleColor = titleColor;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");
        //圆的半径
        int r = Math.min(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        //圆心的横坐标
        int centerX = r;
        //圆心的纵坐标
        int centerY = r;
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        mPaint.setColor(backColor);
        //绘制圆
        canvas.drawCircle(centerX, centerY, r, mPaint);
        mPaint.setColor(titleColor);

        mPaint.setTextSize(titleSize);
        /**
         * 获得绘制文本的宽和高
         */
        mPaint.getTextBounds(titleText, 0, titleText.length(), mBound);
        int x = getWidth() / 2 - mBound.width() / 2 - 3;
        int y = titleText.equals("⋅⋅⋅") ? (int) (getHeight() * 0.7) : getHeight() / 2 + mBound.height() / 2;
        //绘制Text
        canvas.drawText(titleText, x, y, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 计算相应的宽高
     *
     * @param defaultSize 默认值
     * @param measureSpec
     * @return
     */
    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将自己计算View的大小
                mPaint.setTextSize(titleSize);
                mPaint.getTextBounds(titleText, 0, titleText.length(), mBound);
                float textWidth = mBound.width();
                int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
                mySize = desired;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    public void setTitleText(String str) {
        titleText = str;
        invalidate();
    }

}
