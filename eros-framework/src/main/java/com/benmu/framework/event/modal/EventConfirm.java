package com.benmu.framework.event.modal;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ModalManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.ModalBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventConfirm extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        String params = weexEventBean.getJsParams();
        if (TextUtils.isEmpty(params)) return;
        ArrayList<JSCallback> callbacks = weexEventBean.getCallbacks();
        if (callbacks == null && callbacks.size() < 2) return;
        confirm(params, callbacks.get(0), callbacks.get(1), context);
    }

    public void confirm(String options, final JSCallback cancel, final JSCallback ok, Context context) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        ModalBean bean = parseManager.parseObject(options, ModalBean.class);
        ModalManager.BmAlert.showAlert(context, bean.getTitle(), bean
                .getMessage(), bean.getOkTitle(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ok != null) {
                    ok.invoke(null);
                }
            }
        }, bean.getCancelTitle(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cancel != null) {
                    cancel.invoke(null);
                }
            }
        }, bean.getTitleAlign(), bean.getMessageAlign());
    }
}
