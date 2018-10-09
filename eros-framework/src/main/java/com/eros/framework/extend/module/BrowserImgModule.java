package com.eros.framework.extend.module;

import com.eros.framework.constant.WXConstant;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * 点击跳转查看大图。
 */

public class BrowserImgModule extends WXModule {

    @JSMethod(uiThread = true)
    public void open(String json) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_BROWSERIMG);
        eventBean.setJsParams(json);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

}
