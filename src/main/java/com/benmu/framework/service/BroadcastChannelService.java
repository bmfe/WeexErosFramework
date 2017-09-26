package com.benmu.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;
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
    public void onCreate() {
        super.onCreate();
        String mediatorPage = BMWXEnvironment.mPlatformConfig.getPage().getMediatorPage();
        if (TextUtils.isEmpty(mediatorPage)) return;
        mInstance = new WXSDKInstance(this);
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mediatorPage);
        mInstance.renderByUrl(
                "BroadcastChannel",
                mediatorPage,
                options,
                null,
                WXRenderStrategy.APPEND_ASYNC);
    }
}
