package com.eros.framework.event.router;

import android.content.Context;

import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.RouterManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.JsPoster;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/12/21.
 */

public class EventOpenBrowser extends EventGate{

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        openBrowser(context, weexEventBean.getJsParams(), weexEventBean.getJscallback());
    }

    public void openBrowser(Context context, String params, JSCallback callback) {
        RouterManager managerService = ManagerFactory.getManagerService(RouterManager.class);
        boolean b = managerService.openBrowser(context, params);
        if (b) {
            JsPoster.postSuccess(null,callback);
        } else {
            JsPoster.postFailed(callback);
        }
    }
}
