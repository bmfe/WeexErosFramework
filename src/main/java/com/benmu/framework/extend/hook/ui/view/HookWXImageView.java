package com.benmu.framework.extend.hook.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.ViewGroup;

import com.taobao.weex.ui.view.WXImageView;
import com.taobao.weex.utils.ImageDrawable;

import java.util.Arrays;

/**
 * Created by Carry on 2018/4/17.
 */

public class HookWXImageView extends WXImageView {

    private float[] borderRadius;
    public HookWXImageView(Context context) {
        super(context);
    }


    @Override
    public void setBorderRadius(float[] borderRadius) {
        this.borderRadius = borderRadius;
    }

    /**
     * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝添加方法＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
     **/

    public void setCurrentUrl(String url) {
        this.mCurrentUrl = url;
    }

    public String getCurrentUrl() {
        return mCurrentUrl;
    }

    public Drawable getRoundDrawable(Bitmap bitmap) {
        if (bitmap == null) return null;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) return null;
        Drawable wrapDrawable = ImageDrawable.createImageDrawable(new BitmapDrawable
                        (getResources(), bitmap),
                getScaleType(), borderRadius,
                layoutParams.width - getPaddingLeft() - getPaddingRight(),
                layoutParams.height - getPaddingTop() - getPaddingBottom(),
                false);
        if (wrapDrawable instanceof ImageDrawable) {
            ImageDrawable imageDrawable = (ImageDrawable) wrapDrawable;
            if (!Arrays.equals(imageDrawable.getCornerRadii(), borderRadius)) {
                imageDrawable.setCornerRadii(borderRadius);
            }
            return imageDrawable;
        }
        return null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShowing) {
            drawLoading(canvas);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            }, 100);
        }
        if (mDrawError && mErrorBitmap != null) {
            canvas.drawBitmap(mErrorBitmap, getMeasuredWidth() / 2 - mErrorBitmap.getWidth() / 2,
                    getMeasuredHeight() / 2 - mErrorBitmap.getHeight() / 2, mPaint);
        }

    }

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private int mWidthRect;
    private int mHeightRect;
    private int r = 30;
    private boolean mShowing = false;
    private boolean mDrawError = false;
    private Rect mRect;
    private int pos = 0;
    private String[] color = {"#bbbbbb", "#aaaaaa", "#999999", "#888888", "#777777", "#666666"};
    private Handler mHandler = new Handler();
    private Bitmap mErrorBitmap;
    private int max = 300;
    private int mEnsrueLength;
    private int mLoadingPadding;
    private String mCurrentUrl;
    /**
     * 展示菊花的方法
     */
    public void showLoading(int width, int height) {
        mWidth = width;
        mHeight = height;
        mEnsrueLength = Math.min(mWidth, mHeight);
        if (mEnsrueLength > max) {
            mEnsrueLength = max;
        }
        mHeightRect = mEnsrueLength / 5;
        mWidthRect = mHeightRect / 6;
        mLoadingPadding = mEnsrueLength / 5;
        mShowing = true;
        invalidate();
    }

    /**
     * 隐藏菊花
     */

    public void hideLoading() {
        mShowing = false;
        invalidate();
        mHandler.removeCallbacksAndMessages(null);
    }


    public void drawLoading(Canvas canvas) {
        if (mRect == null) {
            mRect = new Rect((mWidth - mWidthRect) / 2, mHeight / 2 - mEnsrueLength / 2 +
                    mLoadingPadding, (mWidth +
                    mWidthRect) /
                    2, mHeight / 2 - mEnsrueLength / 6);
        }
        for (int i = 0; i < 12; i++) {
            if (i - pos >= 5) {
                mPaint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= 0 && i - pos < 5) {
                mPaint.setColor(Color.parseColor(color[i - pos]));
            } else if (i - pos >= -7 && i - pos < 0) {
                mPaint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= -11 && i - pos < -7) {
                mPaint.setColor(Color.parseColor(color[12 + i - pos]));
            }

            canvas.drawRect(mRect, mPaint);  //绘制
            canvas.rotate(30, mWidth / 2, mHeight / 2);    //旋转
        }

        pos++;
        if (pos > 11) {
            pos = 0;
        }
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }


    public void drawErrorBitmap(Bitmap bitmap) {
        this.mErrorBitmap = bitmap;
        mDrawError = true;
        invalidate();
    }

    public void hideErrorBitmap() {
        this.mErrorBitmap = null;
        mDrawError = false;
        invalidate();
    }


    /**＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝结束＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝**/
}
