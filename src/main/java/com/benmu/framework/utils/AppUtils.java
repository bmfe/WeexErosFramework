package com.benmu.framework.utils;

import android.graphics.Typeface;
import android.text.TextUtils;

import com.taobao.weex.common.Constants;

import java.io.File;

/**
 * Created by Carry on 2017/7/11.
 */

public class AppUtils {
    public static int getFontWeight(Object fontWeight) {
        int typeFace = Typeface.NORMAL;
        if (fontWeight != null) {
            String s = fontWeight.toString();
            switch (s) {
                case "600":
                case "700":
                case "800":
                case "900":
                case Constants.Value.BOLD:
                    typeFace = Typeface.BOLD;
                    break;
            }
        }

        return typeFace;

    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return -1;
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }


    public static String getFileExtName(String filepath) {
        String ext = "";
        if (TextUtils.isEmpty(filepath)) return ext;
        File file = new File(filepath);
        String fileName = file.getName();
        if (fileName != null && fileName.length() > 0) {
            int dot = fileName.lastIndexOf(".");
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }


    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) return "";
        return new File(filePath).getName();
    }
}
