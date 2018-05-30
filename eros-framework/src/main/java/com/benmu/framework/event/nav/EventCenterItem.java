package com.benmu.framework.event.nav;

import android.content.Context;

import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.DefaultNavigationAdapter;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/13.
 */

public class EventCenterItem extends NavigationEventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        if (!setNavitionListenter(weexEventBean)) {
            setCenterItem(weexEventBean.getJsParams(), weexEventBean.getJscallback());
        }
    }

    public void setCenterItem(String params, JSCallback jscallback) {
        DefaultNavigationAdapter.setCenterItem(params, jscallback);
    }
}
