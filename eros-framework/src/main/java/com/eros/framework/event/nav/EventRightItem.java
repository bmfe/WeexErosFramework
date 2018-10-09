package com.eros.framework.event.nav;

import android.content.Context;

import com.eros.framework.adapter.DefaultNavigationAdapter;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/13.
 */

public class EventRightItem extends NavigationEventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        if (!setNavitionListenter(weexEventBean)) {
            setRightItem(weexEventBean.getJsParams(), weexEventBean.getJscallback());
        }
    }

    public void setRightItem(String params, JSCallback jscallback) {
        DefaultNavigationAdapter.setRightItem(params, jscallback);
    }
}
