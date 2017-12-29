package com.benmu.framework.event.router;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.BaseResultBean;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/12/21.
 */

public class EventOpenBrowser {

    public void openBrowser(Context context, String params, JSCallback callback) {
        RouterManager managerService = ManagerFactory.getManagerService(RouterManager.class);
        boolean b = managerService.openBrowser(context, params);
        BaseResultBean baseResultBean = new BaseResultBean();
        if (b) {
            baseResultBean.resCode = 0;
        } else {
            baseResultBean.resCode = 9;
        }
        if (callback != null) {
            callback.invoke(baseResultBean);
        }
    }
}
