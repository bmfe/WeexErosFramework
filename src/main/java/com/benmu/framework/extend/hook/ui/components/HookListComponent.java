package com.benmu.framework.extend.hook.ui.components;

import android.content.Context;
import android.util.Log;

import com.benmu.framework.extend.hook.HookConstants;
import com.benmu.framework.extend.hook.ui.view.HookBounceRecyclerView;
import com.benmu.framework.extend.hook.ui.view.refresh.bmrefresh.BMBaseRefresh;
import com.benmu.framework.extend.hook.ui.view.refresh.bmrefresh.BMLoadingRefresh;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
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

    public HookListComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String
            instanceId, boolean isLazy) {
        super(instance, dom, parent, instanceId, isLazy);
        Log.e(TAG, TAG + "init");
    }

    public HookListComponent(WXSDKInstance instance, WXDomObject node, WXVContainer parent,
                             boolean lazy) {
        super(instance, node, parent, lazy);
        Log.e(TAG, TAG + "init");
    }


    @WXComponentProp(name = HookConstants.NAME.SHOW_REFRESH)
    public void setBMRefresh(String showRefresh) {
        this.mAddCustomRefresh = WXUtils.getBoolean(showRefresh, false);
    }


    @Override
    protected BounceRecyclerView generateListView(Context context, int orientation) {
        BounceRecyclerView bounceRecyclerView = new HookBounceRecyclerView(context,mLayoutType,mColumnCount,mColumnGap,orientation);
        if(bounceRecyclerView.getSwipeLayout()  != null){
            if(WXUtils.getBoolean(getDomObject().getAttrs().get(Constants.Name.NEST_SCROLLING_ENABLED), false)) {
                bounceRecyclerView.getSwipeLayout().setNestedScrollingEnabled(true);
            }
        }
        return  bounceRecyclerView;
    }

    @Override
    public void addChild(WXComponent child, int index) {
        super.addChild(child, index);
        addCustomRefresh();
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

}
