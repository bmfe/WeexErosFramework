package com.benmu.framework.event.router;


import android.content.Context;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.StorageManager;

/**
 * Created by liuyuanxiao on 18/1/4.
 */

public class EventSetHomePage {
    public void setHomePage(Context context, String params) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        storageManager.setData(context, Constant.SP.SP_HOMEPAGE_URL, params);
    }
}
