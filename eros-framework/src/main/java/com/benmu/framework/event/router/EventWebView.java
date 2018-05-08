package com.benmu.framework.event.router;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventWebView extends EventGate{
    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        toWebView(weexEventBean.getJsParams(),context);
    }

    public void toWebView(String params, Context context) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        routerManager.toWebView(context,params);

//        WebViewParamBean webViewParamBean = BMJsonParsManager.parseObject(params,
//                WebViewParamBean.class);
//        BMRouterManager.toWebView(context, webViewParamBean, AppConstant.WEBVIEW_CATEGORY, new
//                TitleModel(webViewParamBean.getTitle(), null, false, "#07ae9c", true));
    }
}
