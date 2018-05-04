package com.benmu.framework.extend.hook;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.benmu.framework.BMWXApplication;
import com.benmu.framework.extend.adapter.DefaultTypefaceAdapter;
import com.benmu.framework.extend.adapter.DefaultWXHttpAdapter;
import com.benmu.framework.utils.IOUtil;
import com.benmu.framework.utils.TextUtil;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.common.WXResponse;
import com.taobao.weex.utils.WXLogUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by Carry on 2018/4/16.
 */

public class TypeFaceHandler {
    private final static String TAG = "TypeFaceHandler";

    private static void loadLocalIncon(File localIcon, IWXHttpAdapter.OnHttpListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(localIcon);
            byte[] bytes = IOUtil.readInputStream(inputStream);
            WXResponse response = new WXResponse();
            response.statusCode = "200";
            response.originalData = bytes;
            if (listener != null) {
                listener.onHttpFinish(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load(String url, IWXHttpAdapter.OnHttpListener listener) {
        Uri parse = Uri.parse(url);
        listener.onHttpStart();
        DefaultTypefaceAdapter typefaceAdapter = BMWXApplication.getWXApplication()
                .getTypefaceAdapter();
        if (typefaceAdapter == null) {
            Log.e(TAG, "未找到支持bmLocal的adapter");
            return;
        }
        if ("bmlocal".equalsIgnoreCase(parse.getScheme())) {
            //bmlocal
            if (typefaceAdapter.isInterceptor()) {
                //load local
                File iconDir = typefaceAdapter.getTypefaceDir();
                if (!iconDir.exists()) return;
                File localIcon = new File(iconDir, parse.getPath());
                if (!localIcon.exists()) {
                    //local icon not exists
                    WXResponse response = new WXResponse();
                    response.statusCode = "404";
                    if (listener != null) {
                        listener.onHttpFinish(response);
                    }
                    return;
                }
                loadLocalIncon(localIcon, listener);
            } else {
                //fetch on Js server
                String iconDownloadUrl = typefaceAdapter.getJsServer() + "/dist";
                if (TextUtils.isEmpty(iconDownloadUrl)) return;
                String fetchUrl = iconDownloadUrl + "/" + parse.getHost() + parse.getPath();
                fetchIcon(fetchUrl, listener);
            }
        } else if ("http".equalsIgnoreCase(parse.getScheme()) || "https".equalsIgnoreCase(parse
                .getScheme())) {
            //http
            WXRequest wxRequest = new WXRequest();
            wxRequest.url = url;
            wxRequest.method = "GET";
            requestRemoteIcon(wxRequest, listener);
        }
    }


    private static void requestRemoteIcon(final WXRequest request, final IWXHttpAdapter
            .OnHttpListener
            listener) {
        final WXResponse wxResponse = new WXResponse();
        String method = request.method;
        String requestBodyString = request.body == null ? "{}" : request.body;
        RequestBody body = null;
        if (request.paramMap != null && request.paramMap.containsKey("Content-Type")) {
            body = HttpMethod.requiresRequestBody(method)
                    ? RequestBody.create(MediaType.parse(request.paramMap.get("Content-Type")),
                    requestBodyString) : null;
        } else {
            body = HttpMethod.requiresRequestBody(method)
                    ? RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                    "charset=UTF-8"), requestBodyString) : null;
        }
        Request.Builder requestBuilder = new Request.Builder()
                .url(request.url)
                .method(method, body);
        if (request.paramMap != null) {
            for (Map.Entry<String, String> param : request.paramMap.entrySet()) {
                requestBuilder.addHeader(param.getKey(), TextUtil.toHumanReadableAscii(param
                        .getValue()));
            }
        }
        DefaultWXHttpAdapter adapter = (DefaultWXHttpAdapter) WXSDKManager.getInstance()
                .getIWXHttpAdapter();
        OkHttpClient httpClient = adapter.getHttpClient();
        httpClient.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                wxResponse.errorMsg = e.getMessage();
                wxResponse.errorCode = "-1";
                wxResponse.statusCode = "-1";
                if (listener != null) {
                    listener.onHttpFinish(wxResponse);
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                byte[] responseBody = new byte[0];
                try {
                    responseBody = response.body().bytes();
                } catch (IOException e) {
                    e.printStackTrace();
                    wxResponse.errorMsg = e.getMessage();
                    wxResponse.errorCode = "-1";
                    wxResponse.statusCode = "-1";
                    if (listener != null) {
                        listener.onHttpFinish(wxResponse);
                    }
                }
                wxResponse.data = new String(responseBody);
                wxResponse.statusCode = String.valueOf(response.code());
                wxResponse.originalData = responseBody;
                wxResponse.extendParams = new HashMap<>();
                for (Map.Entry<String, List<String>> entry : response.headers().toMultimap()
                        .entrySet()) {
                    wxResponse.extendParams.put(entry.getKey(), entry.getValue());
                }

                if (response.code() < 200 || response.code() > 299) {
                    wxResponse.errorMsg = response.message();
                    if (listener != null) {
                        listener.onHttpFinish(wxResponse);
                    }
                } else {
                    //success
                    if (listener != null) {
                        listener.onHttpFinish(wxResponse);
                    }
                }
            }
        });
    }


    private static void fetchIcon(String url, IWXHttpAdapter.OnHttpListener listener) {
        IWXHttpAdapter adapter = WXSDKManager.getInstance().getIWXHttpAdapter();
        if (adapter == null) {
            WXLogUtils.e(TAG, "downloadFontByNetwork() IWXHttpAdapter == null");
            return;
        }
        WXRequest request = new WXRequest();
        request.url = url;
        request.method = "GET";
        adapter.sendRequest(request, listener);
    }


}
