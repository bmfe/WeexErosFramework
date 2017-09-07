package com.benmu.framework.model;

/**
 * 返给Js地理位置Bean
 *
 */
public class GeolocationResultBean extends BaseResultBean {
    public Result data;

    public static class Result {
        public double locationLat;
        public double locationLng;
    }
}
