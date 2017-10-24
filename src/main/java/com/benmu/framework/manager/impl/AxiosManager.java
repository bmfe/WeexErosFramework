package com.benmu.framework.manager.impl;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.http.Api;
import com.benmu.framework.http.okhttp.OkHttpUtils;
import com.benmu.framework.http.okhttp.builder.GetBuilder;
import com.benmu.framework.http.okhttp.builder.OkHttpRequestBuilder;
import com.benmu.framework.http.okhttp.builder.OtherRequestBuilder;
import com.benmu.framework.http.okhttp.builder.PostFormBuilder;
import com.benmu.framework.http.okhttp.callback.FileCallBack;
import com.benmu.framework.http.okhttp.callback.StringCallback;
import com.benmu.framework.http.okhttp.exception.IrregularUrlException;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.UpLoadImage;
import com.benmu.framework.model.UploadResultBean;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by Carry on 2017/8/7. default implement by okHttpUtils
 */

public class AxiosManager extends Manager {
    private static final String DEFAULT_MEDIATYPE = "application/json; charset=utf-8";
    private static final String DEFAULT_HOST = "http://app.weex-eros.com";

    public void cancel(Object tag) {

    }

    public void get(String mUrl, HashMap<String, String> params, HashMap<String, String> header,
                    StringCallback stringCallback, Object tag) {

        mUrl = safeUrl(mUrl);
        if (mUrl == null) {
            if (stringCallback != null) {
                stringCallback.onError(null, new IrregularUrlException("url不合法"), 0);
            }
            return;
        }
        if (header == null) {
            header = new HashMap<>();
        }
        GetBuilder builder = OkHttpUtils.get().url(mUrl).tag(tag).headers(header);
        generateParams(params, builder);
        builder.build().execute(stringCallback);
    }

    public void put(String url, String content, HashMap<String, String> header, StringCallback
            callBack, Object tag) {
        url = safeUrl(url);
        if (url == null) {
            if (callBack != null) {
                callBack.onError(null, new IrregularUrlException("url不合法"), 0);
            }
            return;
        }
        if (header == null) {
            header = new HashMap<>();
        }
        OtherRequestBuilder builder = OkHttpUtils.put().url(url).tag(tag).headers(header);
        if (content != null) {
            builder.requestBody(createRequestBodyByMediaType(header, content));
        }
        builder.build().execute(callBack);
    }

    public void delete(String url, String content, HashMap<String, String> header, StringCallback
            callBack, Object tag) {
        url = safeUrl(url);
        if (url == null) {
            if (callBack != null) {
                callBack.onError(null, new IrregularUrlException("url不合法"), 0);
            }
            return;
        }

        if (header == null) {
            header = new HashMap<>();
        }
        OtherRequestBuilder builder = OkHttpUtils.delete().url(url).tag(tag).headers(header);
        if (content != null) {
            builder.requestBody(createRequestBodyByMediaType(header, content));
        }
        builder.build().execute(callBack);
    }

    public void patch(String url, String content, HashMap<String, String> header, StringCallback
            callback, Object tag) {
        url = safeUrl(url);

        if (url == null) {
            if (callback != null) {
                callback.onError(null, new IrregularUrlException("url不合法"), 0);
            }
            return;
        }

        if (header == null) {
            header = new HashMap<>();
        }
        OtherRequestBuilder builder = OkHttpUtils.patch().url(url).tag(tag).headers(header);
        if (content != null) {
            builder.requestBody(createRequestBodyByMediaType(header, content));
        }
        builder.build().execute(callback);
    }

    public void head(String url, HashMap<String, String> params, HashMap<String, String> header,
                     StringCallback callback, Object tag) {
        url = safeUrl(url);

        if (url == null) {
            if (callback != null) {
                callback.onError(null, new IrregularUrlException("url不合法"), 0);
            }
            return;
        }

        if (header == null) {
            header = new HashMap<>();
        }
        if (params == null) {
            params = new HashMap<>();
        }
        OkHttpUtils.head().url(url).tag(tag).headers(header).params(params).build().execute
                (callback);

    }

    private RequestBody createRequestBodyByMediaType(Map<String, String> header, String content) {
        if (header != null && !TextUtils.isEmpty(header.get("Content-Type"))) {
            String s = header.get("Content-Type");
            MediaType mediaType = null;
            try {
                mediaType = MediaType.parse(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mediaType == null) {
                mediaType = MediaType.parse(DEFAULT_MEDIATYPE);
            }
            return RequestBody.create(mediaType, content);
        }
        return RequestBody.create(MediaType.parse(DEFAULT_MEDIATYPE), content);
    }

    private String safeUrl(String origin) {
        if (origin == null) return null;
        Uri parse = Uri.parse(origin);
        StringBuilder builder = new StringBuilder();
        Uri requestHost = Uri.parse(TextUtils.isEmpty(Api.BASE_URL) ? DEFAULT_HOST : Api.BASE_URL);
        if (TextUtils.isEmpty(parse.getScheme())) {
            builder.append(requestHost.getScheme());
        } else {
            builder.append(parse.getScheme());
        }
        builder.append("://");
        if (TextUtils.isEmpty(parse.getHost())) {
            builder.append(requestHost.getHost());
        } else {
            builder.append(parse.getHost());
        }
        if (parse.getPort() != -1) {
            builder.append(":").append(parse.getPort());
        }

        if (!TextUtils.isEmpty(parse.getPath())) {
            builder.append(origin.substring(origin.indexOf(parse.getPath())));
        }
        String finalUrl = builder.toString();

        return HttpUrl.parse(finalUrl) == null ? null : builder.toString();
    }


    private void generateParams(Map<String, String> params, OkHttpRequestBuilder builder) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (builder instanceof GetBuilder) {
            GetBuilder getBuilder = (GetBuilder) builder;

            for (Map.Entry<String, String> entry : params.entrySet()) {
                getBuilder.addParams(entry.getKey().trim(), entry.getValue().trim());
            }
        }

    }


    public void post(String mUrl, String data, HashMap<String, String> header, StringCallback
            stringCallback, Object tag) {
        mUrl = safeUrl(mUrl);
        if (mUrl == null) {
            if (stringCallback != null) {
                stringCallback.onError(null, new IrregularUrlException("url不合法"), 0);
            }
            return;
        }

        String contentType = null;
        if (header != null) {
            contentType = header.get("Content-Type");
        }
        if (TextUtils.isEmpty(contentType)) {
            contentType = DEFAULT_MEDIATYPE;
        }

        OkHttpUtils.postString().url(mUrl).content(data).mediaType(MediaType
                .parse(contentType)).headers(header).tag(tag).build()
                .execute(stringCallback);
    }


    public void upload(String url, List<String> fileMap, Map<String, String> uploadParams,
                       Map<String, String> heads) {
        if (fileMap == null) {
            return;
        }
        UploadImageCallback callback = new UploadImageCallback(fileMap.size());
        for (String filePath : fileMap) {
            upload(url, filePath, uploadParams, heads, callback);
        }
    }

    private void upload(String url, String filePath, Map<String, String> uploadParams,
                        Map<String, String> heads, StringCallback callback) {
        PostFormBuilder builder = OkHttpUtils.post().url(url).params(uploadParams).headers(heads);
        builder.addFile("file", "file", new File(filePath));
        builder.build().execute(callback);
    }


    public void download(String url, FileCallBack fileCallBack) {
        OkHttpUtils.get().url(url).build().execute(fileCallBack);
    }


    /**
     * 图片上传回调
     */
    private class UploadImageCallback extends StringCallback {

        private int fileUrlMapSize;
        private ArrayList<String> arrayList;

        public UploadImageCallback(int fileUrlMapSize) {
            this.fileUrlMapSize = fileUrlMapSize;
            arrayList = new ArrayList();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            DispatchEventManager dispatchEventManager = ManagerFactory.getManagerService
                    (DispatchEventManager.class);
            dispatchEventManager.getBus().post(resultBean(9, "上传文件失败！！！", arrayList));
        }

        @Override
        public void onResponse(String response, int id) {
            String resourceId = ManagerFactory.getManagerService(ParseManager.class).parseObject
                    (response, UpLoadImage.class)
                    .getData().getResourceId();
            if (!TextUtils.isEmpty(resourceId)) {
                String url = BMWXEnvironment.mPlatformConfig.getUrl().getImage() + resourceId;
                arrayList.add(url);
            }
            if (arrayList.size() == fileUrlMapSize) {
                ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                        (resultBean(0, "", arrayList));
            }
        }
    }


    /**
     * 组合返回给前端的Js 数据
     */
    public UploadResultBean resultBean(int code, String message, ArrayList<String> arrayList) {
        UploadResultBean uploadResultBean = new UploadResultBean();
        uploadResultBean.resCode = code;
        uploadResultBean.msg = message;
        uploadResultBean.setData(arrayList);
        return uploadResultBean;
    }


}
