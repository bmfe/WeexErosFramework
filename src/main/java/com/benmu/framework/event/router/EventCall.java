package com.benmu.framework.event.router;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventCall extends EventGate{
    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        call(weexEventBean.getJsParams(),context);
    }

    public void call(String params, Context context) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        routerManager.dialing(context,params);
    }
}
