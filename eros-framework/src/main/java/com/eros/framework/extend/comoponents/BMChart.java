package com.eros.framework.extend.comoponents;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.eros.framework.BMWXEnvironment;
import com.eros.framework.extend.comoponents.view.BMWebView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.IWebView;
import com.taobao.weex.utils.WXUtils;


import java.util.Date;

/**
 * Created by Carry on 2017/11/16.
 */

public class BMChart extends WXComponent implements IWebView.OnPageListener {

    private BMWebView mWebView;
    private WebView mWeb;
    private String mCharInfo;
    private boolean mLoadFinish;
    private String mUrl;
    private static final String INSIDE_URL = "file:///android_asset/bm-chart.html";

    public BMChart(WXSDKInstance instance, WXVContainer parent, int type, BasicComponentData basicComponentData) {
        super(instance, parent, type, basicComponentData);
        init();
    }

    public BMChart(WXSDKInstance instance, WXVContainer parent, String
            instanceId, boolean isLazy, BasicComponentData basicComponentData) {
        super(instance, parent, instanceId, isLazy, basicComponentData);
        init();
    }

    public BMChart(WXSDKInstance instance, WXVContainer parent, boolean isLazy, BasicComponentData basicComponentData) {
        super(instance, parent, isLazy, basicComponentData);
        init();
    }

    public BMChart(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
        init();
    }

    private void init() {
        mWebView = new BMWebView(getContext());
    }

    private String getUrl(String url) {
        return TextUtils.isEmpty(url) ? INSIDE_URL : getAssetsPath(url);
    }

    private String getAssetsPath(String path) {
        Uri uri = Uri.parse(path);
        if ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme())) {
            return path;
        }
        if ("bmlocal".equalsIgnoreCase(uri.getScheme())) {
            return BMWXEnvironment.loadBmLocal(getContext(), uri);
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return path;
        }
        return INSIDE_URL;
    }

    @Override
    protected View initComponentHostView(@NonNull Context context) {
        View view = mWebView.getView();
        mWeb = mWebView.getWebView();
        view.setBackgroundColor(Color.TRANSPARENT);
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setOnPageListener(this);
        String url = null;
        url = WXUtils.getString(getAttrs().get(Constants.Name.SRC), null);
        mUrl = url;
        mWeb.loadUrl(getUrl(url));
        mWeb.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return false;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                String message = consoleMessage.message();
                Log.e("onConsoleMessage", ">>>>>>" + message);
                return super.onConsoleMessage(consoleMessage);
            }
        });
        return view;
    }


    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
    }

    @Override
    public void onReceivedTitle(String title) {

    }

    @Override
    public void onPageStart(String url) {

    }

    @WXComponentProp(name = "options")
    public void setChartInfo(String info) {
        this.mCharInfo = info;
        executeSetOptions();
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String path) {
        if (!TextUtils.isEmpty(path) && !path.equals(mUrl)) {
            mWeb.loadUrl(getUrl(path));
            mUrl = path;
        }
    }

    @JSMethod
    public void setOptions(String info) {
        this.mCharInfo = info;
        executeSetOptions();
    }


    @Override
    public void onPageFinish(String url, boolean canGoBack, boolean canGoForward) {
        Date e = new Date();
        mLoadFinish = true;
        executeSetOptions();
    }


    public void executeSetOptions() {
        if (!mLoadFinish) return;
        if (!TextUtils.isEmpty(mCharInfo)) {
            mWeb.loadUrl("javascript:setOption(" + mCharInfo + ")");
            fireEvent("finish");
        }
    }

}
