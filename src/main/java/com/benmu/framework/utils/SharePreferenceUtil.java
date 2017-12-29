package com.benmu.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.benmu.framework.constant.Constant;

import java.io.Serializable;

/**
 * Created by Carry on 17/2/8.
 */

public class SharePreferenceUtil {
    public static void setToken(Context context, String token) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_TOKEN, token).apply();
        }
    }

    public static String getToken(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_TOKEN, null);
        }
        return null;
    }


    public static String getVersion(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_VERSION, null);
        }
        return null;
    }


    public static void setVersion(Context context, String version) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_VERSION, version).apply();
        }
    }


    public static void setDownLoadVersion(Context context, String version) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_DOWNLOAD_VERSION, version).apply();
        }
    }

    public static String getDownLoadVersion(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_DOWNLOAD_VERSION, null);
        }
        return null;
    }

    public static boolean setUserInfo(Context context, String user) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.edit().putString(Constant.SP.SP_USERINFO, user).commit();
        }
        return false;
    }

    public static String getUserInfo(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_USERINFO, null);
        }
        return null;
    }


    public static boolean deleteUserInfo(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.edit().remove(Constant.SP.SP_USERINFO).commit();
        }
        return false;
    }

    public static boolean deleteToken(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.edit().remove(Constant.SP.SP_TOKEN).commit();
        }
        return false;
    }


    public static String getJsStorage(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_JS_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, null);
        }
        return null;
    }

    public static boolean setJsStorage(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_JS_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.edit().putString(key, value).commit();
        }

        return false;
    }


    public static boolean deleteJsStorage(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_JS_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.edit().remove(key).commit();
        }
        return false;
    }


    public static boolean clearSp(Context context) {
        if (context != null) {
            SharedPreferences js = context.getSharedPreferences(Constant
                    .SP.SP_JS_NAME, Context.MODE_PRIVATE);
            SharedPreferences nav = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);

            return js.edit().clear().commit() && nav.edit().putString(Constant.SP.SP_USERINFO,
                    null).commit();
        }
        return false;
    }

    public static void setNotFirstLauncher(Context context, boolean flag) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(Constant.SP.SP_FIRST_LAUNCHER, flag).apply();
        }
    }

    public static boolean getNotFirstLauncher(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(Constant.SP.SP_FIRST_LAUNCHER, false);
        }
        return false;
    }


    public static void setClientId(Context context, String cId) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_CID, cId).apply();
        }
    }

    public static String getClientId(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_CID, null);
        }
        return null;
    }


    public static String getInterceptorActive(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_INTERCEPTOR_ACTIVE, "");
        }
        return "";
    }

    public static void setInterceptorActive(Context context, String active) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_INTERCEPTOR_ACTIVE, active).apply();
        }
    }

    public static void setAppFontSizeOption(Context context, String option) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_FONTSIZE, option).apply();
        }
    }

    public static String getAppFontSizeOption(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_FONTSIZE, null);
        }
        return null;
    }


    public static void setAppChannel(Context context, String channel) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_APPCHANNEL, channel).apply();
        }
    }


    public static String getAppChannel(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_APPCHANNEL, null);
        }
        return null;
    }


    public static boolean putStringExtra(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
        return preferences.edit().putString(key, value).commit();
    }

    public static String getStringExtra(Context context, String key,
                                        String defValue) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, defValue);
    }


    public static boolean putIntExtra(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
        return preferences.edit().putInt(key, value).commit();
    }

    public static int getIntExtra(Context context, String key, int defValue) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, defValue);
    }

    public static boolean putLongExtra(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
        return preferences.edit().putLong(key, value).commit();
    }

    public static long getLongExtra(Context context, String key, long defValue) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
        return preferences.getLong(key, defValue);
    }


    public static void setApiUrl(Context context, String url) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP
                    .SP_API_URL, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(Constant.SP.SP_TOKEN, url).apply();
        }
    }

    public static String getApiUrl(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SP
                    .SP_API_URL, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Constant.SP.SP_TOKEN, null);
        }
        return null;

    }

    public static boolean deleteData(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constant
                    .SP.SP_NATIVE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.edit().remove(key).commit();
        }
        return false;
    }

    public static boolean setData(Context context, String key, Serializable value) {
        if (context != null) {
            if (value instanceof Integer) {
                return putIntExtra(context, key, (Integer) value);
            } else if (value instanceof String) {
                return putStringExtra(context, key, (String) value);
            } else if (value instanceof Long) {
                return putLongExtra(context, key, (Long) value);
            } else {
                throw new RuntimeException("support value type !");
            }
        }
        return false;
    }

    public static <T> T getData(Context context, String key, Class<T> clazz) {
        if (context != null) {
            if (Integer.class == clazz) {
                return (T) (Integer.valueOf(getIntExtra(context, key, 0)));
            } else if (Long.class == clazz) {
                return (T) (Long.valueOf(getLongExtra(context, key, 0)));
            } else if (String.class == clazz) {
                return (T) getStringExtra(context, key, null);
            } else {
                return null;
            }
        }
        return null;
    }
}
