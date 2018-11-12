package com.eros.framework.extend.hook.ui.components;

import android.content.Context;
import android.util.Log;

import com.eros.framework.extend.hook.HookConstants;
import com.eros.framework.extend.hook.ui.view.HookBounceRecyclerView;
import com.eros.framework.extend.hook.ui.view.refresh.bmrefresh.BMBaseRefresh;
import com.eros.framework.extend.hook.ui.view.refresh.bmrefresh.BMLoadingRefresh;
import com.eros.framework.extend.hook.ui.view.refresh.loadmore.BaseLoadMore;
import com.eros.framework.extend.hook.ui.view.refresh.loadmore.LoadingLoadMore;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.list.WXListComponent;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.utils.WXUtils;

/**
 * Created by Carry on 2018/4/17.
 */

public class HookListComponent extends WXListComponent {
    private String TAG = getClass().getName();
    private boolean mAddCustomRefresh;
    private BMBaseRefresh mBMRefresh;
    private boolean mAddCustomload;
    private BaseLoadMore mload;

    public HookListComponent(WXSDKInstance instance, WXVContainer parent, String
            instanceId, boolean isLazy, BasicComponentData basicComponentData) {
        super(instance, parent, instanceId, isLazy,basicComponentData);
        Log.e(TAG, TAG + "init");
    }

    public HookListComponent(WXSDKInstance instance, WXVContainer parent,
                             boolean lazy, BasicComponentData basicComponentData) {
        super(instance, parent, lazy,basicComponentData);
        Log.e(TAG, TAG + "init");
    }


    @WXComponentProp(name = HookConstants.NAME.SHOW_REFRESH)
    public void setBMRefresh(String showRefresh) {
        this.mAddCustomRefresh = WXUtils.getBoolean(showRefresh, false);
    }

    @WXComponentProp(name = HookConstants.NAME.SHOW_LOADMORE)    // iCoastline 下拉加载更多
    public void setLoadMore(String showLoadMore) {
        this.mAddCustomload = WXUtils.getBoolean(showLoadMore, false);
    }


    @Override
    protected BounceRecyclerView generateListView(Context context, int orientation) {
        BounceRecyclerView bounceRecyclerView = new HookBounceRecyclerView(context,mLayoutType,mColumnCount,mColumnGap,orientation);
        if(bounceRecyclerView.getSwipeLayout()  != null){
            if(WXUtils.getBoolean(getAttrs().get(Constants.Name.NEST_SCROLLING_ENABLED), false)) {
                bounceRecyclerView.getSwipeLayout().setNestedScrollingEnabled(true);
            }
        }
        return  bounceRecyclerView;
    }

    @Override
    public void addChild(WXComponent child, int index) {
        super.addChild(child, index);
        addCustomRefresh();
        addCustomLoadMore();
    }


    public void addCustomRefresh() {
        if (!mAddCustomRefresh || mBMRefresh != null) return;
        mBMRefresh = new BMLoadingRefresh(getContext(), this);
        getHostView().setOnRefreshListener(mBMRefresh);
        getHostView().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getHostView() instanceof HookBounceRecyclerView){
                    ((HookBounceRecyclerView)getHostView()).setCustomHeaderView(mBMRefresh);
                }
            }
        }, 100);
    }

    @JSMethod
    public void refreshEnd() {
        if (mBMRefresh != null && mBMRefresh.mCurrentState == BMBaseRefresh.STATE_REFRESHING) {
            mBMRefresh.onRefreshComplete();
        }
    }

    public void addCustomLoadMore() {    // iCoastline 下拉加载更多
        if (!mAddCustomload || mload != null) return;
        mload = new LoadingLoadMore(getContext(), this);
        getHostView().setOnLoadingListener(mload);
        getHostView().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getHostView() instanceof HookBounceRecyclerView){
                    ((HookBounceRecyclerView)getHostView()).setCustomFootView(mload);
                }
            }
        }, 100);
    }

    @JSMethod
    public void loadMoreEnd() {    // iCoastline 下拉加载更多
        if (mload != null && mload.mCurrentState == LoadingLoadMore.STATE_REFRESHING) {
            mload.onLoadComplete();
        }
    }

}
