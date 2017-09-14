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
    private boolean navShow;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNavShow() {
        return navShow;
    }

    public void setNavShow(boolean navShow) {
        this.navShow = navShow;
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
