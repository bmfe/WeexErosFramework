package com.eros.framework.extend.module;


import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.constant.WXConstant;

/**
 * Created by liuyuanxiao on 18/1/3.
 */

public class ImageModule extends WXModule {

    @JSMethod
    public void uploadImage(String params, JSCallback jsCallback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_CAMERA_UPLOADIMAGE);
        eventBean.setJsParams(params);
        eventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

    @JSMethod(uiThread = true)
    public void uploadScreenshot(JSCallback callback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_CAMERA_UPLOADSCREENSHOT);
        eventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

    @JSMethod
    public void camera(String params, JSCallback jsCallback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_CAMERA_PATH);
        eventBean.setJsParams(params);
        eventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

    @JSMethod(uiThread = true)
    public void preview(String json) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_BROWSERIMG);
        eventBean.setJsParams(json);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

    @JSMethod(uiThread = true)
    public void pick(String params, JSCallback jsCallback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_IMAGE_PICK);
        eventBean.setJsParams(params);
        eventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

    @JSMethod
    public void scanImage(String params, JSCallback jsCallback){
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(WXEventCenter.EVENT_IMAGE_SCAN);
        eventBean.setJsParams(params);
        eventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(eventBean);
    }

}
