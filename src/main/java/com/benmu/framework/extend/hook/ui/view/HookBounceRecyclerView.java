package com.benmu.framework.extend.hook.ui.view;

import android.content.Context;
import android.view.View;

import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.utils.WXViewUtils;

/**
 * Created by Carry on 2018/4/17.
 */

public class HookBounceRecyclerView extends BounceRecyclerView {
    public HookBounceRecyclerView(Context context, int type, int columnCount, float columnGap,
                                  int orientation) {
        super(context, type, columnCount, columnGap, orientation);
    }

    public HookBounceRecyclerView(Context context, int type, int orientation) {
        super(context, type, orientation);
    }


    //benmu.org
    public void setCustomHeaderView(View view){
        setRefreshEnable(true);
        swipeLayout.setRefreshHeight(WXViewUtils.dip2px(40));
        swipeLayout.getHeaderView().setRefreshView(view);
    }
    //benmu.org
}
