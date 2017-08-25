package com.benmu.framework.extend.module;

import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.ArrayList;

/**
 * Created by Carry on 17/2/9.
 */

public class StorageModule extends WXModule {
    @JSMethod
    public void setData(String key, String value, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_SETDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String>list=new ArrayList();
        list.add(key);
        list.add(value);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod
    public void getData(String key, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_GETDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String>list=new ArrayList();
        list.add(key);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod
    public void deleteData(String key, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_DELETEDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String>list=new ArrayList();
        list.add(key);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);

    }

    @JSMethod
    public void removeData(JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_REMOVEDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
}}
