package com.benmu.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.utils.InsertEnvUtil;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carry on 2017/9/26.
 */

public class BroadcastChannelService extends Service {
    private WXSDKInstance mInstance;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String mediatorPage = BMWXEnvironment.mPlatformConfig.getUrl().getJsServer() +
                "/dist/js" + BMWXEnvironment.mPlatformConfig.getPage().getMediatorPage();
        if (TextUtils.isEmpty(mediatorPage)) return super.onStartCommand(intent, flags, startId);
        mInstance = new WXSDKInstance(this);
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mediatorPage);
        InsertEnvUtil.customerRender(options);
        mInstance.renderByUrl(
                "BroadcastChannel",
                mediatorPage,
                options,
                null,
                WXRenderStrategy.APPEND_ASYNC);
        return super.onStartCommand(intent, flags, startId);

    }
}
