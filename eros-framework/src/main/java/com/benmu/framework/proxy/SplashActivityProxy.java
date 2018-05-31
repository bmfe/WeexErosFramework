package com.benmu.framework.proxy;

import android.app.Activity;
import android.os.Handler;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.VersionManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.WeexEventBean;

/**
 * Activity 代理类
 * Created by liuyuanxiao on 2018/5/31.
 */

public class SplashActivityProxy extends ActivityProxy {
    private Handler mHandler = new Handler();

    @Override
    public void onCreateInit(Activity activity) {
        init(activity);
    }

    @Override
    public void onResume(Activity activity) {

    }

    @Override
    public void onStart(Activity activity) {

    }

    @Override
    public void onRestart(Activity activity) {

    }

    @Override
    public void onPause(Activity activity) {

    }

    @Override
    public void onStop(Activity activity) {

    }

    @Override
    public void onDestroy(Activity activity) {

    }

    private void init(final Activity activity) {
        final VersionManager versionManager = ManagerFactory.getManagerService(VersionManager
                .class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                long prepareTime = versionManager.prepareJsBundle(activity);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toHome(activity);
                    }
                }, 1500 - prepareTime);
            }
        }).start();


    }

    private void toHome(Activity activity) {
        String homePage = BMWXEnvironment.mPlatformConfig.getPage().getHomePage(activity);
        String NavigationColor = BMWXEnvironment.mPlatformConfig.getPage().getNavBarColor();
        RouterModel router = new RouterModel(homePage, Constant.ACTIVITIES_ANIMATION
                .ANIMATION_PUSH, null, null, false, null);
        DispatchEventManager dispatchEventManager = ManagerFactory.getManagerService
                (DispatchEventManager.class);
        WeexEventBean eventBean = new WeexEventBean();
        eventBean.setKey(WXEventCenter.EVENT_OPEN);
        eventBean.setJsParams(ManagerFactory.getManagerService(ParseManager.class).toJsonString
                (router));
        eventBean.setContext(activity);
        dispatchEventManager.getBus().post(eventBean);
        activity.finish();
    }
}
