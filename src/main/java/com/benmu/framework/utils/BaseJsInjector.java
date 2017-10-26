package com.benmu.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.http.okhttp.callback.StringCallback;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.AxiosManager;
import com.benmu.framework.manager.impl.FileManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import okhttp3.Call;

/**
 * Created by Carry on 2017/10/25.
 */

public class BaseJsInjector {
    private static BaseJsInjector mInstance = new BaseJsInjector();
    private String mBaseJs;
    private static final String BASE_JS_NAME = "base.js";
    private InjectJsListener mListener;
    private static final String REMOTE_URL = BMWXEnvironment.mPlatformConfig.getUrl().getJsServer
            () + "/config/base.js";

    public static BaseJsInjector getInstance() {
        return mInstance;
    }

    private BaseJsInjector() {
        DispatchEventManager dispatchEventManager = ManagerFactory.getManagerService
                (DispatchEventManager.class);
        dispatchEventManager.getBus().register(this);
    }

    public void injectBaseJs(Context context, final String origin) {
        if (Constant.INTERCEPTOR_ACTIVE.equals(SharePreferenceUtil.getInterceptorActive(context))) {
            if (TextUtils.isEmpty(mBaseJs)) {
                loadFromAssets(context);
            }
            //注入basejs
            if (TextUtils.isEmpty(mBaseJs)) {
                if (mListener != null) {
                    mListener.onInjectError();
                }
                return;
            }
            insert(origin);
        } else {
            //去本地服务下载后注入
            if (TextUtils.isEmpty(mBaseJs)) {
                AxiosManager axiosManager = ManagerFactory.getManagerService(AxiosManager.class);
                axiosManager.get(REMOTE_URL, null, null, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        L.e("BaseJsInjector", "远端base.js请求失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mBaseJs = response;
                        insert(origin);
                    }
                }, REMOTE_URL);
            } else {
                insert(origin);
            }
        }
    }


    private void insert(String origin) {
        StringBuilder builder = new StringBuilder();
        builder.append(mBaseJs).append("\n").append(origin);
        //注入完成 回调页面
        if (mListener != null) {
            mListener.onInjectFinish(origin, builder.toString());
        }
    }

    private void loadFromAssets(Context context) {
        File baseJs = new File(FileManager.getBaseJsDir(context), BASE_JS_NAME);
        if (baseJs.exists()) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(baseJs);
                byte[] bytes = IOUtil.readInputStream(inputStream);
                if (bytes != null) {
                    mBaseJs = new String(bytes, "UTF-8");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe
    public void onRefresh(Intent intent) {
        if (intent != null && WXConstant.ACTION_WEEX_REFRESH.equals(intent.getAction())) {
            //页面刷新了 如果这时候拦截器关闭将mbaseJs=null
            if (Constant.INTERCEPTOR_DEACTIVE.equals(SharePreferenceUtil.getInterceptorActive
                    (BMWXEnvironment.mApplicationContext))) {
                this.mBaseJs = null;
            }
        }
    }


    public void setInjectListener(InjectJsListener listener) {
        this.mListener = listener;
    }


    public interface InjectJsListener {
        void onInjectStart(String origin);

        void onInjectFinish(String origin, String result);

        void onInjectError();
    }
}
