package com.benmu.framework.event.modal;

import android.content.Context;

import com.benmu.framework.manager.impl.ModalManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
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
