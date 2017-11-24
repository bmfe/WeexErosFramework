package com.benmu.framework.event.auth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.BaseResultBean;
import com.benmu.framework.model.WechatLoginBean;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by Carry on 2017/11/24.
 */

public class EventAuth {
    private Context mContext;
    private JSCallback mCallback;
    private boolean mSharing;
    private ProgressDialog mProgressDialog;

    public void wechat(Context context, String params, JSCallback jscallback) {
        if (context == null) return;
        mContext = context;
        mCallback = jscallback;
        try {
            UMShareAPI umShareAPI = UMShareAPI.get(context);
            umShareAPI.getPlatformInfo((Activity) context, SHARE_MEDIA.WEIXIN, umAuthListener);
        } catch (Exception e) {
            e.printStackTrace();
            if (mCallback != null) {
                mCallback.invoke(new BaseResultBean(9, null));
            }
            return;
        }
        mSharing = true;
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {

        WechatLoginBean bean = new WechatLoginBean();//与前端回调Js Bean

        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("请稍后...");
            mProgressDialog.show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data == null) {
                bean.resCode = 9;
                return;
            } else {
                WechatLoginBean.WechatAuth dataBean = new WechatLoginBean().new WechatAuth();
                String uid = data.get("uid");
                String name = data.get("name");
                dataBean.setUid(TextUtils.isEmpty(uid) ? "" : uid);
                dataBean.setName(TextUtils.isEmpty(name) ? "" : name);
                bean.resCode = 0;
                bean.msg = "success";
                bean.setData(dataBean);
            }

            invoke(bean);
            mProgressDialog.dismiss();
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            bean.resCode = 9;
            invoke(bean);
            mProgressDialog.dismiss();
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            bean.resCode = 9;
            invoke(bean);
            mProgressDialog.dismiss();
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        }
    };

    /**
     * Code 获取
     */
    @Subscribe
    public void OnEvent(Intent intent) {
        if (Constant.Action.ACTION_AUTHLOGIN_CANCEL.equals(intent.getAction()) && mSharing) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            WechatLoginBean bean = new WechatLoginBean();//与前端回调Js Bean
            bean.resCode = 9;
            invoke(bean);
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        }
    }


    /**
     * 返回前端回调地址
     */
    private void invoke(WechatLoginBean bean) {
        mSharing = false;
        if (mCallback != null) {
            mCallback.invoke(bean);
        }
    }
}
