package com.eros.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.eros.framework.constant.WXConstant;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;

/**
 * Created by Carry on 2018/5/30.
 */

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(new Intent
                    (WXConstant.ACTION_NETWORK_CHANGED));
        }
    }


}
