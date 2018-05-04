package com.benmu.framework.event.router;

import android.content.Context;

import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/8/21.
 */

public class EventBack extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        back(weexEventBean.getJsParams(), context, weexEventBean.getJscallback());
    }

    public void back(String params, Context context, JSCallback jscallback) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        boolean result = routerManager.back(context, params);
        if (jscallback != null) {
            jscallback.invoke(result ? WXConstant.BACK_PAGE_SUCCESS : WXConstant.BACK_PAGE_FAILED);
        }
    }
}
