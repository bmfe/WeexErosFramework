package com.benmu.framework.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.R;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.GlobalEventManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.utils.DebugableUtil;
import com.benmu.framework.utils.InsertEnvUtil;
import com.benmu.framework.utils.WXAnalyzerDelegate;
import com.benmu.framework.utils.WXCommonUtil;
import com.benmu.widget.view.DebugErrorDialog;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.RenderContainer;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyuanxiao on 2018/5/21.
 */

public class AbstractWeexFragment extends Fragment implements IWXRenderListener {
    protected Activity mAct;
    private String mPageUrl;
    protected ViewGroup mContainer;
    private WXSDKInstance mWXInstance;
    private static final String TAG = "AbstractWeexFragment";
    private String mPageName;
    protected WXAnalyzerDelegate mWxAnalyzerDelegate;
    private DebugErrorDialog errorDialog;
    private String mRouterType;
    private boolean isFirstRun = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAct = getActivity();
        mRouterType = GlobalEventManager.TYPE_OPEN;
        mWxAnalyzerDelegate = new WXAnalyzerDelegate(getActivity());
        mWxAnalyzerDelegate.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWXInstance != null && isViewDid()) {
            GlobalEventManager.onViewDidAppear(mWXInstance, mRouterType);
        }
        isFirstRun = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mWXInstance != null && isViewDid()) {
            GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWXInstance != null && isViewDid()) {
            GlobalEventManager.onViewWillDisappear(mWXInstance, mRouterType);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mWXInstance != null && isViewDid()) {
            GlobalEventManager.onViewDidDisappear(mWXInstance, mRouterType);
        }
        mRouterType = GlobalEventManager.TYPE_BACK;

    }

    public void renderPage() {
        if (TextUtils.isEmpty(mPageUrl)) {
            return;
        }
        createWXInstance();
        preRender();
        renderPageByURL();
        postRender();
    }

    protected void createWXInstance() {
        if (mWXInstance != null) {
            destroyWXInstance();
        }
        RenderContainer renderContainer = new RenderContainer(mAct);
        mContainer.addView(renderContainer);
        mWXInstance = new WXSDKInstance(mAct);
        mWXInstance.registerRenderListener(this);
        mWXInstance.setRenderContainer(renderContainer);

    }

    protected void destroyWXInstance() {
        if (mWXInstance != null) {
            Intent intent = new Intent(WXEventCenter.EVENT_INSTANCE_DESTORY);
            intent.putExtra("data", mWXInstance.getInstanceId());
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(intent);
            mWXInstance.registerRenderListener(null);
            mWXInstance.registerOnWXScrollListener(null);
            mWXInstance.destroy();
        }
    }

    private void renderPageByURL() {
        WXCommonUtil.throwIfNull(mContainer, new RuntimeException("Can't render page, container " +
                "is" +
                " null"));
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mPageUrl);
        InsertEnvUtil.customerRender(options);
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

    protected void preRender() {

    }

    protected void postRender() {

    }

    /*****mWXInstance.registerRenderListener*****/
    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        View wrappedView = null;
        if (mWxAnalyzerDelegate != null) {
            wrappedView = mWxAnalyzerDelegate.onWeexViewCreated(mWXInstance, view);
        }

        if (wrappedView != null) {
            view = wrappedView;
        }
        if (view != null && view.getParent() == null) {
            mContainer.addView(view);
        }
        if (view instanceof RenderContainer) {
            RenderContainer container = (RenderContainer) view;
            int childCount = container.getChildCount();
            if (childCount > 0) {
                container.getChildAt(0).setBackgroundColor(ContextCompat.getColor(getActivity(), R.color
                        .c_eff3f4));
            }
        }
        mContainer.requestLayout();
        GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
    }

    public int getWxInstanseHasCode() {
        if (mWXInstance != null) {
            return mWXInstance.hashCode();
        }
        return -1;
    }

    public WXSDKInstance getWXSDKInstance() {
        return mWXInstance;
    }

    public void setPageUrl(String url) {
        Uri pathUri = Uri.parse(url);
        if (!TextUtils.equals("http", pathUri.getScheme()) && !TextUtils.equals("https", pathUri
                .getScheme())) {
            url = BMWXEnvironment.mPlatformConfig.getUrl().getJsServer() +
                    "/dist/js" + url;
        }
        this.mPageUrl = url;
    }

    public void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DispatchEventManager dispatchEventManager = ManagerFactory.getManagerService
                        (DispatchEventManager.class);
                Intent intent = new Intent(WXConstant.ACTION_WEEX_REFRESH);
                intent.putExtra("instanceId", mWXInstance.getInstanceId());
                dispatchEventManager.getBus().post(intent);
                createWXInstance();
                renderPage();
            }
        });

    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        //do some report
        GlobalEventManager.onViewDidAppear(mWXInstance, mRouterType);

        if (mWxAnalyzerDelegate != null) {
            mWxAnalyzerDelegate.onWeexRenderSuccess(instance);
        }
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        if (!DebugableUtil.isDebug()) return;
        if (errorDialog == null) {
            errorDialog = new DebugErrorDialog();
            errorDialog.createErrorDialog(mAct);
        }
        String errorMsg = "errCode -> " + errCode + " msg -> " + msg;
        errorDialog.setTextMsg(errorMsg);
        errorDialog.show();

        if (mWxAnalyzerDelegate != null) {
            mWxAnalyzerDelegate.onException(instance, errCode, msg);
        }
    }

    /*****mWXInstance.registerRenderListener*****/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && GlobalEventManager.TYPE_BACK.equals(mRouterType)) {
            if (mWXInstance != null) {
                GlobalEventManager.onViewWillAppear(mWXInstance, mRouterType);
            }
        }
        if (!isVisibleToUser) {
            if (mWXInstance != null) {
                GlobalEventManager.onViewDidDisappear(mWXInstance, mRouterType);
            }
        }
    }

    private boolean isViewDid() {
        if (GlobalEventManager.TYPE_OPEN.endsWith(mRouterType)) {
            return true;
        }
        if (getUserVisibleHint() && GlobalEventManager.TYPE_BACK.endsWith(mRouterType)) {
            return true;
        }
        return false;
    }
}
