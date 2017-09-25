package com.benmu.framework.event.shorage;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.StorageManager;
import com.benmu.framework.model.BaseResultBean;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;

/**
 * Created by Carry on 2017/5/21.
 */

public class EventSetData {
    public void setData(Context context, ArrayList<String> paramsList, JSCallback jscallback) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        String key = paramsList.get(0);
        String value = paramsList.get(1);
        boolean result = storageManager.setData(context, key, value);
        BaseResultBean bean = new BaseResultBean();
        if (result) {
            bean.resCode = 0;
        } else {
            bean.resCode = 9;
            bean.msg = "存储失败";
        }
        if (jscallback != null) {
            jscallback.invoke(bean);
        }
    }


    public Object setDataSync(Context context, ArrayList<String> paramsList) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        String key = paramsList.get(0);
        String value = paramsList.get(1);
        boolean result = storageManager.setData(context, key, value);
        BaseResultBean bean = new BaseResultBean();
        if (result) {
            bean.resCode = 0;
        } else {
            bean.resCode = 9;
            bean.msg = "存储失败";
        }
        return bean;
    }
}
