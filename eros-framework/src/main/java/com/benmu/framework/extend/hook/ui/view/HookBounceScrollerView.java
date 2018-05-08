package com.benmu.framework.extend.hook.ui.view;

import android.content.Context;
import android.view.View;

import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView;
import com.taobao.weex.utils.WXViewUtils;

/**
 * Created by Carry on 2018/4/20.
 */

public class HookBounceScrollerView extends BounceScrollerView {
    public HookBounceScrollerView(Context context, int orientation, WXScroller waScroller) {
        super(context, orientation, waScroller);
    }


    //benmu.org
    public void setCustomHeaderView(View view) {
        setRefreshEnable(true);
        swipeLayout.setRefreshHeight(WXViewUtils.dip2px(40));
        swipeLayout.getHeaderView().setRefreshView(view);
    }
    //benmu.org

}
