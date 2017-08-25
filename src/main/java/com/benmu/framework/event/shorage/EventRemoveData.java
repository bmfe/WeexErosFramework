package com.benmu.framework.event.shorage;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.StorageManager;
import com.benmu.framework.model.BaseResultBean;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/5/21.
 */

public class EventRemoveData {
    public void removeData(Context context, JSCallback jscallback) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        boolean result = storageManager.removeData(context);

        BaseResultBean bean = new BaseResultBean();
        if (result) {
            bean.resCode = 0;
        } else {
            bean.resCode = 9;
            bean.msg = "删除失败";
        }

        if (jscallback != null) {
            jscallback.invoke(bean);
        }
    }
}

