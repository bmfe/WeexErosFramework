package com.benmu.framework.model;

/**
 * Created by Carry on 2017/11/15.
 */

public class CidBean extends BaseResultBean {
    private Result data;

    public static class Result {
        private String cid;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }
    }

    public Result getData() {
        return data;
    }

    public void setData(Result data) {
        this.data = data;
    }

}
