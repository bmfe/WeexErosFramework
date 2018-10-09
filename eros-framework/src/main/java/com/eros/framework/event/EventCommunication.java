package com.eros.framework.event;

import android.Manifest;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.CommunicationManager;
import com.eros.framework.manager.impl.PermissionManager;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.AxiosResultBean;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.PermissionUtils;
import com.eros.wxbase.EventGate;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

import java.util.List;

/**
 * Created by liuyuanxiao on 17/12/29.
 */

public class EventCommunication extends EventGate {
    private JSCallback mContactsCallBack;

    @Override
    public void perform(Context context, WeexEventBean weexEventBean, String type) {
        if (WXEventCenter.EVENT_COMMUNICATION_SMS.equals(type)) {
            sms(weexEventBean.getExpand().toString(), weexEventBean.getJsParams(), context);
        } else if (WXEventCenter.EVENT_COMMUNICATION_CONTACTS.equals(type)) {
            contacts(context, weexEventBean.getJscallback());
        }
    }

    public void sms(String recipients, String params, final Context context) {
        List<String> rec = JSON.parseArray(recipients, String.class);
        StringBuilder smsList = new StringBuilder();
        for (int i = 0; i < rec.size(); i++) {
            if (i > 0) {
                smsList.append(",");
            }
            smsList.append(rec.get(i));
        }
        CommunicationManager routerManager = ManagerFactory.getManagerService(CommunicationManager.class);
        routerManager.sms(smsList.toString(), params, context);
    }

    public void contacts(final Context context, JSCallback callback) {
        if (!PermissionUtils.checkPermission(context, Manifest.permission.READ_CONTACTS)) {
            return;
        }
        mContactsCallBack = callback;
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
