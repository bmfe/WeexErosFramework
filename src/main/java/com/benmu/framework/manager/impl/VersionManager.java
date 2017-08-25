package com.benmu.framework.manager.impl;

import android.content.Context;
import android.text.TextUtils;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.model.JsVersionInfoBean;
import com.benmu.framework.model.Md5MapperModel;
import com.benmu.framework.utils.AppUtils;
import com.benmu.framework.utils.AssetsUtil;
import com.benmu.framework.utils.BaseCommonUtil;
import com.benmu.framework.utils.SharePreferenceUtil;

import java.io.File;
import java.util.Date;

/**
 * Created by Carry on 2017/8/23.
 */

public class VersionManager extends Manager {

    public long prepareJsBundle(Context context) {
        long startTime = new Date().getTime();
        if (SharePreferenceUtil.isInterceptorActive(context)) {
            if (TextUtils.isEmpty(SharePreferenceUtil.getVersion(context))) {
                setVersion(context, AssetsUtil.getAssetsVersionInfo(context));
            }
            if (isCover(context)) {
                transferInsideBundle(context);
            } else {
                String downloadVersion = SharePreferenceUtil.getDownLoadVersion(context);
                if (TextUtils.isEmpty(downloadVersion)) {
                    checkBundleDir(context);
                } else {
                    File zip = FileManager.getFileInDir(FileManager.getTempBundleDir
                            (context), 0);
                    FileManager.unZip(zip, FileManager.getBundleDir(context));
                    SharePreferenceUtil.setVersion(context, SharePreferenceUtil
                            .getDownLoadVersion(context));
                    SharePreferenceUtil.setDownLoadVersion(context, null);
                }
            }
        }

        File file = new File(FileManager.getBundleDir(context), "pages/md5.json");
        if (file.exists()) {
            String json = FileManager.loadJs(file.getAbsolutePath());
            Md5MapperModel mapper = ManagerFactory.getManagerService(ParseManager.class)
                    .parseObject(json, Md5MapperModel.class);
            ManagerFactory.getManagerService(PersistentManager.class).setFileMapper(mapper
                    .getFilesMd5());
        }
        return new Date().getTime() - startTime;
    }

    private void checkBundleDir(Context context) {
        if (FileManager.isEmptyDir(FileManager.getBundleDir(context))) {
            transferInsideBundle(context);
        }
    }


    private void transferInsideBundle(Context context) {
        AssetsUtil.copyAssetsFile(context, Constant.BUNDLE_ZIP_NAME, new File(FileManager
                .getTempBundleDir(context), Constant.BUNDLE_ZIP_NAME));
        FileManager.unZip(new File(FileManager.getTempBundleDir(context), Constant
                .BUNDLE_ZIP_NAME), FileManager.getBundleDir(context));
        setVersion(context, AssetsUtil.getAssetsVersionInfo(context));
    }

    private void setVersion(Context context, Object assetsVersionInfo) {
        if (assetsVersionInfo != null) {
            ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
            SharePreferenceUtil.setVersion(context, parseManager.toJsonString(assetsVersionInfo));
        }
    }

    private boolean isCover(Context context) {
        AssetsUtil.AssetsJsVersion assetsVersionInfo = AssetsUtil.getAssetsVersionInfo(context);
        String downloadVersion = SharePreferenceUtil.getDownLoadVersion(context);
        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        JsVersionInfoBean compareVersion = null;
        if (TextUtils.isEmpty(downloadVersion)) {
            String currentVersion = SharePreferenceUtil.getVersion(context);
            compareVersion = parseManager.parseObject(currentVersion,
                    JsVersionInfoBean.class);

        } else {
            compareVersion = parseManager.parseObject(downloadVersion,
                    JsVersionInfoBean.class);
        }

        return isCoverByAssetsZip(context, new JsVersionInfoBean
                (assetsVersionInfo.getJsVersion(), assetsVersionInfo.getAndroid(),
                        assetsVersionInfo.getTimestamp()), compareVersion);
    }


    public boolean isCoverByAssetsZip(Context context, JsVersionInfoBean assets,
                                      JsVersionInfoBean current) {
        if (AppUtils.compareVersion(BaseCommonUtil.getVersionName(context), current.getAndroid())
                < 0) {
            return true;
        }
        if (assets.getJsVersion().equals(current.getJsVersion()) && assets.getTimestamp().equals
                (current.getTimestamp())) {
            //版本号和时间戳都相同
            return true;
        }
        //最低版本大于等于
        return !assets.getJsVersion().equals(current.getJsVersion()) && assets.getTimestamp()
                .compareTo(current.getTimestamp()) > 0;
    }
}
