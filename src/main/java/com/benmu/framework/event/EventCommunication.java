package com.benmu.framework.event;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.CommunicationManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.AxiosResultBean;
import com.benmu.framework.model.UploadResultBean;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

import java.util.List;

/**
 * Created by liuyuanxiao on 17/12/29.
 */

public class EventCommunication {
    private JSCallback mContactsCallBack;

    public void sms(String params, final Context context) {
        JSONObject jsonObject = (JSONObject) JSONObject.parse(params);
        List<String> rec = JSON.parseArray(jsonObject.getString("to"), String.class);
        StringBuilder smsList = new StringBuilder();
        for (int i = 0; i < rec.size(); i++) {
            if (i > 0) {
                smsList.append(",");
            }
            smsList.append(rec.get(i));
        }
        String content = jsonObject.get("content").toString();
        CommunicationManager routerManager = ManagerFactory.getManagerService(CommunicationManager.class);
        routerManager.sms(smsList.toString(), content, context);
    }

    public void contacts(final Context context,JSCallback callback) {
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
        CommunicationManager routerManager = ManagerFactory.getManagerService(CommunicationManager.class);
        routerManager.contacts(context);
    }

    @Subscribe
    public void contactsResult(AxiosResultBean uploadResultBean) {
        if (uploadResultBean != null && mContactsCallBack != null) {
            mContactsCallBack.invoke(uploadResultBean);
        }
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }
}
