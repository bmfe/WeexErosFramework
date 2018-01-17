package com.benmu.framework.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyuanxiao on 18/1/16.
 */

public class TextUtil {
    public static List<Object> conversionObject(List<String> data) {
        List<Object> mData = new ArrayList<>();
        for (String d : data) {
            mData.add(JSON.parse(d));
        }
        return mData;

    }
}
