package com.benmu.framework.extend.hook.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.taobao.weex.ui.view.WXHorizontalScrollView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import java.util.Date;
/**
 * Created by Carry on 2018/4/19.
 */

public class HookWXHorizontalScrollView extends WXHorizontalScrollView {
    private boolean mPageEnable = false;
    private int mCurrentPage = 0;
    private int mPageWidth;
    private static final int NEXT = 100;
    private static final int PRE = 101;
    private static final int STAY = 102;
    private static final int THRESHOLD_VELOCITY = 1;
    private int mChildCount;
    private boolean mLock = false;
    private long mDownTimeStamp;
    private WXGesture wxGesture;

    private int mDownX;
    private int mLastMoveX;


    public HookWXHorizontalScrollView(Context context) {
        super(context);
    }

    public HookWXHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isScrollable()) {
            return true; // when scrollable is set to false, then eat the touch event
        }

        if (mPageEnable) {
            handlePageEnable(ev);
        }
        boolean result = mPageEnable;
        if (!mPageEnable) {
            result = super.onTouchEvent(ev);
        }

        if (wxGesture != null) {
            result |= wxGesture.onTouch(this, ev);
        }
        return result;
    }


    private void handlePageEnable(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mockDownEvent(ev);
                if (mLastMoveX == 0) {
                    mLastMoveX = mDownX;
                }
                int moveX = (int) ev.getX();
                scrollBy(mLastMoveX - moveX, 0);
                mLastMoveX = moveX;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                boolean isFiling = false;
                mLastMoveX = 0;
                long upTimeStamp = new Date().getTime();
                float v = Math.abs(ev.getX() - mDownX) / (upTimeStamp - mDownTimeStamp);
                if (v > THRESHOLD_VELOCITY) {
                    isFiling = true;
                }
                if (Math.abs(ev.getX() - mDownX) > mPageWidth / 2 || isFiling) {
                    if (ev.getX() > mDownX) {
                        move(PRE);
                    } else {
                        move(NEXT);
                    }
                } else {
                    move(STAY);
                }
                mLock = false;
                break;
        }
    }

    private void mockDownEvent(MotionEvent ev) {
        if (!mLock) {
            mDownX = (int) ev.getX();
            mDownTimeStamp = new Date().getTime();
            mLock = true;
        }


    }

    private void move(int action) {
        if (NEXT == action) {
            if (mCurrentPage < mChildCount - 1) {
                mCurrentPage++;
            }
        } else if (PRE == action) {
            if (mCurrentPage > 0) {
                mCurrentPage--;
            }
        }
        post(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(mCurrentPage * mPageWidth, 0);
            }
        });

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        onReceiveChild();
    }

    private void onReceiveChild() {
        if (getChildCount() > 0) {
            View firstChild = getChildAt(0);
            if (firstChild != null) {
                int childCount = ((ViewGroup) firstChild).getChildCount();
                if (childCount > 0) {
                    final View child = ((ViewGroup) firstChild).getChildAt(0);
                    ((ViewGroup) firstChild).getChildAt(0).getViewTreeObserver()
                            .addOnGlobalLayoutListener(new ViewTreeObserver
                                    .OnGlobalLayoutListener() {


                                @Override
                                public void onGlobalLayout() {
                                    mPageWidth = child.getWidth();
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        child.getViewTreeObserver().removeOnGlobalLayoutListener
                                                (this);
                                    } else {
                                        child.getViewTreeObserver().removeGlobalOnLayoutListener
                                                (this);
                                    }
                                }
                            });
                }
                mChildCount = childCount;
            }
        }
    }



    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int mCurrentPage) {
        this.mCurrentPage = mCurrentPage;
    }

    public boolean isPageEnable() {
        return mPageEnable;
    }

    public void setPageEnable(boolean mPageEnable) {
        this.mPageEnable = mPageEnable;
    }


}
