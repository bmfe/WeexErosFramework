package com.benmu.framework.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Carry on 2017/4/20.
 */

public class WebViewParamBean implements Serializable {
    private String url;
    private String title;
    private ShareInfo shareInfo;
    private boolean authorize;
    private boolean hideNavbar;


    public boolean isAuthorize() {
        return authorize;
    }

    public void setAuthorize(boolean authorize) {
        this.authorize = authorize;
    }

    public boolean isHideNavbar() {
        return hideNavbar;
    }

    public void setHideNavbar(boolean hideNavbar) {
        this.hideNavbar = hideNavbar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ShareInfo getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public static class ShareInfo implements Serializable {
        private String title;
        private String content;
        private String image;
        private String url;
        private List<String> platforms;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getPlatforms() {
            return platforms;
        }

        public void setPlatforms(List<String> platforms) {
            this.platforms = platforms;
        }
    }
}
