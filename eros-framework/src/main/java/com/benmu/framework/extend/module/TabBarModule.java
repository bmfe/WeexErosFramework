package com.benmu.framework.extend.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.activity.MainActivity;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
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

    @JSMethod(uiThread = true)
    public int getIndex() {
        if (mWXSDKInstance.getContext() instanceof MainActivity) {
            return ((MainActivity) mWXSDKInstance.getContext()).getPageIndex();
        }
        return -1;
    }

    @JSMethod(uiThread = true)
    public String getTabbarInfo(String params) {
        return JSON.toJSONString(BMWXEnvironment.mPlatformConfig.getTabBar());
    }


    @JSMethod(uiThread = true)
    public void setTabbarInfo(String params) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setJsParams(params);
        weexEventBean.setKey(WXEventCenter.EVENT_TABBAR_OPENPAGE);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void watchIndex(JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_TABBAR_WATCHINDEX);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void clearTabbarInfo() {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_TABBAR_CLEARTABBARINFO);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }
}
