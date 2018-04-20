package com.benmu.framework.extend.comoponents.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.taobao.weex.ui.view.IWebView;
import com.taobao.weex.ui.view.WXWebView;
import com.taobao.weex.utils.WXLogUtils;

/**
 * Created by Carry on 2018/4/19.
 */

public class BMWebView implements IWebView {
    private Context mContext;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private boolean mShowLoading = true;
    private OnErrorListener mOnErrorListener;
    private OnPageListener mOnPageListener;

    public BMWebView(Context context) {
        this.mContext = context;
    }


    public View getView() {
        FrameLayout root = new FrameLayout(this.mContext);
        root.setBackgroundColor(-1);
        this.mWebView = new WebView(this.mContext);
        FrameLayout.LayoutParams wvLayoutParams = new FrameLayout.LayoutParams(-1, -1);
        wvLayoutParams.gravity = 17;
        this.mWebView.setLayoutParams(wvLayoutParams);
        root.addView(this.mWebView);
        this.initWebView(this.mWebView);
        this.mProgressBar = new ProgressBar(this.mContext);
        this.showProgressBar(false);
        FrameLayout.LayoutParams pLayoutParams = new FrameLayout.LayoutParams(-2, -2);
        this.mProgressBar.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = 17;
        root.addView(this.mProgressBar);
        return root;
    }

    public void destroy() {
        if (this.getWebView() != null) {
            this.getWebView().removeAllViews();
            this.getWebView().destroy();
            this.mWebView = null;
        }

    }

    public void loadUrl(String url) {
        if (this.getWebView() != null) {
            this.getWebView().loadUrl(url);
        }
    }

    public void reload() {
        if (this.getWebView() != null) {
            this.getWebView().reload();
        }
    }

    public void goBack() {
        if (this.getWebView() != null) {
            this.getWebView().goBack();
        }
    }

    public void goForward() {
        if (this.getWebView() != null) {
            this.getWebView().goForward();
        }
    }

    public void setShowLoading(boolean shown) {
        this.mShowLoading = shown;
    }

    public void setOnErrorListener(OnErrorListener listener) {
        this.mOnErrorListener = listener;
    }

    public void setOnPageListener(OnPageListener listener) {
        this.mOnPageListener = listener;
    }

    private void showProgressBar(boolean shown) {
        if (this.mShowLoading) {
            this.mProgressBar.setVisibility(shown ? View.VISIBLE : View.GONE);
        }

    }

    private void showWebView(boolean shown) {
        this.mWebView.setVisibility(shown ? View.VISIBLE : View.GONE);
    }

    public WebView getWebView() {
        return this.mWebView;
    }

    private void initWebView(WebView wv) {
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                WXLogUtils.v("tag", "onPageOverride " + url);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                WXLogUtils.v("tag", "onPageStarted " + url);
                if (BMWebView.this.mOnPageListener != null) {
                    BMWebView.this.mOnPageListener.onPageStart(url);
                }

            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WXLogUtils.v("tag", "onPageFinished " + url);
                if (BMWebView.this.mOnPageListener != null) {
                    BMWebView.this.mOnPageListener.onPageFinish(url, view.canGoBack(), view
                            .canGoForward());
                }

            }

            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (BMWebView.this.mOnErrorListener != null) {
                    BMWebView.this.mOnErrorListener.onError("error", "page error");
                }

            }

            public void onReceivedHttpError(WebView view, WebResourceRequest request,
                                            WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (BMWebView.this.mOnErrorListener != null) {
                    BMWebView.this.mOnErrorListener.onError("error", "http error");
                }

            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                if (BMWebView.this.mOnErrorListener != null) {
                    BMWebView.this.mOnErrorListener.onError("error", "ssl error");
                }

            }
        });
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                BMWebView.this.showWebView(newProgress == 100);
                BMWebView.this.showProgressBar(newProgress != 100);
                WXLogUtils.v("tag", "onPageProgressChanged " + newProgress);
            }

            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (BMWebView.this.mOnPageListener != null) {
                    BMWebView.this.mOnPageListener.onReceivedTitle(view.getTitle());
                }

            }
        });
    }

}
