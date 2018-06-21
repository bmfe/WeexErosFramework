package com.benmu.framework.extend.module;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * Created by liuyuanxiao on 2018/6/21.
 */
@WeexModule(name = "bmTabbar", lazyLoad = true)
public class TabBarModule extends WXModule {

    @JSMethod(uiThread = true)
    public void showBadge(String params) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_TABBAR_SHOWBADGE);
        weexEventBean.setJsParams(params);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void hideBadge(String params) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_TABBAR_HIDBADGE);
        weexEventBean.setJsParams(params);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void openPage(String params) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setJsParams(params);
        weexEventBean.setKey(WXEventCenter.EVENT_TABBAR_OPENPAGE);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }
}
