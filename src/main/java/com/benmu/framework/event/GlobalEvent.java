package com.benmu.framework.event;

import android.app.Activity;
import android.content.Context;

import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.GlobalEventManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.BaseEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.WXSDKInstance;

/**
 * Created by liuyuanxiao on 18/4/9.
 */

public class GlobalEvent extends EventGate {


    @Override
    public void perform(Context context, BaseEventBean eventBean) {
        if (WXEventCenter.EVENT_PUSHMANAGER.equals(eventBean.type)) {
            eventPushMessage(context, eventBean);
        }
    }

    private void eventPushMessage(Context context, BaseEventBean eventBean) {
        Activity activity = RouterTracker.peekActivity();
        if (activity instanceof AbstractWeexActivity) {
            WXSDKInstance instance = ((AbstractWeexActivity) activity).getWXSDkInstance();
            ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
            GlobalEventManager.pushMessage(instance, parseManager.parseObject(eventBean.param));
        }
    }
}
