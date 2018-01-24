package com.benmu.framework.model;

/**
 * Created by Carry on 2017/11/17.
 */

public class GeoResultBean extends BaseResultBean {
    private Geo data;

    public class Geo {
        private double locationLat;
        private double locationLng;

        public double getLocationLat() {
            return locationLat;
        }

        public void setLocationLat(double locationLat) {
            this.locationLat = locationLat;
        }

        public double getLocationLng() {
            return locationLng;
        }

        public void setLocationLng(double locationLng) {
            this.locationLng = locationLng;
        }
    }

    public Geo getData() {
        return data;
    }

    public void setData(Geo data) {
        this.data = data;
    }
}
