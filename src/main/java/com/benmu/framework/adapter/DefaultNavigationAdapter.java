package com.benmu.framework.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.status.StatusBarManager;
import com.benmu.framework.model.BaseResultBean;
import com.benmu.framework.model.TitleModel;
import com.benmu.framework.utils.BMHookGlide;
import com.benmu.framework.utils.BaseCommonUtil;
import com.benmu.widget.utils.ColorUtils;
import com.benmu.widget.view.BaseToolBar;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.utils.WXUtils;

/**
 * Created by Carry on 2017/9/13.
 */

public class DefaultNavigationAdapter {

    public static void setLeftItem(String params, final JSCallback jscallback) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        TitleModel titleModel = parseManager.parseObject(params, TitleModel.class);
        BaseToolBar navigationBar = getToolBar();
        if (navigationBar == null) return;

        setTextView(navigationBar.getLeftTextView(), titleModel);

        if (!TextUtils.isEmpty(titleModel.getImage())) {
            setImage(BMWXEnvironment.mApplicationContext, titleModel.getImage(), navigationBar
                    .getLeftIcon());
        }

        if (jscallback != null) {

            navigationBar.setOnWebClosedListenner(new BaseToolBar.OnWebViewClosed() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(new BaseResultBean());
                }
            });
            navigationBar.setOnLeftListenner(new BaseToolBar.OnLeftIconClick() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(null);
                }
            });
        }
    }


    private static void setImage(Context context, String url, final ImageView image) {
        BMHookGlide.load(context, url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into
                (new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap>
                            glideAnimation) {
                        image.setImageBitmap(resource);
                        image.setVisibility(View.VISIBLE);
                    }
                });
    }

    private static void setTextView(TextView textView, TitleModel titleModel) {
        if (textView == null) return;
        if (!TextUtils.isEmpty(titleModel.getFontSize())) {
            textView.setTextSize(BaseCommonUtil.px2dp(BMWXEnvironment.mApplicationContext,
                    WXUtils.getInt(titleModel.getFontSize
                            ())));
        }
        if (!TextUtils.isEmpty(titleModel.getFontWeight())) {
            textView.getPaint().setFakeBoldText(true);

        }
        if (!TextUtils.isEmpty(titleModel.getText())) {
            textView.setText(titleModel.getText());
        }
        if (!TextUtils.isEmpty(titleModel.getTextColor())) {
            textView.setTextColor(ColorUtils.getColor(titleModel
                    .getTextColor()));
        }
        textView.setVisibility(View.VISIBLE);

    }

    public static void setRightItem(String params, final JSCallback jscallback) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        TitleModel titleModel = parseManager.parseObject(params, TitleModel.class);
        BaseToolBar navigationBar = getToolBar();
        if (navigationBar == null) return;

        setTextView(navigationBar.getRightText(), titleModel);

        if (!TextUtils.isEmpty(titleModel.getImage())) {
            setImage(BMWXEnvironment.mApplicationContext, titleModel.getImage(), navigationBar
                    .getRightIcon());
        }

        if (jscallback != null) {

            navigationBar.setOnRightListenner(new BaseToolBar.OnRightIconClick() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(new BaseResultBean());
                }
            });
        }
    }

    public static void setNavigationInfo(String params, final JSCallback jscallback) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        TitleModel titleModel = parseManager.parseObject(params, TitleModel.class);
        BaseToolBar navigationBar = getToolBar();
        if (navigationBar == null) return;
        navigationBar.setVisibility(titleModel.isNavShow() ? View.VISIBLE : View.GONE);
        if (jscallback != null)
            navigationBar.setOnTitleListenner(new BaseToolBar.OnTitleClick() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(new BaseResultBean());
                }
            });
    }

    public static void setCenterItem(String params, final JSCallback jscallback) {
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        TitleModel titleModel = parseManager.parseObject(params, TitleModel.class);
        BaseToolBar navigationBar = getToolBar();

        if (navigationBar == null) return;

        setTextView(navigationBar.getTitleTextView(), titleModel);

        if (jscallback != null)
            navigationBar.setOnTitleListenner(new BaseToolBar.OnTitleClick() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(new BaseResultBean());
                }
            });
    }

    private static BaseToolBar getToolBar() {
        Activity activity = RouterTracker.peekActivity();
        if (activity != null) {
            if (activity instanceof AbstractWeexActivity) {
                AbstractWeexActivity abs = (AbstractWeexActivity) activity;
                return abs.getNavigationBar();
            }
        }
        return null;
    }


}
