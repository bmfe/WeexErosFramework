package com.eros.framework.event.shorage;

import android.content.Context;

import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.StorageManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.JsPoster;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/5/21.
 */

public class EventRemoveData extends EventGate{

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        removeData(context, weexEventBean.getJscallback());
    }

    public void removeData(Context context, JSCallback jscallback) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        boolean result = storageManager.removeData(context);

        if (result) {
            JsPoster.postSuccess(null, jscallback);
        } else {
            JsPoster.postFailed(jscallback);
        }

    }

    public Object removeDataSync(Context context) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        boolean result = storageManager.removeData(context);
        return result ? JsPoster.getSuccess(null) : JsPoster.getFailed();
    }
}

