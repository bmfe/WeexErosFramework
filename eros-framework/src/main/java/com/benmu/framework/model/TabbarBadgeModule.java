package com.benmu.framework.model;

import java.io.Serializable;

/**
 * Created by liuyuanxiao on 2018/6/21.
 */

public class TabbarBadgeModule implements Serializable {
    public int index;
    public int value;
    public String textColor;
    public String bgColor;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
