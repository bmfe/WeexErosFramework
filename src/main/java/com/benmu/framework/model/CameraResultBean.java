package com.benmu.framework.model;


/**
 * 扫描二维码，结果返回Js
 */
public class CameraResultBean extends BaseResultBean {
    public Result data;

    public static class Result {
        public String text;
    }
}
