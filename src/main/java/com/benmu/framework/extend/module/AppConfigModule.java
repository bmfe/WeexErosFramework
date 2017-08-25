package com.benmu.framework.extend.module;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.AppConfigBean;
import com.benmu.framework.utils.BaseCommonUtil;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * 获取应用参数,环境配置
 */
public class AppConfigModule extends WXModule {
    private AppConfigBean mFontSizeBean;

    @JSMethod(uiThread = false)
    public void changeFontSize(String options, JSCallback jsCallback) {
        initFontSize();
        if (options == null) {
            if (jsCallback != null) {
                jsCallback.invoke(mFontSizeBean);
            }
            return;
        }
        Object obj = ManagerFactory.getManagerService(ParseManager.class).parseObject(options)
                .get("fontSize");
        if (obj != null && obj instanceof String) {
            String fontSize = (String) obj;
            if (fontSize.equals(mFontSizeBean.data.fontSize)) {
                if (jsCallback != null) {
                    jsCallback.invoke(mFontSizeBean);
                }
                return;
            }
            mFontSizeBean.data.fontSize = fontSize;
            Intent intent = new Intent(Constant.Action.ACTION_GOBALFONTSIZE_CHANGE);
            intent.putExtra("currentFontSize", fontSize);
            LocalBroadcastManager.getInstance(mWXSDKInstance.getContext()).sendBroadcast(intent);
            SharePreferenceUtil.setAppFontSizeOption(mWXSDKInstance.getContext(), fontSize);
        }
        if (jsCallback != null) {
            jsCallback.invoke(mFontSizeBean);
        }


    }


    @JSMethod(uiThread = false)
    public void getFontSize(JSCallback jsCallback) {
        initFontSize();
        if (jsCallback != null) {
            jsCallback.invoke(mFontSizeBean);
        }
    }

    private void initFontSize() {
        if (mFontSizeBean == null) {
            String fontSize = SharePreferenceUtil.getAppFontSizeOption(mWXSDKInstance.getContext());
            mFontSizeBean = new AppConfigBean();
            mFontSizeBean.msg = "";
            mFontSizeBean.resCode = 0;
            AppConfigBean.Result data = new AppConfigBean.Result();
            data.fontSize = fontSize;
            data.scale = BaseCommonUtil.getScaleByFontsize(fontSize);
            mFontSizeBean.data = data;
        }
    }

}
