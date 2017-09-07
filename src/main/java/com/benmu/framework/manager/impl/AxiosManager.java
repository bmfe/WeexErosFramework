package com.benmu.framework.manager.impl;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.UpLoadImage;
import com.benmu.framework.model.UploadResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by Carry on 2017/8/7. default implement by okHttpUtils
 */

public class AxiosManager extends Manager {

    public void cancel(Object tag) {

    }

    public void get(String mUrl, HashMap<String, String> params, HashMap<String, String> header,
                    StringCallback stringCallback, Object tag) {
        if (mUrl == null) {
            return;
        }
        if (!safeUrl(mUrl)) {
            return;
        }
        if (header == null) {
            header = new HashMap<>();
        }
        GetBuilder builder = OkHttpUtils.get().url(mUrl).tag(tag).headers(header);
        generateParams(params, builder);
        builder.build().execute(stringCallback);
    }

    private boolean safeUrl(String origin) {
        Uri parse = Uri.parse(origin);
        return !(TextUtils.isEmpty(parse.getScheme()) || TextUtils.isEmpty(parse.getHost()));
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


    public void post(String url, String data, HashMap<String, String> header, StringCallback
            stringCallback, Object tag) {
        OkHttpUtils.postString().url(url).content(data).mediaType(MediaType
                .parse("application/json; charset=utf-8")).headers(header).tag(tag).build()
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
