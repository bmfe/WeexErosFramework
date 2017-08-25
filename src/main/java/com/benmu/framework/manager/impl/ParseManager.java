package com.benmu.framework.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.benmu.framework.manager.Manager;

/**
 * Created by Carry on 2017/8/7. json parse
 */

public class ParseManager extends Manager {
    public <T> T parseObject(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    public String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public JSONObject parseObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }

}
