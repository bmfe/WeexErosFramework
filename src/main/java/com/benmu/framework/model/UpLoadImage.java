package com.benmu.framework.model;

/**
 * Created by Carry on 2017/8/21.
 */

public class UpLoadImage extends BaseResultBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private String resourceId;
        private String filename;
        private Object checksum;

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public Object getChecksum() {
            return checksum;
        }

        public void setChecksum(Object checksum) {
            this.checksum = checksum;
        }

    }
}
