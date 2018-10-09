package com.eros.framework.event.modal;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ModalManager;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.model.ModalBean;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventToast extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        String params = weexEventBean.getJsParams();
        if (TextUtils.isEmpty(params)) return;
        toast(params, context);
    }

    public void toast(String options, Context Context) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        ModalBean bean = parseManager.parseObject(options, ModalBean.class);
        ModalManager.BmToast.toast(Context, bean.getMessage(), bean
                .getDuration() == 0 ? Toast.LENGTH_SHORT : bean.getDuration());
    }
}
