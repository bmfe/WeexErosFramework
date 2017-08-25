package com.benmu.framework.model;

import com.taobao.weex.bridge.SimpleJSCallback;

import java.io.Serializable;


/**
 * 导航解析Bean
 */
public class RouterModel implements Serializable {
    public String url;
    public String animateType;
    public Object params;
    public int length;
    public TitleModel navigationInfo;
    public boolean authorize;
    public BackCallback backCallback;
    public boolean forbidBack;
    public boolean needBackCallback;

    public RouterModel(String url, String animateType, Object params, int length, TitleModel
            navigationInfo) {
        this.url = url;
        this.animateType = animateType;
        this.params = params;
        this.length = length;
        this.navigationInfo = navigationInfo;
    }

    public RouterModel() {
    }

    public class BackCallback extends SimpleJSCallback implements Serializable {

        public BackCallback(String instanceId, String callbackId) {
            super(instanceId, callbackId);
        }
    }

}
