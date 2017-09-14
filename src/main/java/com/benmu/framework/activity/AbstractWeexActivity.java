package com.benmu.framework.activity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.BuildConfig;
import com.benmu.framework.R;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.GlobalEventManager;
import com.benmu.framework.manager.impl.ImageManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.manager.impl.status.StatusBarManager;
import com.benmu.framework.model.CameraResultBean;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.utils.WXCommonUtil;
import com.benmu.widget.view.BMFloatingLayer;
import com.benmu.widget.view.BMLoding;
import com.benmu.widget.view.BaseToolBar;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.RenderContainer;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carry on 2017/8/16.
 */

public class AbstractWeexActivity extends AppCompatActivity implements IWXRenderListener,
        RouterTracker.RouterTrackerListener {
    protected RouterModel mRouterParam;
    private WXSDKInstance mWXInstance;
    protected ViewGroup mContainer;
    private String mPageUrl;
    private static final String TAG = "AbstractWeexActivity";
    private String mPageName;
    private String mRouterType;
    protected BMLoding mLoding;
    private BaseToolBar mNavigationBar;
    private BMFloatingLayer mDebugger;
    protected Activity mAct;
    public String[] mDebugOptions = new String[]{"调试页面", "刷新", "扫一扫"};
    private RelativeLayout rl_error;
    private ViewGroup mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAct = this;
        mRouterType = GlobalEventManager.TYPE_OPEN;
        Intent data = getIntent();
        initRouterParams(data);
        initUrl(data);
        synRouterStack();
        initDebug();
    }

    private void initDebug() {
        if (!BuildConfig.DEBUG) return;
        mDebugger = new BMFloatingLayer(mAct);
        mDebugger.setListener(new BMFloatingLayer.FloatingLayerListener() {
            @Override
            public void onClick() {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7
                        .app.AlertDialog.Builder(mAct);
                builder.setItems(mDebugOptions, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(mAct, DebugActivity.class);
                            startActivity(intent);
                        } else if (which == 1) {
                            refresh();
                        } else if (which == 2) {
                            DispatchEventManager dispatchEventManager = ManagerFactory
                                    .getManagerService(DispatchEventManager.class);
                            WeexEventBean eventBean = new WeexEventBean();
                            eventBean.setContext(mAct);
                            eventBean.setKey(WXConstant.WXEventCenter.EVENT_CAMERA);
                            dispatchEventManager.getBus().post(eventBean);
                        }
                    }
                });
                builder.create().show();
            }

            @Override
            public void onShow() {

            }

            @Override
            public void onClose() {

            }
        });
        mDebugger.show(mAct);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mRootView = (ViewGroup) View.inflate(this, R.layout.layout_root, null);
        RelativeLayout rl_root = (RelativeLayout) mRootView.findViewById(R.id.rl_root);
        rl_error = (RelativeLayout) mRootView.findViewById(R.id.rl_error);
        mNavigationBar = (BaseToolBar) mRootView.findViewById(R.id.base_navBar);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View child = View.inflate(this, layoutResID, null);
        rl_root.addView(child, params);
        StatusBarManager.setHeaderBg(mRouterParam, this);
        StatusBarManager.setStatusBarFontStyle(this, mRouterParam);
        setNavigationBar();
        setContentView(mRootView);
    }

    public View getRootView() {
        return mRootView;
    }


    public void setNavigationBar() {
        if (mNavigationBar == null) return;
        //setVisibility
        if (null == mRouterParam) {
            mNavigationBar.setNavigationVisible(false);
            return;
        }
        if (!mRouterParam.navShow) {
            mNavigationBar.setNavigationVisible(false);
            return;
        }
        mNavigationBar.setNavigationVisible(true);
        //set color
        String navBarColor = BMWXEnvironment.mPlatformConfig.getPage().getNavBarColor();
        if (!TextUtils.isEmpty(navBarColor)) {
            mNavigationBar.setBackgroundColor(Color.parseColor(navBarColor));
        }
        //set title
        String title = mRouterParam.navTitle;
        mNavigationBar.setTitle(title);

        //left icon
        mNavigationBar.setOnLeftListenner(new BaseToolBar.OnLeftIconClick() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //back
        mNavigationBar.setLeftIconVisible(mRouterParam.canBack);

        //nav item color
        mNavigationBar.setNavigationItemColor(BMWXEnvironment.mPlatformConfig.getPage()
                .getNavItemColor(), mNavigationBar);

    }


    public BaseToolBar getNavigationBar() {
        return mNavigationBar;
    }


    protected void initRouterParams(Intent data) {

        Serializable serializableExtra = data.getSerializableExtra(Constant.ROUTERPARAMS);
        if (serializableExtra instanceof RouterModel) {
            setRouterParam((RouterModel) serializableExtra);
        }


    }


    public void showError() {
        rl_error.setVisibility(View.VISIBLE);
    }


    public void hideError() {
        rl_error.setVisibility(View.GONE);
    }

    protected void initUrl(Intent data) {
        Uri pageUri = data.getData();
        if (pageUri == null) return;
        setPageUrl(pageUri.toString());
    }

    public void setRouterParam(RouterModel param) {
        this.mRouterParam = param;
    }

    public RouterModel getRouterParam() {
        return mRouterParam;
    }

    protected void synRouterStack() {
        if (mRouterParam != null) {
            if (!Constant.ACTIVITIES_ANIMATION.ANIMATION_PRESENT.equals(mRouterParam.type)) {
                onAttach(this);
            } else {
                onAttach(this, getClass().getName());
            }
        } else {
            onAttach(this);
        }
    }

    public void refresh() {
        createWXInstance();
        renderPage();
    }


    public void setPageUrl(String url) {
        this.mPageUrl = url;
    }


    public String getPageUrl() {
        return mPageUrl;
    }


    protected void renderPage() {
        if (TextUtils.isEmpty(mPageUrl)) {
            return;
        }
        createWXInstance();
        preRender();
        renderPageByURL();
        postRender();
    }

    private void renderPageByURL() {
        WXCommonUtil.throwIfNull(mContainer, new RuntimeException("Can't render page, container " +
                "is" +
                " null"));
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mPageUrl);
        mWXInstance.renderByUrl(
                getPageName(),
                mPageUrl,
                options,
                null,
                WXRenderStrategy.APPEND_ASYNC);
    }


    public String getPageName() {
        return mPageName == null ? TAG : mPageName;
    }


    public void setPageName(String pageName) {
        this.mPageName = pageName;
    }


    protected void preRender() {

    }

    protected void postRender() {

    }

    public WXSDKInstance getWXSDkInstance() {
        return mWXInstance;
    }

    protected void createWXInstance() {
        if (mWXInstance != null) {
            destroyWXInstance();
        }
        RenderContainer renderContainer = new RenderContainer(this);
        mContainer.addView(renderContainer);
        mWXInstance = new WXSDKInstance(this);
        mWXInstance.registerRenderListener(this);
        mWXInstance.setRenderContainer(renderContainer);

    }

    protected void destroyWXInstance() {
        if (mWXInstance != null) {
            Intent intent = new Intent(WXConstant.WXEventCenter.EVENT_INSTANCE_DESTORY);
            intent.putExtra("data", mWXInstance.getInstanceId());
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(intent);
            mWXInstance.registerRenderListener(null);
            mWXInstance.registerOnWXScrollListener(null);
            mWXInstance.destroy();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mRouterType = GlobalEventManager.TYPE_BACK;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXInstance != null) {
            mWXInstance.onActivityResume();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewDidAppear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWXInstance != null) {
            mWXInstance.onActivityStart();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXInstance != null) {
            mWXInstance.onActivityPause();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewWillDisappear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXInstance != null) {
            mWXInstance.onActivityStop();
        }

        if (mWXInstance != null) {
            GlobalEventManager.onViewDidDisappear(mWXInstance, mRouterType);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXInstance != null) {
            mWXInstance.onActivityDestroy();
        }
        if (mDebugger != null) {
            mDebugger.close();
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view != null && view.getParent() == null) {
            mContainer.addView(view);
        }
        mContainer.requestLayout();
        GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        //do some report
        GlobalEventManager.onViewDidAppear(mWXInstance, mRouterType);
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    @Override
    public void onAttach(Activity activity) {
        RouterTracker.push(activity);
    }

    @Override
    public void onAttach(Activity activity, String activityName) {
        RouterTracker.newInstancePush(activity, activityName);
    }

    @Override
    public void onDetach(Activity activity) {
        if (activity == this) {
            finish();
            if (mRouterParam != null) {
                String type = mRouterParam.type;
                if (Constant.ACTIVITIES_ANIMATION.ANIMATION_PUSH.equals(type)) {
                    overridePendingTransition(R.anim.view_stay, R.anim.right_out);
                } else if (Constant.ACTIVITIES_ANIMATION.ANIMATION_PRESENT.equals(type)) {
                    overridePendingTransition(R.anim.view_stay, R.anim.bottom_out);
                } else if (Constant.ACTIVITIES_ANIMATION.ANIMATION_TRANSLATION.equals(type)) {
                    overridePendingTransition(R.anim.view_stay, R.anim.left_out);
                }
            }
        }
    }

    @Override
    public void onDetach(Activity activity, String activityName) {

    }

    @Override
    public void onBackPressed() {
        if (mRouterParam != null && mRouterParam.isRunBackCallback && null != mRouterParam
                .backCallback) {
            mRouterParam.backCallback.invoke(null);
        } else {
            RouterTracker.popActivity();
        }
    }


    public BMLoding getLoading() {
        return mLoding;
    }


    public void setLoading(BMLoding bmLoading) {
        this.mLoding = bmLoading;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                handleDecodeInternally(result.getContents());
            }
        }

        switch (resultCode) {
            case ImagePicker.RESULT_CODE_ITEMS:
                if (data != null && requestCode == Constant.ImageConstants.IMAGE_PICKER) {
                    ArrayList<ImageItem> items = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    int newWidth = data.getIntExtra(Constant.ImageConstants
                            .UPLOADIMAGERBEAN_WITH, 0);
                    ImageManager imageManager = ManagerFactory.getManagerService(ImageManager
                            .class);
                    imageManager.UpMultipleImageData(this, items, newWidth);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleDecodeInternally(String code) {
        if (!TextUtils.isEmpty(code)) {
            Uri uri = Uri.parse(code);
            if (uri.getQueryParameterNames().contains("bundle")) {
                WXEnvironment.sDynamicMode = uri.getBooleanQueryParameter("debug", false);
                WXEnvironment.sDynamicUrl = uri.getQueryParameter("bundle");
                String tip = WXEnvironment.sDynamicMode ? "Has switched to Dynamic Mode" : "Has " +
                        "switched to Normal Mode";
                Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
                finish();
                return;
            } else if (uri.getQueryParameterNames().contains("_wx_devtool")) {
                WXEnvironment.sRemoteDebugProxyUrl = uri.getQueryParameter("_wx_devtool");
                WXSDKEngine.reload();
                Toast.makeText(this, "devtool", Toast.LENGTH_SHORT).show();
                finish();
                return;
            } else if (code.contains("_wx_debug")) {
                uri = Uri.parse(code);
                String debug_url = uri.getQueryParameter("_wx_debug");
                WXSDKEngine.switchDebugModel(true, debug_url);
                finish();
            } else {
                CameraResultBean bean = new CameraResultBean();
                if (!TextUtils.isEmpty(code)) {
                    bean.msg = "success";
                    bean.resCode = 0;
                    CameraResultBean.Result data = new CameraResultBean.Result();
                    data.text = code;
                    bean.data = data;
                } else {
                    bean.msg = "fail";
                    bean.resCode = 9;
                }

                ManagerFactory.getManagerService(DispatchEventManager.class).getBus
                        ().post(bean);
            }
        }
    }
}
