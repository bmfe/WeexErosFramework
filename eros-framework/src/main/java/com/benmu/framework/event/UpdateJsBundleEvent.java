package com.benmu.framework.event;

import android.content.Context;
import android.content.Intent;
import com.benmu.framework.BMWXApplication;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.UpdateOptionBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.update.VersionChecker;
import com.benmu.framework.utils.JsPoster;
import com.benmu.wxbase.EventGate;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

public class UpdateJsBundleEvent extends EventGate {
    private JSCallback mCallback;

    @Override
    public void perform(Context context, WeexEventBean weexEventBean, String type) {
        if (WXEventCenter.EVENT_DOWNLOAD_BUNDLE.equals(type)) {
            downloadBundle(weexEventBean);
        }
        if (WXEventCenter.EVENT_UPDATE_BUNDLE.equals(type)) {
            update();
        }
    }

    private void update() {
        VersionChecker versionChecker = BMWXApplication.getWXApplication().getVersionChecker();
        if (versionChecker != null) {
            versionChecker.restartApp();
        }
    }

    private void downloadBundle(WeexEventBean weexEventBean) {
        if (weexEventBean == null) return;
        UpdateOptionBean updateOptionBean = ManagerFactory.getManagerService(ParseManager.class)
                .parseObject(weexEventBean.getJsParams(), UpdateOptionBean.class);
        if (updateOptionBean == null) return;
        VersionChecker versionChecker = BMWXApplication.getWXApplication().getVersionChecker();
        if (versionChecker != null) {
            this.mCallback = weexEventBean.getJscallback();
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
            versionChecker.checkVersion(updateOptionBean.getUrl(), updateOptionBean.isDiff());
        }

    }

    @Subscribe
    public void onUpdateResult(Intent intent) {
        if (WXConstant.ACTION_BUNDLE_DOWNLOADED.equals(intent.getAction())) {
            int code = intent.getIntExtra("code", -1);
            String msg = intent.getStringExtra("msg");
            if (code == 0) {
                JsPoster.postSuccess(null, msg, mCallback);
            } else {
                JsPoster.postFailed(msg, mCallback);
            }
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        }
    }


}
