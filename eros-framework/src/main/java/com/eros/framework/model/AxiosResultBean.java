package com.eros.framework.model;

import java.io.Serializable;

/**
 * Axios 数据返回类
 */
public class AxiosResultBean implements Serializable {
    public Object status;
    public String errorMsg = "";
    public Object data;
    public Object header;

    public AxiosResultBean() {
    }

    public AxiosResultBean(Object status, String errorMsg, Object data, Object header) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.data = data;
        this.header = header;
    }
}
