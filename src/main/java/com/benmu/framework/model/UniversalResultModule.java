package com.benmu.framework.model;

import java.io.Serializable;

/**
 * Created by Carry on 2018/1/3.
 * 基础js回调
 */


public class UniversalResultModule implements Serializable {
    private String errorMsg;
    private String status;
    private Object data;


    public UniversalResultModule(String errorMsg, String status, Object data) {
        this.errorMsg = errorMsg;
        this.status = status;
        this.data = data;
    }


    public UniversalResultModule() {

    }

    public static UniversalResultModule obtainSuccess(Object object) {
        return new UniversalResultModule(null, "0", object);
    }

    public static UniversalResultModule obtainFailed(String errorMsg) {
        return new UniversalResultModule(errorMsg, "9", null);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
