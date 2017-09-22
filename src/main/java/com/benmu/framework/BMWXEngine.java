package com.benmu.framework;

import android.app.Application;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.event.DispatchEventCenter;
import com.benmu.framework.extend.adapter.DefaultWXHttpAdapter;
import com.benmu.framework.extend.adapter.DefaultWXImageAdapter;
import com.benmu.framework.http.BMPersistentCookieStore;
import com.benmu.framework.http.okhttp.OkHttpUtils;
import com.benmu.framework.http.okhttp.cookie.CookieJarImpl;
import com.benmu.framework.http.okhttp.log.LoggerInterceptor;
import com.benmu.framework.manager.impl.CustomerEnvOptionManager;
import com.benmu.framework.utils.DebugableUtil;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Carry on 2017/8/23.
 */

public class BMWXEngine {
    public static void initialize(Application context, BMInitConfig initConfig) {
        initPlatformConfig(context);
        initConing(initConfig);
        engineStart(context);
        registerBMComponentsAndModules(context);
        initHttpClient(context);
        initInterceptor(context, initConfig);
        initDispatchCenter();
        DebugableUtil.syncIsDebug(context);
    }


    private static void initDispatchCenter() {
        DispatchEventCenter.getInstance().register();
    }

    private static void initPlatformConfig(Application context) {
        BMWXEnvironment.mPlatformConfig = CustomerEnvOptionManager.initPlatformConfig
                (context);
        BMWXEnvironment.mApplicationContext = context;
    }

    private static void initInterceptor(Application context, BMInitConfig initConfig) {
        String activeState = SharePreferenceUtil.getInterceptorActive(context);

        if (!Constant.INTERCEPTOR_DEACTIVE.equals(activeState)) {
            if (initConfig != null) {
                SharePreferenceUtil.setInterceptorActive(context, initConfig.getmActice());
            }
        }

    }

    private static void initHttpClient(Application context) {
        CookieJarImpl cookieJar = new CookieJarImpl(new BMPersistentCookieStore
                (context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS).cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    private static void registerBMComponentsAndModules(Application context) {
        CustomerEnvOptionManager.init(context);
        CustomerEnvOptionManager.registerComponents(CustomerEnvOptionManager.getComponents());
        CustomerEnvOptionManager.registerModules(CustomerEnvOptionManager.getModules());
    }

    private static void engineStart(Application app) {
        WXSDKEngine.initialize(app,
                new InitConfig.Builder()
                        .setImgAdapter(new DefaultWXImageAdapter()).setHttpAdapter(new
                        DefaultWXHttpAdapter(app))
                        .build()
        );
    }

    private static void initConing(BMInitConfig initConfig) {
        if (initConfig == null) return;
        initEnv(initConfig.getmEnvs());
    }

    private static void initEnv(HashMap<String, String> Env) {
        HashMap<String, String> insideEnv = new HashMap<>();
        insideEnv.put(Constant.CustomOptions.CUSTOM_APPNAME, BMWXEnvironment.mPlatformConfig
                .getAppName());
        insideEnv.put(Constant.CustomOptions.CUSTOM_SERVERURL, BMWXEnvironment.mPlatformConfig
                .getUrl()
                .getJsServer());
        insideEnv.put(Constant.CustomOptions.CUSTOM_REQUESTURL, BMWXEnvironment.mPlatformConfig
                .getUrl().getRequest());
        if (Env != null && !Env.isEmpty()) {
            insideEnv.putAll(Env);
        }
        CustomerEnvOptionManager.registerCustomConfig(insideEnv);
    }


}
