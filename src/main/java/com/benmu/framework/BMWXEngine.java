package com.benmu.framework;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.event.DispatchEventCenter;
import com.benmu.framework.event.mediator.EventCenter;
import com.benmu.framework.extend.adapter.BMTypefaceAdapter;
import com.benmu.framework.extend.adapter.DefaultWXHttpAdapter;
import com.benmu.framework.extend.adapter.DefaultWXImageAdapter;
import com.benmu.framework.http.BMPersistentCookieStore;
import com.benmu.framework.http.okhttp.OkHttpUtils;
import com.benmu.framework.http.okhttp.cookie.CookieJarImpl;
import com.benmu.framework.http.okhttp.log.LoggerInterceptor;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.AxiosManager;
import com.benmu.framework.manager.impl.CustomerEnvOptionManager;
import com.benmu.framework.manager.impl.GeoManager;
import com.benmu.framework.service.BroadcastChannelService;
import com.benmu.framework.update.SynVersionService;
import com.benmu.framework.utils.BaseCommonUtil;
import com.benmu.framework.utils.DebugableUtil;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.Constants;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import me.ele.patch.BsPatch;
import okhttp3.OkHttpClient;

/**
 * Created by Carry on 2017/8/23.
 */

public class BMWXEngine {
    public static void initialize(Application context, BMInitConfig initConfig) {
        initPatch(context);
        initPlatformConfig(context);
        initConing(context, initConfig);
        engineStart(context);
        registerBMComponentsAndModules(context);
        initHttpClient(context);
        initInterceptor(context, initConfig);
        initDispatchCenter();
        DebugableUtil.syncIsDebug(context);
        initWeChat(context);
        initUmeng(context);
        EventCenter.getInstance().init();
        initMap();
    }


    private static void initPatch(Application context) {
        BsPatch.init(context);
    }

    private static void initMap() {
        GeoManager geoManager = ManagerFactory.getManagerService(GeoManager.class);
        geoManager.init();
    }


    private static void initUmeng(Application context) {
        boolean enabled = BMWXEnvironment.mPlatformConfig.getUmeng().isEnabled();
        String androidAppKey = BMWXEnvironment.mPlatformConfig.getUmeng().getAndroidAppKey();
        if (enabled && !TextUtils.isEmpty(androidAppKey)) {
            MobclickAgent.setDebugMode(DebugableUtil.isDebug());
            MobclickAgent.openActivityDurationTrack(false);
            MobclickAgent.setCatchUncaughtExceptions(!DebugableUtil.isDebug());
            MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
            PlatformConfig.setWeixin(BMWXEnvironment.mPlatformConfig.getWechat().getAppId(),
                    BMWXEnvironment.mPlatformConfig.getWechat().getAppSecret());
            UMShareAPI.get(context);
        }
    }


    private static void initWeChat(Context context) {
        boolean enabled = BMWXEnvironment.mPlatformConfig.getWechat().isEnabled();
        String appId = BMWXEnvironment.mPlatformConfig.getWechat().getAppId();
        if (enabled && !TextUtils.isEmpty(appId)) {
            BMWXEnvironment.mWXApi = WXAPIFactory.createWXAPI(context, appId, true);
        }
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
        ManagerFactory.getManagerService(AxiosManager.class).initClient(context);
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
                        DefaultWXHttpAdapter(app)).setTypefaceAdapter(new BMTypefaceAdapter(app))
                        .build()
        );
    }

    private static void initConing(Application context, BMInitConfig initConfig) {
        if (initConfig == null) return;
        initEnv(initConfig.getmEnvs(), context);
    }

    private static void initEnv(HashMap<String, String> Env, Context context) {
        HashMap<String, String> insideEnv = new HashMap<>();
        insideEnv.put(Constant.CustomOptions.CUSTOM_APPNAME, BMWXEnvironment.mPlatformConfig
                .getAppName());
        insideEnv.put(Constant.CustomOptions.CUSTOM_SERVERURL, BMWXEnvironment.mPlatformConfig
                .getUrl()
                .getJsServer());
        insideEnv.put(Constant.CustomOptions.CUSTOM_REQUESTURL, BMWXEnvironment.mPlatformConfig
                .getUrl().getRequest());
        int statusBarHeight = BaseCommonUtil.getStatusBarHeight(context);
        insideEnv.put(Constant.CustomOptions.CUSTOM_STATUSBARHEIGHT, BaseCommonUtil
                .transferDimenToFE(context, statusBarHeight) + "");

        insideEnv.put(Constant.CustomOptions.CUSTOM_REALDEVICEHEIGHT, "" + BaseCommonUtil.transferDimenToFE(context, BaseCommonUtil.getRealDeviceHeight(context)));

        if (Env != null && !Env.isEmpty()) {
            insideEnv.putAll(Env);
        }
        BMWXEnvironment.mCustomer = insideEnv;
        CustomerEnvOptionManager.registerCustomConfig(insideEnv);
    }


}
