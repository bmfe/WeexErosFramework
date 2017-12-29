package com.benmu.framework.event.shorage;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.StorageManager;
import com.benmu.framework.model.StorageRaesultBean;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;

/**
 * Created by Carry on 2017/5/21.
 */

public class EventGetData {
    public void getData(Context context, ArrayList<String> paramsList, JSCallback jscallback) {
        String key = paramsList.get(0);
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        String result = storageManager.getData(context, key);
        StorageRaesultBean bean = new StorageRaesultBean();
        if (TextUtils.isEmpty(result)) {
            bean.resCode = 9;
        } else {
            bean.resCode = 0;
        }
        bean.value = result;
        if (jscallback != null) {
            jscallback.invoke(bean);
        }
    }

    public Object getDataSync(Context context, ArrayList<String> list) {
        String key = list.get(0);
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        String result = storageManager.getData(context, key);
        StorageRaesultBean bean = new StorageRaesultBean();
        if (result == null) {
            bean.resCode = 9;
        } else {
            bean.resCode = 0;
        }
        bean.value = result;

        return bean;
    }
}
