package com.eros.framework.event.nav;

import android.content.Context;

import com.eros.framework.adapter.DefaultNavigationAdapter;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/13.
 */

public class EventLeftItem extends NavigationEventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        if (!setNavitionListenter(weexEventBean)) {
            setLeftItem(weexEventBean.getJsParams(), weexEventBean.getJscallback());
        }
    }

    public void setLeftItem(String params, JSCallback jscallback) {
        DefaultNavigationAdapter.setLeftItem(params, jscallback);
    }
}
