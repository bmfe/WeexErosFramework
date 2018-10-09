package com.eros.framework.extend.module;

import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.event.shorage.EventDeleteData;
import com.eros.framework.event.shorage.EventGetData;
import com.eros.framework.event.shorage.EventRemoveData;
import com.eros.framework.event.shorage.EventSetData;
import com.eros.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.constant.WXConstant;

import java.util.ArrayList;

/**
 * Created by Carry on 17/2/9.
 */

public class StorageModule extends WXModule {

    @JSMethod(uiThread = true)
    public Object setData(String key, String value, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_SETDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String> list = new ArrayList();
        list.add(key);
        list.add(value);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;
    }

    @JSMethod(uiThread = true)
    public Object getData(String key, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_GETDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String> list = new ArrayList();
        list.add(key);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;
    }

    @JSMethod(uiThread = true)
    public Object deleteData(String key, JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_DELETEDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String> list = new ArrayList();
        list.add(key);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;

    }

    @JSMethod(uiThread = true)
    public Object removeData(JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_REMOVEDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;
    }


    //同步存取
    @JSMethod(uiThread = false)
    public Object setDataSync(String key, String value) {
        ArrayList<String> list = new ArrayList();
        list.add(key);
        list.add(value);
        return new EventSetData().setDataSync(mWXSDKInstance.getContext(), list);
    }


    @JSMethod(uiThread = false)
    public Object getDataSync(String key) {
        ArrayList<String> list = new ArrayList();
        list.add(key);
        return new EventGetData().getDataSync(mWXSDKInstance.getContext(), list);
    }

    @JSMethod(uiThread = false)
    public Object deleteDataSync(String key) {
        ArrayList<String> list = new ArrayList();
        list.add(key);
        return new EventDeleteData().deleteDataSync(mWXSDKInstance.getContext(), list);
    }

    @JSMethod(uiThread = false)
    public Object removeDataSync() {
        return new EventRemoveData().removeDataSync(mWXSDKInstance.getContext());
    }


}
