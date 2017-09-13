package com.benmu.framework.event.http;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.AxiosManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.AxiosGet;
import com.benmu.framework.model.AxiosPost;
import com.benmu.framework.model.BaseResultBean;
import com.benmu.framework.utils.L;
import com.taobao.weex.bridge.JSCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Carry on 2017/8/16.
 */

public class EventFetch {
    public void fetch(String params, Context context, final JSCallback jscallback) {

        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        AxiosManager axiosManager = ManagerFactory.getManagerService(AxiosManager.class);
        JSONObject object = parseManager.parseObject(params);
        final String mUrl = object.getString("url");

        Boolean noRepeat = object.getBoolean("noRepeat");
        if (noRepeat != null && noRepeat) {
            axiosManager.cancel(mUrl);
        }

        if ("GET".equals(object.getString("method"))) {
            AxiosGet axiosGet = parseManager.parseObject(params, AxiosGet.class);

            axiosManager.get(mUrl, axiosGet.data, axiosGet.header, new
                    StringCallback() {

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            //请求被cancel不再抛出错误
                            if (e != null && "Canceled".equalsIgnoreCase(e.getLocalizedMessage())) {
                                return;
                            }
                            String message = e.getMessage();
                            if (jscallback != null) {
                                jscallback.invoke(new BaseResultBean(404, "网络错误"));
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (jscallback != null) {
                                L.e("http", response);
                                jscallback.invoke(JSONObject.parse(response));
                            }
                        }
                    }, axiosGet.url);
        } else if ("POST".equals(object.getString("method"))) {
            AxiosPost axiosPost = parseManager.parseObject(params, AxiosPost.class);

            axiosManager.post(axiosPost.url, axiosPost.data, axiosPost.header, new
                    StringCallback() {


                        @Override
                        public void onError(Call call, Exception e, int id) {
                            //请求被cancel不再抛出错误
                            if (e != null && "Canceled".equalsIgnoreCase(e.getLocalizedMessage())) {
                                return;
                            }
                            String message = e.getMessage();
                            if (jscallback != null) {
                                jscallback.invoke(new BaseResultBean(404, "网络错误"));
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (jscallback != null) {
                                jscallback.invoke(JSONObject.parse(response));
                            }
                        }
                    }, axiosPost.url);
        }
    }
}
