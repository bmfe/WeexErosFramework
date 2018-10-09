package com.eros.framework.manager.impl;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import com.eros.framework.BMWXEnvironment;
import com.eros.framework.constant.WXConstant;
import com.eros.framework.manager.Manager;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.receiver.NetworkReceiver;
import com.eros.framework.utils.JsPoster;
import com.eros.framework.utils.NetworkUtil;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carry on 2018/5/30.
 */

public class ConnectivityManager extends Manager {
    private List<JSCallback> mCallbacks;
    private NetworkReceiver mNetworkReceiver;

    public void registerNetworkListener(Context context, JSCallback callback) {
        if (mCallbacks == null) {
            mCallbacks = new ArrayList<>();
        }
        mCallbacks.add(callback);
        if (mNetworkReceiver == null) {
            mNetworkReceiver = new NetworkReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
            intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            context.registerReceiver(mNetworkReceiver, intentFilter);
        }

        DispatchEventManager dispatchEventManager = ManagerFactory.getManagerService
                (DispatchEventManager.class);
        dispatchEventManager.getBus().register(this);
    }

    @Subscribe
    public void onNetworkChange(Intent intent) {
        if (WXConstant.ACTION_NETWORK_CHANGED.equals(intent.getAction())) {
            String status = NetworkUtil.status(BMWXEnvironment.mApplicationContext);
            if (mCallbacks != null) {
                for (JSCallback callback : mCallbacks) {
                    if (callback != null) {
                        callback.invokeAndKeepAlive(JsPoster.getSuccess(status));
                    }
                }
            }
        }
    }

    public void unregisterNetworkListener(Context context) {
        if (mNetworkReceiver != null) {
            context.unregisterReceiver(mNetworkReceiver);
            mNetworkReceiver = null;

        }
        if (mCallbacks != null) {
            mCallbacks.clear();
            mCallbacks = null;
        }
        DispatchEventManager dispatchEventManager = ManagerFactory.getManagerService
                (DispatchEventManager.class);
        dispatchEventManager.getBus().unregister(this);
    }
}
