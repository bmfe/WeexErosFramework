package com.eros.framework.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eros.framework.BMWXEnvironment;
import com.eros.framework.activity.AbstractWeexActivity;
import com.eros.framework.adapter.router.RouterTracker;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ImageManager;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.manager.impl.status.StatusBarManager;
import com.eros.framework.model.BaseResultBean;
import com.eros.framework.model.NatigatorModel;
import com.eros.framework.model.NavigatorBarModel;
import com.eros.framework.model.NavigatorModel;
import com.eros.framework.model.RouterModel;
import com.eros.framework.utils.BMHookGlide;
import com.eros.framework.utils.ImageUtil;
import com.eros.widget.utils.BaseCommonUtil;
import com.eros.widget.utils.ColorUtils;
import com.eros.widget.view.BaseToolBar;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.utils.WXUtils;

/**
 * Created by Carry on 2017/9/13.
 */

public class DefaultNavigationAdapter {

    public static void setLeftItem(String params, final JSCallback jscallback) {
        BaseToolBar navigationBar;
        if (TextUtils.isEmpty(params)) {
            navigationBar = getToolBar();
            if (navigationBar == null) return;
            navigationBar.getLeftTextView().setVisibility(View.GONE);
            navigationBar.getLeftIcon().setVisibility(View.GONE);
            return;
        }
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        NavigatorBarModel navigatorBarModel = parseManager.parseObject(params, NavigatorBarModel
                .class);
        navigationBar = getToolBar();
        if (navigationBar == null) return;

        setTextView(navigationBar.getLeftTextView(), navigatorBarModel);

        if (!TextUtils.isEmpty(navigatorBarModel.getImage())) {
            setImage(BMWXEnvironment.mApplicationContext, navigatorBarModel.getImage(),
                    navigationBar
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


    private static void setImage(final Context context, String url, final ImageView image) {
        BMHookGlide.load(context, url).apply(new RequestOptions().diskCacheStrategy
                (DiskCacheStrategy.ALL)).into
                (new SimpleTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<?
                            super Drawable> transition) {
                        if (resource instanceof BitmapDrawable) {
                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                            Bitmap scaleBitmap = ImageUtil.zooImage(context, bitmap,
                                    BaseCommonUtil.getImageScale(context));
                            if (scaleBitmap != null) {
                                image.setImageBitmap(scaleBitmap);
                                image.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                });
    }

    private static void setTextView(TextView textView, NavigatorBarModel navigatorBarModel) {
        if (textView == null) return;
        if (!TextUtils.isEmpty(navigatorBarModel.getFontSize())) {
            textView.setTextSize(WXUtils.getInt(navigatorBarModel.getFontSize()) / 2);
        }
        String fontWeight = navigatorBarModel.getFontWeight();
        if (!TextUtils.isEmpty(fontWeight) && !"normal".equals(fontWeight)) {
            textView.getPaint().setFakeBoldText(true);
        } else {
            textView.getPaint().setFakeBoldText(false);
        }
        String text = navigatorBarModel.getText();
        textView.setText(text);

        String textColor = navigatorBarModel.getTextColor();
        if (!TextUtils.isEmpty(textColor)) {
            textView.setTextColor(ColorUtils.getColor(textColor));
        } else {
            //传递颜色无效 检查基础配置中的颜色
            String navItemColor = BMWXEnvironment.mPlatformConfig.getPage().getNavItemColor();
            if (TextUtils.isEmpty(navItemColor)) {
                //没有设置基础颜色
                textView.setTextColor(ColorUtils.getColor("#ffffff"));
            } else {
                textView.setTextColor(ColorUtils.getColor(navItemColor));
            }
        }
        textView.setVisibility(View.VISIBLE);
    }

    public static void setRightItem(String params, final JSCallback jscallback) {
        BaseToolBar navigationBar;
        if (TextUtils.isEmpty(params)) {
            navigationBar = getToolBar();
            if (navigationBar == null) return;
            navigationBar.getRightText().setVisibility(View.GONE);
            navigationBar.getRightIcon().setVisibility(View.GONE);
            return;
        }
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        NavigatorBarModel navigatorBarModel = parseManager.parseObject(params, NavigatorBarModel
                .class);
        navigationBar = getToolBar();
        if (navigationBar == null) return;
        setTextView(navigationBar.getRightText(), navigatorBarModel);

        if (!TextUtils.isEmpty(navigatorBarModel.getImage())) {
            setImage(BMWXEnvironment.mApplicationContext, navigatorBarModel.getImage(),
                    navigationBar
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
        BaseToolBar navigationBar;
        if (TextUtils.isEmpty(params)) {
            navigationBar = getToolBar();
            if (navigationBar == null) return;
            navigationBar.setVisibility(View.GONE);
            return;
        }
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
//        NavigatorBarModel navigatorBarModel = parseManager.parseObject(params, NavigatorBarModel
//                .class);
        NatigatorModel navigatorModel = parseManager.parseObject(params, NatigatorModel
                .class);
        navigationBar = getToolBar();
        if (navigationBar == null) return;
        navigationBar.setVisibility(navigatorModel.isNavShow() ? View.VISIBLE : View.GONE);
        if (navigationBar.getVisibility() == View.GONE) return;
        navigationBar.getTitleTextView().setText(navigatorModel.getTitle());
        if (navigatorModel.getStatusBarStyle() == null || "".equals(navigatorModel
                .getStatusBarStyle()) || "Default".equals(navigatorModel.getStatusBarStyle())) {
            navigationBar.getTitleTextView().setTextColor(ColorUtils.getColor("#000000"));
        } else {
            navigationBar.getTitleTextView().setTextColor(ColorUtils.getColor("#ffffff"));
        }
        if (jscallback != null)
            navigationBar.setOnTitleListenner(new BaseToolBar.OnTitleClick() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(new BaseResultBean());
                }
            });
    }

    public static void setCenterItem(String params, final JSCallback jscallback) {
        BaseToolBar navigationBar;
        if (TextUtils.isEmpty(params)) {
            navigationBar = getToolBar();
            if (navigationBar == null) return;
            navigationBar.getTitleTextView().setVisibility(View.GONE);
            return;
        }
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        NavigatorBarModel navigatorBarModel = parseManager.parseObject(params, NavigatorBarModel
                .class);

        navigationBar = getToolBar();

        if (navigationBar == null) return;

        setTextView(navigationBar.getTitleTextView(), navigatorBarModel);

        if (jscallback != null)
            navigationBar.setOnTitleListenner(new BaseToolBar.OnTitleClick() {
                @Override
                public void onClick(View v) {
                    jscallback.invokeAndKeepAlive(new BaseResultBean());
                }
            });
    }


    public static void setTabbarNavigation(Activity activity, NavigatorModel navigatorModel) {
        if (activity instanceof AbstractWeexActivity) {

            RouterModel routerModel = ((AbstractWeexActivity) activity).getRouterParam();
            ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
            NatigatorModel model = parseManager.parseObject(navigatorModel.navigatorModel,
                    NatigatorModel
                            .class);
            routerModel.navShow = model.isNavShow();
            routerModel.navTitle = model.getTitle();
            routerModel.canBack = false;
            ((AbstractWeexActivity) activity).setRouterParam(routerModel);
            ((AbstractWeexActivity) activity).setNavigationBar();
            StatusBarManager.setHeaderBg(routerModel, (AbstractWeexActivity) activity);
        }
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
