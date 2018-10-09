package com.eros.framework.extend.module;


import com.eros.framework.constant.WXConstant;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;


/**
 * Created by Carry on 17/1/16.
 */

public class AxiosModule extends WXModule {

    @JSMethod(uiThread = false)
    public void fetch(String params, final JSCallback jsCallback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(   WXEventCenter.EVENT_FETCH);
        eventBean.setJsParams(params);
        eventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                (eventBean);
    }
    @JSMethod
    public void uploadImage(String params, final JSCallback jsCallback) {
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setContext(mWXSDKInstance.getContext());
        eventBean.setKey(   WXEventCenter.EVENT_IMAGE_UPLOAD);
        eventBean.setJsParams(params);
        eventBean.setJscallback(jsCallback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                (eventBean);
    }

}
