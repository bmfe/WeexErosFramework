package com.benmu.framework.event.nav;

import android.content.Context;

import com.benmu.framework.adapter.DefaultNavigationAdapter;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/13.
 */

public class EventLeftItem extends EventGate{

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        setLeftItem(weexEventBean.getJsParams(), weexEventBean.getJscallback());
    }

    public void setLeftItem(String params, JSCallback jscallback) {
        DefaultNavigationAdapter.setLeftItem(params,jscallback);
    }
}
