package com.eros.framework.extend.module;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.eros.framework.BMWXApplication;
import com.eros.framework.activity.AbstractWeexActivity;
import com.eros.framework.activity.MainActivity;
import com.eros.framework.adapter.DefaultNavigationAdapter;
import com.eros.framework.constant.Constant;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.StorageManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.SharePreferenceUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.constant.WXConstant;

/**
 * Created by Carry on 17/1/12.
 */

public class TitleModule extends WXModule {


    @JSMethod(uiThread = true)
    public void set(String data) {
    }

    @JSMethod(uiThread = true)
    public void setRightItem(String json, final JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_RIGHTITEM);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        weexEventBean.setJsParams(json);
        weexEventBean.setExpand(mWXSDKInstance.hashCode());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void setLeftItem(String json, final JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_LEFTITEM);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        weexEventBean.setJsParams(json);
        weexEventBean.setExpand(mWXSDKInstance.hashCode());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }


    @JSMethod(uiThread = true)
    public void setNavigationInfo(String json, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_NAVIGATIONINFO);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        weexEventBean.setJsParams(json);
        weexEventBean.setExpand(mWXSDKInstance.hashCode());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);

    }

    @JSMethod(uiThread = true)
    public void setCenterItem(String json, final JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_CENTERITEM);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        weexEventBean.setJsParams(json);
        weexEventBean.setExpand(mWXSDKInstance.hashCode());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod(uiThread = true)
    public void statusBarHidden(String param) {
        boolean isFullScreen = false;
        try {
            isFullScreen = Boolean.parseBoolean(param);
        } catch (Exception e) {
            Log.d("jsLog", "bmNavigator statusBarHidden() param is not Boolean --> " + param);
        }
        Context context = mWXSDKInstance.getContext();
        if (context instanceof AbstractWeexActivity) {
            BMWXApplication.getWXApplication().IS_FULL_SCREEN = isFullScreen;
            ((AbstractWeexActivity) context).statusBarHidden(isFullScreen);
        }
    }
}
