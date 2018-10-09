package com.eros.framework.event.modal;

import android.content.Context;

import com.eros.framework.manager.impl.ModalManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/21.
 */

public class EventDismissLoading extends EventGate{

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        dismiss(context, weexEventBean.getJscallback());
    }

    public void dismiss(Context context, JSCallback jscallback) {
        ModalManager.BmLoading.dismissLoading(context);
        if (jscallback != null) {
            jscallback.invoke(null);
        }
    }
}
