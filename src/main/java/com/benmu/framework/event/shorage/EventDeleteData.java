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

public class EventDeleteData {


    public void deleteData(Context context, ArrayList<String> paramsList, JSCallback jscallback) {
        String key = paramsList.get(0);
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        boolean result = storageManager.deleteData(context, key);
        BaseResultBean bean = new BaseResultBean();
        if (result) {
            bean.resCode = 0;
        } else {
            bean.resCode = 9;
            bean.msg = "删除" + key + "失败";
        }
        if (jscallback != null) {
            jscallback.invoke(bean);
        }
    }
}
