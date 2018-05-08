package com.benmu.framework.event.router;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventGetParams extends EventGate{
    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        getParams(context, weexEventBean.getJscallback());
    }

    public void getParams(Context context, JSCallback jscallback) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        RouterModel routerModel = routerManager.getParams(context);
        if (routerModel != null && jscallback != null) {
            jscallback.invoke(routerModel.params);
        }
    }
}
