package com.benmu.framework.event.share;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ShareManager;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/26.
 */

public class EventShare {

    public void share(Context context, String params, JSCallback success, JSCallback fail) {
        if (context == null || TextUtils.isEmpty(params)) return;
        ShareManager shareManager = ManagerFactory.getManagerService(ShareManager.class);
        shareManager.share((Activity) context, params, success, fail);
    }
}
