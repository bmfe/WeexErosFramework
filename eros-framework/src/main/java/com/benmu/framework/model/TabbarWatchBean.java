package com.benmu.framework.model;

/**
 * Created by liuyuanxiao on 2018/7/25.
 */

public class TabbarWatchBean {
    public int index;
    public int  hsCode;
    public boolean isClear;
    public TabbarWatchBean(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getHsCode() {
        return hsCode;
    }

    public void setHsCode(int hsCode) {
        this.hsCode = hsCode;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }
}
