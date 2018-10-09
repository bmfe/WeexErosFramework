package com.eros.framework.model;

import java.io.Serializable;

public class UpdateOptionBean implements Serializable {
    private String url;
    private boolean diff;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDiff() {
        return diff;
    }

    public void setDiff(boolean diff) {
        this.diff = diff;
    }
}
