package com.benmu.framework.event.tool;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.model.CidBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.utils.JsPoster;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.benmu.widget.utils.BaseCommonUtil;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/9/18.
 */

public class EventTool extends EventGate {

    @Override
    public void perform(Context context, WeexEventBean weexEventBean, String type) {
        if (WXEventCenter.EVENT_RESIGNKEYBOARD.equals(type)) {
            resignKeyboard(context, weexEventBean.getJscallback());
        } else if (WXEventCenter.EVENT_ISINSTALLWXAPP.equals(type)) {
            isWXInstall(context, weexEventBean.getJscallback());
        } else if (WXEventCenter.EVENT_GETCID.equals(type)) {
            getCid(context, weexEventBean.getJscallback());
        } else if (WXEventCenter.EVENT_COPYSTRING.equals(type)) {
            copyString(context, weexEventBean.getJsParams(), weexEventBean.getJscallback());
        }
    }

    public void resignKeyboard(Context context, JSCallback jscallback) {
        if (BaseCommonUtil.getKeyBoardState(context)) {
            JsPoster.postSuccess(null, jscallback);
        } else {
            JsPoster.postFailed(jscallback);
        }
    }


    public void isWXInstall(Context context, JSCallback jsCallback) {
        boolean weChatInstall = BaseCommonUtil.isWeChatInstall(context);

        if (weChatInstall) {
            JsPoster.postSuccess(true, jsCallback);
        } else {
            JsPoster.postFailed(jsCallback);
        }

    }

    public void copyString(Context context, String params, JSCallback callback) {
        BaseCommonUtil.copyString(context, params);
        JsPoster.postSuccess(null, "复制成功", callback);
    }

    public void getCid(Context context, JSCallback callback) {
        CidBean cidBean = new CidBean();
        String clientId = SharePreferenceUtil.getClientId(context);
        if (!TextUtils.isEmpty(clientId)) {
            cidBean.setCid(clientId);
            JsPoster.postSuccess(cidBean, callback);
        } else {
            JsPoster.postFailed(callback);
        }
    }

}

