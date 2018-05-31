package com.benmu.framework.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by Carry on 2018/5/30.
 */

public class NetworkUtil {
    public static final String WIFI = "wifi";
    public static final String WIMAX = "wimax";
    // mobile
    public static final String MOBILE = "mobile";

    // Android L calls this Cellular, because I have no idea!
    public static final String CELLULAR = "cellular";
    // 2G network types
    public static final String TWO_G = "2g";
    public static final String GSM = "gsm";
    public static final String GPRS = "gprs";
    public static final String EDGE = "edge";
    // 3G network types
    public static final String THREE_G = "3g";
    public static final String CDMA = "cdma";
    public static final String UMTS = "umts";
    public static final String HSPA = "hspa";
    public static final String HSUPA = "hsupa";
    public static final String HSDPA = "hsdpa";
    public static final String ONEXRTT = "1xrtt";
    public static final String EHRPD = "ehrpd";
    // 4G network types
    public static final String FOUR_G = "4g";
    public static final String LTE = "lte";
    public static final String UMB = "umb";
    public static final String HSPA_PLUS = "hspa+";

    public static final String TYPE_UNKNOWN = "UNKNOWN";
    public static final String TYPE_ETHERNET = "ethernet";
    public static final String TYPE_ETHERNET_SHORT = "eth";
    public static final String TYPE_WIFI = "WIFI";
    public static final String TYPE_2G = "2g";
    public static final String TYPE_3G = "3g";
    public static final String TYPE_4G = "4g";
    public static final String TYPE_NONE = "NOT_REACHABLE";
    public static final String TYPE_3G_4G="3G/4G";

    public static String status(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        if (context == null) return TYPE_UNKNOWN;

        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        String connectionType = "";
        try {
            connectionType = getConnectionInfo(networkInfo).get("type").toString();
        } catch (Exception e) {
            e.printStackTrace();
            connectionType = TYPE_UNKNOWN;
        }
        return connectionType;
    }

    private static JSONObject getConnectionInfo(NetworkInfo info) {
        String type = TYPE_NONE;
        if (info != null) {
            // If we are not connected to any network set type to none
            if (!info.isConnected()) {
                type = TYPE_NONE;
            } else {
                type = getType(info);
            }
        }

        JSONObject connectionInfo = new JSONObject();

        try {
            connectionInfo.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return connectionInfo;
    }

    private static String getType(NetworkInfo info) {
        if (info != null) {
            String type = info.getTypeName().toLowerCase(Locale.US);

            if (type.equals(WIFI)) {
                return TYPE_WIFI;
            } else if (type.toLowerCase().equals(TYPE_ETHERNET) || type.toLowerCase().startsWith
                    (TYPE_ETHERNET_SHORT)) {
                return TYPE_ETHERNET;
            } else if (type.equals(MOBILE) || type.equals(CELLULAR)) {
                type = info.getSubtypeName().toLowerCase(Locale.US);
                if (type.equals(GSM) ||
                        type.equals(GPRS) ||
                        type.equals(EDGE) ||
                        type.equals(TWO_G)) {
                    return TYPE_3G_4G;
                } else if (type.startsWith(CDMA) ||
                        type.equals(UMTS) ||
                        type.equals(ONEXRTT) ||
                        type.equals(EHRPD) ||
                        type.equals(HSUPA) ||
                        type.equals(HSDPA) ||
                        type.equals(HSPA) ||
                        type.equals(THREE_G)) {
                    return TYPE_3G_4G;
                } else if (type.equals(LTE) ||
                        type.equals(UMB) ||
                        type.equals(HSPA_PLUS) ||
                        type.equals(FOUR_G)) {
                    return TYPE_3G_4G;
                }
            }
        } else {
            return TYPE_NONE;
        }
        return TYPE_UNKNOWN;
    }
}

