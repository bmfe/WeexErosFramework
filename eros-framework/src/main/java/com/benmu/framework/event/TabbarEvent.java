package com.benmu.framework.event;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.benmu.framework.activity.MainActivity;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.StorageManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.TabbarBadgeModule;
import com.benmu.framework.model.TabbarWatchBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by liuyuanxiao on 2018/6/21.
 */

public class TabbarEvent extends EventGate {

    private JSCallback watchCallback;
    private int callBaclKey;

    public void perform(Context context, WeexEventBean eventBean, String type) {
        if (WXEventCenter.EVENT_TABBAR_SHOWBADGE.equals(type)) {
            showBadge(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_HIDBADGE.equals(type)) {
            hideBadge(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_OPENPAGE.equals(type)) {
            openPage(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_SETTABBAR.equals(type)) {
            setTabbar(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_WATCHINDEX.equals(type)) {
            watchIndex(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_CLEARTABBARINFO.equals(type)) {
            clearTabbarInfo(eventBean);
        } else if (WXEventCenter.EVENT_TABBAR_CLEARWATCH.equals(type)) {
            clearWatch(eventBean);
        }

    }


    private void showBadge(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        TabbarBadgeModule module = parseManager.parseObject(weexEventBean.getJsParams(), TabbarBadgeModule.class);
        if (context instanceof MainActivity) {
            ((MainActivity) context).setBadge(module);
        }
    }

    private void hideBadge(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        String index = JSON.parseObject(weexEventBean.getJsParams()).get("index").toString();
        if (context instanceof MainActivity) {
            ((MainActivity) context).hideBadge(Integer.parseInt(index));
        }
    }

    private void openPage(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        String index = JSON.parseObject(weexEventBean.getJsParams()).get("index").toString();
        RouterTracker.clearActivitySurplus(1);
        Activity activity = RouterTracker.peekActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).openPage(Integer.parseInt(index));
        }
    }

    private void setTabbar(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        storageManager.setData(context, Constant.SP.SP_TABBAR_JSON, weexEventBean.getJsParams());
    }

    private void watchIndex(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        watchCallback = weexEventBean.getJscallback();
        this.callBaclKey = Integer.parseInt(weexEventBean.getExpand().toString());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }

    private void clearWatch(WeexEventBean weexEventBean) {
        TabbarWatchBean bean = new TabbarWatchBean(-1);
        bean.isClear = true;
        bean.setHsCode(Integer.parseInt(weexEventBean.getExpand().toString()));
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(bean);
    }

    private void clearTabbarInfo(WeexEventBean weexEventBean) {
        weexEventBean.setJsParams("");
        setTabbar(weexEventBean);
    }


    @Subscribe
    public void watchIndex(TabbarWatchBean bean) {
        if (watchCallback != null) {
            if (bean.index != -1 && !bean.isClear) {
                watchCallback.invokeAndKeepAlive(bean.index);
            } else {
                if (bean.hsCode == callBaclKey) {
                    watchCallback = null;
                    ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
                }
            }
        }
    }

}
