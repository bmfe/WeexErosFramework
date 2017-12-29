package com.benmu.framework.manager.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.benmu.framework.R;
import com.benmu.framework.adapter.router.DefaultRouterAdapter;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.model.MedicalMapModel;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.TitleModel;
import com.benmu.framework.model.WebViewParamBean;
import com.benmu.framework.utils.RouterUtil;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Carry on 2017/8/7. manage router
 */

public class RouterManager extends Manager {

    public static void finish(Activity activity) {
        //TODO
    }


    public void dialing(final Context context, final String params) {
        //TODO
        DefaultRouterAdapter.getInstance().dialing(context, params);
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }


    public boolean open(Context context, String params, JSCallback callback) {
        return !(context == null || TextUtils.isEmpty(params)) && DefaultRouterAdapter
                .getInstance().open(context, params,callback);
    }

    public boolean back(Context context, String params) {
        return !(context == null || TextUtils.isEmpty(params)) && DefaultRouterAdapter
                .getInstance().back(context, params);
    }

    public RouterModel getParams(Context context) {
        return DefaultRouterAdapter.getInstance().getParams(context);
    }

    public boolean refresh(Context context) {
        return context != null && DefaultRouterAdapter.refresh(context);

    }

    public boolean finish(Context context){
        return context != null && DefaultRouterAdapter.finish(context);
    }
    public void toWebView(Context context, String params) {
        DefaultRouterAdapter.getInstance().toWebView(context, params);
    }

    public boolean openBrowser(Context context,String params){
        return DefaultRouterAdapter.getInstance().openBrowser(context, params);
    }
}
