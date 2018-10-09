package com.eros.framework.extend.hook.ui.view.refresh.loadmore;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.eros.framework.R;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;

import java.util.HashMap;
import java.util.Map;


public class LoadingLoadMore extends BaseLoadMore {
    private ImageView iv_loading;
    private AnimationDrawable animation;
    private long mStartTime;


    public LoadingLoadMore(Context context, WXComponent component) {
        super(context, component);
        initView();
    }

    @Override
    public void onLoadComplete() {
        //停止动画
        long mEndTime = System.currentTimeMillis();
        long timeDiff = mEndTime - mStartTime;
        if (timeDiff < 1000) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hide();
                }
            }, 1000 - timeDiff);
        } else {
            hide();
        }
    }

    private void hide() {
        BounceRecyclerView recyclerView = (BounceRecyclerView) mWeakReference.get().getHostView();
        recyclerView.finishPullLoad();
        recyclerView.onLoadmoreComplete();
        mCurrentState = STATE_IDLE;
        animation.stop();
        iv_loading.setImageResource(R.drawable.sdk_bmloading);
    }


    private void initView() {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View container = mInflater.inflate(R.layout.sdk_layout_bmloading, this, true);
        iv_loading = (ImageView) container.findViewById(R.id.iv_loading);
        iv_loading.setImageResource(R.drawable.sdk_bmloading);
    }


    @Override
    public void onLoading() {
        if (mCurrentState != STATE_IDLE) return;
        mCurrentState = STATE_REFRESHING;
        mStartTime = System.currentTimeMillis();
        if (iv_loading != null) {
            animation = (AnimationDrawable) iv_loading.getDrawable();
            animation.start();
        }
        if (mWeakReference != null && mWeakReference.get() != null) {
            mWeakReference.get().fireEvent(Constants.Event.ONLOADMORE);
        }
    }

    @Override
    public void onPullingUp(float dy, int pullOutDistance, float viewHeight) {
        if (mWeakReference != null && mWeakReference.get() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put(Constants.Name.DISTANCE_Y, dy);
            data.put(Constants.Name.PULLING_DISTANCE, pullOutDistance);
            data.put(Constants.Name.VIEW_HEIGHT, viewHeight);
            mWeakReference.get().fireEvent(Constants.Event.ONPULLING_UP, data);
        }
    }
}
