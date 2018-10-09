package com.eros.framework.extend.module;

import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.AppUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

public class UpdateModule extends WXModule {

    @JSMethod
    public void download(String params, JSCallback jsCallback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_DOWNLOAD_BUNDLE);
        weexEventBean.setJsParams(params);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod
    public void update() {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_UPDATE_BUNDLE);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void getJsVersion(JSCallback jsCallback) {
        if (jsCallback != null) {
            jsCallback.invoke(AppUtils.getJsVersion(mWXSDKInstance.getContext()));
        }
    }

}
