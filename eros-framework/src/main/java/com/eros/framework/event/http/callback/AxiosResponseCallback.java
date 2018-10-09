package com.eros.framework.event.http.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.eros.framework.http.okhttp.callback.Callback;
import com.eros.framework.model.AxiosResultBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by Carry on 2018/5/28.
 */

public abstract class AxiosResponseCallback extends Callback<AxiosResultBean> {


    @Override
    public AxiosResultBean parseNetworkResponse(Response response, int id) throws Exception {
        Map<String, List<String>> responseHeader = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap()
                .entrySet()) {
            responseHeader.put(entry.getKey(), entry.getValue());
        }
        try {
            return new AxiosResultBean(response.code(), "", JSON.parse(response.body().string()),
                    responseHeader);
        } catch (JSONException e) {
            e.printStackTrace();
            return new AxiosResultBean(-1, "json解析错误", null, responseHeader);
        }

    }
}
