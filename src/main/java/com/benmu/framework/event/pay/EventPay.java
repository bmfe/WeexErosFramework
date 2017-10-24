package com.benmu.framework.event.pay;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.BaseResultBean;
import com.benmu.framework.model.WeChatPayModel;
import com.benmu.framework.model.WeChatPayResultModel;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Carry on 2017/9/26.
 */

public class EventPay {
    private JSCallback mCallback;

    public void pay(String params, Context context, JSCallback callback) {
        this.mCallback = callback;
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        WeChatPayModel weChatPayModal = parseManager.parseObject(params, WeChatPayModel.class);
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, weChatPayModal.getAppid(), true);
        if (!wxapi.isWXAppInstalled()) {
            BaseResultBean result = new BaseResultBean();
            result.resCode = 9;
            result.msg = "请先安装微信客户端";
            if (mCallback != null) {
                mCallback.invokeAndKeepAlive(result);
            }
            return;
        }
        PayReq request = new PayReq();
        request.appId = weChatPayModal.getAppid();
        request.partnerId = weChatPayModal.getPartnerid();
        request.nonceStr = weChatPayModal.getNoncestr();
        request.packageValue = weChatPayModal.getPackageValue();
        request.prepayId = weChatPayModal.getPrepayid();
        request.timeStamp = weChatPayModal.getTimestamp();
        request.sign = weChatPayModal.getSign();
        wxapi.registerApp(weChatPayModal.getAppid());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
        wxapi.sendReq(request);
    }

    @Subscribe
    public void onPayResult(WeChatPayResultModel result) {
        if (mCallback != null) {
            mCallback.invoke(result);
        }
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }

}
