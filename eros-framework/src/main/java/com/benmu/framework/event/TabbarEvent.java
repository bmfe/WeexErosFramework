package com.benmu.framework.event;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.activity.MainActivity;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.TabbarBadgeModule;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;

/**
 * Created by liuyuanxiao on 2018/6/21.
 */

public class TabbarEvent extends EventGate {

    public void perform(Context context, WeexEventBean eventBean, String type) {
        if (WXEventCenter.EVENT_TABBAR_SHOWBADGE.equals(type)) {
            showBadge(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_HIDBADGE.equals(type)) {
            hideBadge(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_OPENPAGE.equals(type)) {
            openPage(eventBean);
        }

    }


    public void showBadge(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        TabbarBadgeModule module = parseManager.parseObject(weexEventBean.getJsParams(), TabbarBadgeModule.class);
        if (context instanceof MainActivity) {
            ((MainActivity) context).setBadge(module);
        }
    }

    public void hideBadge(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        String index = JSON.parseObject(weexEventBean.getJsParams()).get("index").toString();
        if (context instanceof MainActivity) {
            ((MainActivity) context).hideBadge(Integer.parseInt(index));
        }
    }

    public void openPage(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        String index = JSON.parseObject(weexEventBean.getJsParams()).get("index").toString();
        if (context instanceof MainActivity) {
            ((MainActivity) context).openPage(Integer.parseInt(index));
        }
    }
}
