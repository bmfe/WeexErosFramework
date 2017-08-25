package com.benmu.framework.model;


/**
 *  Js回调Bean
 */
public class AppConfigBean extends BaseResultBean {
    public Result data;
    public static class Result{
        public String fontSize;
        public float scale;
    }
}
