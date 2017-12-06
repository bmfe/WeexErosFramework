package com.benmu.framework.model;

import java.io.Serializable;

/**
 * Created by Carry on 2017/8/23.
 */

public class PlatformConfigBean implements Serializable {
    private String AppName;
    private String appBoard;
    private boolean androidIsListenHomeBack;
    private Page Page;
    private Url url;
    private Wechat wechat;
    private Umeng umeng;
    private Amap amap;

    public boolean isAndroidIsListenHomeBack() {
        return androidIsListenHomeBack;
    }

    public void setAndroidIsListenHomeBack(boolean androidIsListenHomeBack) {
        this.androidIsListenHomeBack = androidIsListenHomeBack;
    }

    public Wechat getWechat() {
        return wechat;
    }

    public void setWechat(Wechat wechat) {
        this.wechat = wechat;
    }

    public Umeng getUmeng() {
        return umeng;
    }

    public void setUmeng(Umeng umeng) {
        this.umeng = umeng;
    }

    public Amap getAmap() {
        return amap;
    }

    public void setAmap(Amap amap) {
        this.amap = amap;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public PlatformConfigBean.Page getPage() {
        return Page;
    }

    public void setPage(PlatformConfigBean.Page page) {
        Page = page;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getAppBoard() {
        return appBoard;
    }

    public void setAppBoard(String appBoard) {
        this.appBoard = appBoard;
    }

    public class Page {
        private String homePage;
        private String mediatorPage;
        private String navBarColor;
        private String navItemColor;

        public String getNavItemColor() {
            return navItemColor;
        }

        public void setNavItemColor(String navItemColor) {
            this.navItemColor = navItemColor;
        }

        public String getHomePage() {
            return homePage;
        }

        public void setHomePage(String homePage) {
            this.homePage = homePage;
        }

        public String getMediatorPage() {
            return mediatorPage;
        }

        public void setMediatorPage(String mediatorPage) {
            this.mediatorPage = mediatorPage;
        }

        public String getNavBarColor() {
            return navBarColor;
        }

        public void setNavBarColor(String navBarColor) {
            this.navBarColor = navBarColor;
        }
    }

    public class Url {
        private String request;
        private String jsServer;
        private String image;
        private String bundleUpdate;

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getJsServer() {
            return jsServer;
        }

        public void setJsServer(String jsServer) {
            this.jsServer = jsServer;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBundleUpdate() {
            return bundleUpdate;
        }

        public void setBundleUpdate(String bundleUpdate) {
            this.bundleUpdate = bundleUpdate;
        }
    }


    public class Wechat {
        private boolean enabled;
        private String appId;
        private String appSecret;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }
    }

    public class Umeng {
        private boolean enabled;
        private String androidAppKey;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getAndroidAppKey() {
            return androidAppKey;
        }

        public void setAndroidAppKey(String androidAppKey) {
            this.androidAppKey = androidAppKey;
        }
    }

    public class Amap {
        private boolean enabled;
        private String androidAppKey;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getAndroidAppKey() {
            return androidAppKey;
        }

        public void setAndroidAppKey(String androidAppKey) {
            this.androidAppKey = androidAppKey;
        }
    }

}
