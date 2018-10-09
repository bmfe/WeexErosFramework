package com.eros.framework.extend.module;


import android.content.Intent;
import android.content.IntentFilter;

import com.eros.framework.BMWXEnvironment;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.impl.ConnectivityManager;
import com.eros.framework.model.WeexEnvironment;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.receiver.NetworkReceiver;
import com.eros.framework.utils.JsPoster;
import com.eros.framework.utils.NetworkUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.constant.WXConstant;

/**
 * Js交互键盘退出
 */
public class ToolModule extends WXModule {

    @JSMethod
    public void resignKeyboard(JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_RESIGNKEYBOARD);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }


    /**
     * 复制字符串到粘贴板
     */
    @JSMethod
    public void copyString(String params, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_COPYSTRING);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        weexEventBean.setJsParams(params);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void scan(JSCallback callback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_CAMERA);
        eventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

    @JSMethod(uiThread = false)
    public Object networkStatus() {
        return NetworkUtil.status(BMWXEnvironment.mApplicationContext);
    }

    @JSMethod
    public void watchNetworkStatus(JSCallback callback) {
        ConnectivityManager connectivityManager = ManagerFactory.getManagerService
                (ConnectivityManager
                        .class);
        connectivityManager.registerNetworkListener(BMWXEnvironment.mApplicationContext, callback);

    }

    @JSMethod
    public void clearWatchNetworkStatus() {
        ConnectivityManager connectivityManager = ManagerFactory.getManagerService
                (ConnectivityManager
                        .class);
        connectivityManager.unregisterNetworkListener(BMWXEnvironment.mApplicationContext);
    }
}
