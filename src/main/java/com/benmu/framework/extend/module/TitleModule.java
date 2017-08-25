package com.benmu.framework.extend.module;

import android.app.Activity;
import android.util.Log;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * Created by Carry on 17/1/12.
 */

public class TitleModule extends WXModule {


    @JSMethod(uiThread = true)
    public void set(String data) {
        //TODO
//        TitleModel title = BMJsonParsManager.parseObject(data, TitleModel.class);
//        Activity activity = RouterUtil.peek();
//        if (activity != null) {
//            BaseActivity target = (BaseActivity) activity;
//            target.setRightText(title.subTitle);
//            target.setTitle(title.title);
//        }
    }

    @JSMethod(uiThread = true)
    public void setRightItem(String json, final JSCallback callback) {
//        WeexEventBean weexEventBean = new WeexEventBean();
//        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_RIGHTITEM);
//        weexEventBean.setContext(mWXSDKInstance.getContext());
//        weexEventBean.setJscallback(callback);
//        weexEventBean.setJsParams(json);
//        BMEventManager.getInstance().getBus().post(weexEventBean);
//        TitleModel headerBean = BMJsonParsManager.parseObject(json, TitleModel.class);
//        Activity activity = RouterUtil.peek();
//        if (activity != null) {
//            BMBaseActivity target = (BMBaseActivity) activity;
//
//            BMToolBar bmBar = (BMToolBar) target.getBMBar();
//            if (bmBar == null) return;
//
//            bmBar.setOnRightListenner(new BaseToolBar.OnRightIconClick() {
//                @Override
//                public void onClick(View v) {
//                    callback.invokeAndKeepAlive(new BaseResultBean());
//                }
//            });
//            if (TextUtils.isEmpty(headerBean.getText())) {
//                if (TextUtils.isEmpty(headerBean.getImage())) return;
//                bmBar.setRightIconUrl(headerBean.getImage());
//            } else {
//                bmBar.setRightText(headerBean.getText());
//            }
//            if (TextUtils.isEmpty(headerBean.getTextColor())) return;
//            bmBar.setRightTextColor(headerBean.getText());
//
//
//        }

    }

    @JSMethod(uiThread = true)
    public void setLeftItem(String json, final JSCallback callback) {
//        WeexEventBean weexEventBean = new WeexEventBean();
//        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_LEFTITEM);
//        weexEventBean.setContext(mWXSDKInstance.getContext());
//        weexEventBean.setJscallback(callback);
//        weexEventBean.setJsParams(json);
//        BMEventManager.getInstance().getBus().post(weexEventBean);
//        TitleModel headerBean = BMJsonParsManager.parseObject(json, TitleModel.class);
//        Activity activity = RouterUtil.peek();
//        if (activity != null) {
//            BMBaseActivity target = (BMBaseActivity) activity;
//
//            BMToolBar bmBar = (BMToolBar)target.getBMBar();
//            if (bmBar == null) return;
//
//            bmBar.setOnLeftListenner(new BaseToolBar.OnLeftIconClick() {
//                @Override
//                public void onClick(View v) {
//                    callback.invokeAndKeepAlive(new BaseResultBean());
//                }
//            });
//            if (TextUtils.isEmpty(headerBean.getText())) {
//                if (TextUtils.isEmpty(headerBean.getImage())) return;
//                bmBar.setLeftIconUrl(headerBean.getImage());
//            } else {
//                bmBar.getLeftTextView().setText(headerBean.getText());
//            }
//            if (TextUtils.isEmpty(headerBean.getTextColor())) return;
//            bmBar.setLeftTextColor(headerBean.getText());
//
//
//        }

    }


    @JSMethod(uiThread = true)
    public void setNavigationInfo(String json,JSCallback callback){
//        Log.e("setNavigationInfo","json>>>>>"+json);
//        WeexEventBean weexEventBean = new WeexEventBean();
//        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_NAVIGATIONINFO);
//        weexEventBean.setContext(mWXSDKInstance.getContext());
//        weexEventBean.setJscallback(callback);
//        weexEventBean.setJsParams(json);
//        BMEventManager.getInstance().getBus().post(weexEventBean);

    }

    @JSMethod(uiThread = true)
    public void setCenterItem(String json, final JSCallback callback) {
//        WeexEventBean weexEventBean = new WeexEventBean();
//        weexEventBean.setKey(WXConstant.WXEventCenter.EVENT_CENTERITEM);
//        weexEventBean.setContext(mWXSDKInstance.getContext());
//        weexEventBean.setJscallback(callback);
//        weexEventBean.setJsParams(json);
//        BMEventManager.getInstance().getBus().post(weexEventBean);

//        TitleModel headerBean = BMJsonParsManager.parseObject(json, TitleModel.class);
//        Activity activity = RouterUtil.peek();
//        if (activity != null) {
//            BaseActivity target = (BaseActivity) activity;
//
//            BMToolBar bmBar = target.getBMBar();
//            if (bmBar == null) return;
//
//            bmBar.setOnTitleListenner(new BMToolBar.OnTitleClick() {
//                @Override
//                public void onClick(View v) {
//                    callback.invokeAndKeepAlive(new BaseResultBean());
//                }
//            });
//            if (TextUtils.isEmpty(headerBean.getText())) {
//                bmBar.setTitle("");
//                if (TextUtils.isEmpty(headerBean.getImage())) return;
//                bmBar.setTitleIconUrl(headerBean.getImage());
//            } else {
//                bmBar.setTitle(headerBean.getText());
//            }
//            if (TextUtils.isEmpty(headerBean.getTextColor())) return;
//            bmBar.setTitleColor(headerBean.getTextColor());
//
//        }
    }


}
