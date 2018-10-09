package com.eros.framework.event;

import android.content.Context;
import android.content.Intent;
import com.eros.framework.BMWXApplication;
import com.eros.framework.constant.WXConstant;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.UpdateOptionBean;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.update.VersionChecker;
import com.eros.framework.utils.JsPoster;
import com.eros.wxbase.EventGate;
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
