package com.eros.framework.extend.hook.ui.view.refresh.loadmore;

import android.content.Context;
import android.widget.FrameLayout;

import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.refresh.core.WXSwipeLayout;

import java.lang.ref.WeakReference;

/**
 * Created by Carry on 2017/5/10.
 */

public abstract class BaseLoadMore extends FrameLayout implements WXSwipeLayout
        .WXOnLoadingListener {
    protected WeakReference<WXComponent> mWeakReference;
    public static final int STATE_IDLE = 0;
    public static final int STATE_REFRESHING = 1;
    public int mCurrentState = STATE_IDLE;

    public BaseLoadMore(Context context, WXComponent component) {
        super(context);
        if (mWeakReference == null) {
            mWeakReference = new WeakReference<WXComponent>(component);
        }
    }


    public abstract void onLoadComplete();

}