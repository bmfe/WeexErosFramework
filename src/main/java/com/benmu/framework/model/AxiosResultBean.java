package com.benmu.framework.model;

import java.io.Serializable;

/**
 * Axios 数据返回类
 */
public class AxiosResultBean implements Serializable {
    public int status;
    public String errorMsg = "";
    public String data;

    public AxiosResultBean() {
    }
}
