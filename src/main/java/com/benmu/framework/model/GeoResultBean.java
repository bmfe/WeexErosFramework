package com.benmu.framework.model;

/**
 * Created by Carry on 2017/11/17.
 */

public class GeoResultBean extends BaseResultBean {
    private Geo data;

    public class Geo {
        private double locationLat;
        private double locationLng;

        public double getLat() {
            return locationLat;
        }

        public void setLat(double lat) {
            this.locationLat = lat;
        }

        public double getLng() {
            return locationLng;
        }

        public void setLng(double lng) {
            this.locationLng = lng;
        }
    }

    public Geo getData() {
        return data;
    }

    public void setData(Geo data) {
        this.data = data;
    }
}
