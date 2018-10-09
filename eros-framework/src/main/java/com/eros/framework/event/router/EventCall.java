package com.eros.framework.event.router;

import android.content.Context;

import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.RouterManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;

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
