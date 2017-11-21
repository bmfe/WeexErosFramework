package com.benmu.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.GlobalEventManager;
import com.benmu.framework.manager.impl.LifecycleManager;
import com.taobao.weex.WXSDKInstance;

/**
 * Created by Carry on 2017/9/4.
 */

public class BMWXApplication extends Application {
    private static BMWXApplication mInstance;
    private WXSDKInstance mMediator;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initWeex();
        registerLifecycle();
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

    private void registerLifecycle() {
        LifecycleManager lifecycleManager = ManagerFactory.getManagerService(LifecycleManager
                .class);
        lifecycleManager.register(this).setOnTaskSwitchListenner(new LifecycleManager
                .OnTaskSwitchListener() {


            @Override
            public void onTaskSwitchToForeground() {
                Activity activity = RouterTracker.peekActivity();
                if (activity != null) {
                    GlobalEventManager.appActive(((AbstractWeexActivity) activity)
                            .getWXSDkInstance());
                }
            }

            @Override
            public void onTaskSwitchToBackground() {
                Activity activity = RouterTracker.peekActivity();
                if (activity != null) {
                    GlobalEventManager.appDeactive(((AbstractWeexActivity) activity)
                            .getWXSDkInstance());
                }
            }
        });
    }


    private void initWeex() {
        BMWXEngine.initialize(this, new BMInitConfig.Builder().isActiceInterceptor(Constant
                .INTERCEPTOR_ACTIVE).build());

    }


    public static BMWXApplication getWXApplication() {
        return mInstance;
    }
}
