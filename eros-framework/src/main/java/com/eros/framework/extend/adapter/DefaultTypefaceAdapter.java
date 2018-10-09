package com.eros.framework.extend.adapter;

import android.content.Context;

import com.eros.framework.BMWXEnvironment;
import com.eros.framework.constant.Constant;
import com.eros.framework.manager.impl.FileManager;
import com.eros.framework.utils.SharePreferenceUtil;

import java.io.File;

/**
 * Created by Carry on 2017/10/26.
 */

public class DefaultTypefaceAdapter{
    private Context mContext;

    public DefaultTypefaceAdapter(Context context) {
        this.mContext = context;
    }

    public File getTypefaceDir() {
        return FileManager.getIconDir(mContext);
    }

    public boolean isInterceptor() {
        return Constant.INTERCEPTOR_ACTIVE.equals(SharePreferenceUtil.getInterceptorActive
                (mContext));
    }

    public String getJsServer() {
        return BMWXEnvironment.mPlatformConfig.getUrl().getJsServer();
    }
}
