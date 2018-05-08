package com.benmu.framework.event.router;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

import java.util.List;

/**
 * Created by Carry on 2017/8/21.
 */

public class EventOpen extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        String params = weexEventBean.getJsParams();
        if (TextUtils.isEmpty(params)) return;
        if (weexEventBean.getCallbacks() != null) {
            new EventOpen().open(params, context, weexEventBean.getCallbacks());
        } else if (weexEventBean.getJscallback() != null) {
            new EventOpen().open(params, context, weexEventBean.getJscallback());
        } else {
            new EventOpen().open(params, context);
        }
    }

    public void open(String params, Context context, List<JSCallback> callbacks) {
        JSCallback backCallback = null;
        JSCallback resultCallback = null;
        if (callbacks.size() > 1) {
            backCallback = callbacks.get(0);
            resultCallback = callbacks.get(1);
        } else if (callbacks.size() > 0) {
            backCallback = callbacks.get(0);
        }

        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        boolean result = routerManager.open(context, params, backCallback);

        if (resultCallback != null) {
            resultCallback.invoke(result ? WXConstant.OPEN_PAGE_SUCCESS : WXConstant
                    .OPNE_PAGE_FAILED);
        }
    }

    public void open(String params, Context context, JSCallback backCallback) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        routerManager.open(context, params, backCallback);
    }

    public void open(String params, Context context) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        routerManager.open(context, params, null);
    }
}
