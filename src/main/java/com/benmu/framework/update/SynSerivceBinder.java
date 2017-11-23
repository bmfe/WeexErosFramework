package com.benmu.framework.update;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Carry on 2017/11/23.
 * 更新服务代理类
 */

public class SynSerivceBinder {
    private SynVersionService.SynBinder mBinder;
    private SynSericeConnection mConnection;

    public void bindSynService(Context context) {
        if (context == null) return;
        Intent intent = new Intent(context, SynVersionService.class);
        mConnection = new SynSericeConnection();
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void checkVersion(Context context) {
        Intent intent = new Intent(context, SynVersionService.class);
        context.startService(intent);
    }

    public void unbind(Context context) {
        context.unbindService(mConnection);
    }


    private class SynSericeConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                mBinder = (SynVersionService.SynBinder) iBinder;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBinder = null;
        }
    }
}
