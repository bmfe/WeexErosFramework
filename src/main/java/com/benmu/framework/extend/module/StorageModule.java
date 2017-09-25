package com.benmu.framework.extend.module;

import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.event.shorage.EventDeleteData;
import com.benmu.framework.event.shorage.EventGetData;
import com.benmu.framework.event.shorage.EventRemoveData;
import com.benmu.framework.event.shorage.EventSetData;
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

    @JSMethod(uiThread = false)
    public Object setData(String key, String value, JSCallback callback) {
        if (callback == null) {
            return setSync(key, value);
        }
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_SETDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String> list = new ArrayList();
        list.add(key);
        list.add(value);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;
    }

    private Object setSync(String key, String value) {
        ArrayList<String> list = new ArrayList();
        list.add(key);
        list.add(value);
        return new EventSetData().setDataSync(mWXSDKInstance.getContext(), list);
    }


    @JSMethod(uiThread = false)
    public Object getData(String key, JSCallback callback) {
        if (callback == null) {
            return getSync(key);
        }
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_GETDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String> list = new ArrayList();
        list.add(key);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;
    }

    private Object getSync(String key) {
        ArrayList<String> list = new ArrayList();
        list.add(key);
        return new EventGetData().getDataSync(mWXSDKInstance.getContext(), list);
    }

    @JSMethod(uiThread = false)
    public Object deleteData(String key, JSCallback callback) {
        if (callback == null) {
            return deleteSync(key);
        }
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_DELETEDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        ArrayList<String> list = new ArrayList();
        list.add(key);
        weexEventBean.setParamsList(list);
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;

    }

    private Object deleteSync(String key) {
        ArrayList<String> list = new ArrayList();
        list.add(key);
        return new EventDeleteData().deleteDataSync(mWXSDKInstance.getContext(), list);
    }

    @JSMethod(uiThread = false)
    public Object removeData(JSCallback callback) {
        if (callback == null) {
            return removeSync();
        }
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_REMOVEDATA);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
        return null;
    }

    private Object removeSync() {
        return new EventRemoveData().removeDataSync(mWXSDKInstance.getContext());
    }

}
