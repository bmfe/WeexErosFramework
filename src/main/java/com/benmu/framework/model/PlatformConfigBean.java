package com.benmu.framework.model;

import java.io.Serializable;

/**
 * Created by Carry on 2017/8/23.
 */

public class PlatformConfigBean implements Serializable {
    private String AppName;
    private Page Page;
    private Url url;

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

    public class Page {
        private String homePage;
        private String mediatorPage;
        private String navBarColor;

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
        private String updateVersion;

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

        public String getUpdateVersion() {
            return updateVersion;
        }

        public void setUpdateVersion(String updateVersion) {
            this.updateVersion = updateVersion;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }


        public String getCheckJsVersionApi() {
            return updateVersion;
        }

        public void setCheckJsVersionApi(String checkJsVersionApi) {
            this.updateVersion = checkJsVersionApi;
        }
    }

}
