package com.benmu.framework.utils;

import android.support.v4.BuildConfig;
import android.util.Log;

/**
 * Created by Carry on 17/3/29.
 */

public class L {
    public static void i(String s) {
        if (BuildConfig.DEBUG ) {
            Log.i("tag", s);
        }
    }

    public static void e(String tag,String s){
        if (BuildConfig.DEBUG ) {
            Log.e(tag,s);
        }
    }

    public static void d(String tag,String s) {

        if (BuildConfig.DEBUG ) {
            Log.d(tag,s);
        }
    }


}
