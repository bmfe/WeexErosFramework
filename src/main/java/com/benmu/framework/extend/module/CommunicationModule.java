package com.benmu.framework.extend.module;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by liuyuanxiao on 17/12/29.
 */

public class CommunicationModule extends WXModule {
    @JSMethod
    public void call(String params) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_CALL);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJsParams(params);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod
    public void sms(String recipients, String params, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_COMMUNICATION_SMS);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJsParams(params);
        weexEventBean.setExpand(recipients);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod
    public void contacts(JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_COMMUNICATION_CONTACTS);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }
}
