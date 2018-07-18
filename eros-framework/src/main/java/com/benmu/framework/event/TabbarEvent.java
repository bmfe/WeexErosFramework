package com.benmu.framework.event;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.benmu.framework.activity.MainActivity;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.StorageManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.TabbarBadgeModule;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.utils.TabbarListener;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

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
        if (context instanceof MainActivity) {
            ((MainActivity) context).openPage(Integer.parseInt(index));
        }
    }

    private void setTabbar(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        storageManager.setData(context, Constant.SP.SP_TABBAR_JSON, weexEventBean.getJsParams());
    }

    private void watchIndex(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        if (context instanceof MainActivity) {
            ((MainActivity) context).watchIndex(new TabbarListen(weexEventBean.getJscallback()));
        }
    }

    private void clearWatch(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        if (context instanceof MainActivity) {
            ((MainActivity) context).clearWatch();
        }
    }

    private void clearTabbarInfo(WeexEventBean weexEventBean) {
        weexEventBean.setJsParams("");
        setTabbar(weexEventBean);
    }

    public static class TabbarListen implements TabbarListener {
        private JSCallback callback;

        private TabbarListen(JSCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onPageSelected(int index) {
            if (callback != null) {
                callback.invokeAndKeepAlive(index);
            }
        }
    }
}
