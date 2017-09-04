package com.benmu.framework.model;

import com.taobao.weex.bridge.SimpleJSCallback;

import java.io.Serializable;


/**
 * 导航解析Bean
 */
public class RouterModel implements Serializable {
    public String url;
    public String type;
    public Object params;
    public int length;
    public boolean canBack;
    public String title;
    public boolean navShow;
    public String statusBarStyle;
    public boolean isRunBackCallback;


    public RouterModel() {
    }


    public RouterModel(String url, String type, Object params, String title, boolean navShow,
                       String statusBarStyle) {
        this.url = url;
        this.type = type;
        this.params = params;
        this.title = title;
        this.navShow = navShow;
        this.statusBarStyle = statusBarStyle;
    }


    public RouterModel(String url, String type, Object params, int length, boolean canBack,
                       String title, boolean navShow, String statusBarStyle, boolean
                               isRunBackCallback) {
        this.url = url;
        this.type = type;
        this.params = params;
        this.length = length;
        this.canBack = canBack;
        this.title = title;
        this.navShow = navShow;
        this.statusBarStyle = statusBarStyle;
        this.isRunBackCallback = isRunBackCallback;
    }
}
