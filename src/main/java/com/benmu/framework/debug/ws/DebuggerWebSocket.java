package com.benmu.framework.debug.ws;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.adapter.ws.DefaultWebSocketAdapter;
import com.benmu.framework.adapter.ws.WSConfig;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.squareup.otto.Subscribe;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.appfram.websocket.WebSocketCloseCodes;


/**
 * 开发阶段 连接服务实时刷新页面
 * Created by liuyuanxiao on 18/3/15.
 */

public class DebuggerWebSocket {
    private DefaultWebSocketAdapter webSocketInstance;
    private MyWebSocketListener eventListent;
    private static final String TAG = "DebuggerWebSocket";
    private Handler mHandler;
    private boolean mActice;

    public DebuggerWebSocket() {
        webSocketInstance = new DefaultWebSocketAdapter();
        eventListent = new MyWebSocketListener();
        mHandler = new Handler(Looper.getMainLooper());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }


    public void init() {
        connect(WXConstant.DEBUG_SOKECT_URL);
    }

    private void connect(String url) {
        //connect when interceptor close
        if (!Constant.INTERCEPTOR_ACTIVE.equals(SharePreferenceUtil.getInterceptorActive
                (BMWXEnvironment.mApplicationContext)
        )) {
            mActice = true;
            webSocketInstance.connect(url, "", eventListent, new WSConfig(true, 10));

        } else {
            mActice = false;
            webSocketInstance.close(WebSocketCloseCodes.CLOSE_NORMAL.getCode(), "手动关闭");
        }
    }


    private class MyWebSocketListener implements IWebSocketAdapter.EventListener {

        @Override
        public void onOpen() {
            Log.e(TAG, "调试socket已链接");
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BMWXEnvironment.mApplicationContext, "调试socket已链接", Toast
                            .LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onMessage(String data) {
            if (Instruction.REFRESH.equals(data)) {
                Activity peek = RouterTracker.peekActivity();
                if (peek instanceof AbstractWeexActivity) {
                    ((AbstractWeexActivity) peek).refresh();
                }
            }

        }

        @Override
        public void onClose(int code, String reason, boolean wasClean) {
            //重连
            Log.e(TAG, "调试socket关闭重试");
            reconnect();
        }

        @Override
        public void onError(String msg) {
            //重连
            Log.e(TAG, "调试socket链接失败重试");
            reconnect();
        }
    }

    private void reconnect() {
        if (!mActice) return;

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BMWXEnvironment.mApplicationContext, "调试socket尝试重连", Toast
                        .LENGTH_SHORT).show();
                webSocketInstance.reconnect();
            }
        }, 3000);
    }


    @Subscribe
    public void onEvent(Intent intent) {
        if (WXConstant.ACTION_INTERCEPTOR_SWTICH.equals(intent.getAction())) {
            //interceptor swtich
            connect(WXConstant.DEBUG_SOKECT_URL);
        }
    }

}
