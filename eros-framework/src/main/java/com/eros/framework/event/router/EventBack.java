package com.eros.framework.event.router;

import android.content.Context;

import com.eros.framework.constant.WXConstant;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.RouterManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;
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
