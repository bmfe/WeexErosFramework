package com.benmu.framework.utils;

import android.content.Context;
import android.net.Uri;

import java.io.File;

/**
 * Created by Carry on 2017/10/21.
 */
public class ResourceUtil {

    public static Uri getSafeUri(Context context, String path, String fileName) {
//        Uri parse=null;
//        if (Build.VERSION.SDK_INT >= 24) {
////            try {
//            File file = new File(path);
//            if (!file.exists()) file.mkdirs();
//            return FileProvider.getUriForFile(context, "com.benmu.wx.fileprovider", file);
//                parse = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver
//                        (), path,path.substring(path.lastIndexOf("/")+1), null));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            return parse;
//        } else {
            return Uri.fromFile(new File(path));
//        }
    }
}
