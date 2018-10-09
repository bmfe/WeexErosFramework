package com.eros.framework.event.modal;

import android.content.Context;
import android.text.TextUtils;

import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ModalManager;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.model.ModalBean;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventShowLoading extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        String params = weexEventBean.getJsParams();
        if (TextUtils.isEmpty(params)) return;
        JSCallback modal_showloading_callback = weexEventBean.getJscallback();
        showLoading(params, modal_showloading_callback, context);
    }

    public void showLoading(String options, JSCallback callback, Context Context) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        ModalBean bean = parseManager.parseObject(options, ModalBean.class);
        ModalManager.BmLoading.showLoading(Context, bean.getMessage(), false);
        if (callback != null) {
            callback.invoke(null);
        }
    }

}
