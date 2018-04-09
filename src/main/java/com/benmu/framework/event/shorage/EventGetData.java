package com.benmu.framework.event.shorage;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.StorageManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.utils.JsPoster;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;

/**
 * Created by Carry on 2017/5/21.
 */

public class EventGetData extends EventGate{

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        getData(context, weexEventBean.getParamsList(), weexEventBean
                .getJscallback());
    }

    public void getData(Context context, ArrayList<String> paramsList, JSCallback jscallback) {
        String key = paramsList.get(0);
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        String result = storageManager.getData(context, key);
        if (TextUtils.isEmpty(result)) {
            JsPoster.postFailed(jscallback);
        } else {
            JsPoster.postSuccess(result, jscallback);
        }
    }

    public Object getDataSync(Context context, ArrayList<String> list) {
        String key = list.get(0);
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        String result = storageManager.getData(context, key);
        return result == null ? JsPoster.getFailed() : JsPoster.getSuccess(result);
    }
}
