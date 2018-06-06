package com.benmu.framework.extend.adapter;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.benmu.framework.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by liuyuanxiao on 18/4/12.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int
            height) {
        Log.e("GlideImageLoader", "GlideImageLoader>>>>>>" + path);
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path))).apply(new RequestOptions().error(R.drawable
                .ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy
                (DiskCacheStrategy.ALL))   //设置图片路径(fix #8,
                // 文件名包含%符号 无法识别和显示)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int
            width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path))).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))  //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
