package com.benmu.framework.event.modal;

import android.content.Context;
import android.content.DialogInterface;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ModalManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.ModalBean;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/8/23.
 */

public class EventAlert {

    public void alert(String options, final JSCallback callback, Context Context) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        ModalBean bean = parseManager.parseObject(options, ModalBean.class);
        ModalManager.BmAlert.showAlert(Context, bean.getTitle(), bean
                .getMessage(), bean.getOkTitle(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback != null) {
                    callback.invoke(null);
                }
            }
        }, null, null, bean.getTitleAlign(), bean.getMessageAlign());
    }
}
