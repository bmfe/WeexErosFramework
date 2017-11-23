package com.benmu.framework.update;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.http.okhttp.callback.FileCallBack;
import com.benmu.framework.http.okhttp.callback.StringCallback;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.FileManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.VersionManager;
import com.benmu.framework.model.JsVersionInfoBean;
import com.benmu.framework.model.Md5MapperModel;
import com.benmu.framework.model.VersionBean;
import com.benmu.framework.utils.L;
import com.benmu.framework.utils.Md5Util;
import com.benmu.framework.utils.SharePreferenceUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import me.ele.patch.BsPatch;
import okhttp3.Call;

/**
 * Created by Carry on 2017/11/22.
 * 更新逻辑应reCode模块化 先单独分包
 */

public class SynVersionService extends Service {

    private static final String TAG = "SynchronizedService";
    private JsVersionInfoBean newVersion;
    private String mUpdateUrl;

    @Override
    public IBinder onBind(Intent intent) {
        return new SynBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "启动更新服务");
        readyUpdate();
    }

    public class SynBinder extends Binder {

        public void checkVersion() {
            Log.e(TAG, "启动更新服务");
            readyUpdate();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }




    /**
     * 是否有Jar包更新
     */
    private void readyUpdate() {
        mUpdateUrl = BMWXEnvironment.mPlatformConfig.getUrl().getBundleUpdate();
        if (TextUtils.isEmpty(mUpdateUrl)) return;

        if (Constant.INTERCEPTOR_ACTIVE.equals(SharePreferenceUtil.getInterceptorActive(this))) {
            VersionManager versionManager = ManagerFactory.getManagerService(VersionManager.class);
            versionManager.checkBundleUpdate(this, mUpdateUrl,
                    true, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(TAG, "获取更新失败");
                            stopSelf();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            VersionBean version = ManagerFactory.getManagerService(ParseManager
                                    .class).parseObject(response,
                                    VersionBean
                                            .class);
                            if (version == null) {
                                Log.e(TAG, "返回结果异常");
                                stopSelf();
                                return;
                            }
                            switch (version.resCode) {
                                case 0://需要更新
                                    if (!TextUtils.isEmpty(version.data.path)) {
                                        if (version.data.diff) {
                                            //更新插分包
                                            Log.e(TAG, "检查插分包");
                                            download(version, false);
                                        } else {
                                            //下载全量包
                                            Log.e(TAG, "检查全量包");
                                            downloadCompleteZip();
                                        }
                                    } else {
                                        //下载全量包
                                        Log.e(TAG, "检查全量包");
                                        downloadCompleteZip();
                                    }
                                    break;
                                case 401:
                                    Log.e(TAG, "JS文件查询失败!");
                                    stopSelf();
                                    break;
                                case 4000:
                                    Log.e(TAG, "当前版本已是最新!");
                                    stopSelf();
                                    break;
                                default:
                                    Log.e(TAG, "resCode:" + version.resCode);
                                    stopSelf();
                                    break;

                            }
                        }
                    });

        }
    }

    /**
     * 下载全量包
     */
    private void downloadCompleteZip() {
        VersionManager versionManager = ManagerFactory.getManagerService(VersionManager.class);
        versionManager.checkBundleUpdate(this, mUpdateUrl,
                false, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        L.e(TAG, "检查全量包失败!，更新失败");
                        stopSelf();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        VersionBean version = ManagerFactory.getManagerService(ParseManager
                                .class).parseObject(response, VersionBean
                                .class);
                        if (version != null && !TextUtils.isEmpty(version.data.path)) {
                            L.e(TAG, "检查全量包成功!，开始下载");
                            download(version, true);
                        }
                    }
                });
    }

    /**
     * 下载包
     */
    public void download(final VersionBean version, final boolean complete) {
        try {
            if (version == null) {
                stopSelf();
                return;
            }
            VersionManager versionManager = ManagerFactory.getManagerService(VersionManager.class);
            versionManager.downloadBundle(version.data.path, new FileCallBack(FileManager
                    .getTempBundleDir(this).getAbsolutePath(), version.data.diff ? FileManager
                    .PATCH_NAME : FileManager.TEMP_BUNDLE_NAME) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG, "下载插分包出错");
                    if (!complete) {
                        downloadCompleteZip();
                    } else {
                        stopSelf();

                    }
                }

                @Override
                public void onResponse(File response, int id) {
                    Log.e("version", "下载成功" + response.getAbsolutePath());
                    if (version.data.diff) {
                        bsPatch();
                    } else {
                        File download = new File(FileManager.getTempBundleDir(getBaseContext())
                                , FileManager.TEMP_BUNDLE_NAME);
                        if (checkZipValidate(download)) {
                            RenameDeleteFile();
                            //更改本地jsversion
                            SharePreferenceUtil.setDownLoadVersion(getApplication(),
                                    ManagerFactory.getManagerService(ParseManager.class)
                                            .toJsonString(newVersion));
                            newVersion = null;
                            stopSelf();
                        } else {
                            L.e(TAG, "更新包md5校验失败，更新失败");
                            FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                    (getBaseContext()), FileManager.TEMP_BUNDLE_NAME));
                            newVersion = null;
                            stopSelf();
                        }
                    }

                }


            });
        } catch (Exception e) {
            stopSelf();
            e.printStackTrace();
        }


    }

    /**
     * MD5 效验
     */
    private boolean checkZipValidate(File file) {
        if (file.exists()) {
            byte[] json = FileManager.extractZip(file, "md5.json");
            try {
                Md5MapperModel mapper = ManagerFactory.getManagerService(ParseManager.class)
                        .parseObject(new String(json, "UTF-8"),
                                Md5MapperModel.class);
                //校验文件正确性
                List<Md5MapperModel.Item> lists = mapper.getFilesMd5();
                // 按照md5值从小到大排列
                Collections.sort(lists);
                //所有md5想加得到总的md5
                String total = "";
                for (Md5MapperModel.Item item : lists) {
                    total = total + item.getMd5();
                }
                String finalMd5 = Md5Util.getMd5code(total);
                //比较md5是否正确
                newVersion = new JsVersionInfoBean(mapper.getJsVersion(), mapper.getAndroid(),
                        mapper.getTimestamp());
                return mapper.getJsVersion().equals(finalMd5);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 插分生成全量包
     */
    private void bsPatch() {

        File oldFile = new File(FileManager.getTempBundleDir(getBaseContext()), FileManager
                .BUNDLE_NAME);
        File newFile = new File(FileManager.getTempBundleDir(getBaseContext()), FileManager
                .TEMP_BUNDLE_NAME);
        File patchFile = new File(FileManager.getTempBundleDir(getBaseContext()), FileManager
                .PATCH_NAME);
        if (oldFile.exists() && patchFile.exists()) {
            BsPatch.workAsync(oldFile.getAbsolutePath(), newFile.getAbsolutePath(), patchFile
                    .getAbsolutePath(), new BsPatch.BsPatchListener() {
                @Override
                public void onSuccess(String oldPath, String newPath, String patchPath) {
                    L.e("version", "bspath 命令成功");
                    //验证下载包是否正确
                    File download = new File(FileManager.getTempBundleDir
                            (getBaseContext()), FileManager
                            .TEMP_BUNDLE_NAME);
                    boolean validate = checkZipValidate(download);
                    if (validate) {
                        L.e("version", "下载patch md5校验成功");
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (getBaseContext()), FileManager.BUNDLE_NAME));
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (getBaseContext()), FileManager.PATCH_NAME));
                        FileManager.renameFile(FileManager.getTempBundleDir
                                        (getBaseContext()),
                                FileManager.TEMP_BUNDLE_NAME, FileManager
                                        .BUNDLE_NAME);
                        //更改本地jsversion
                        SharePreferenceUtil.setDownLoadVersion(getApplication(), ManagerFactory
                                .getManagerService(ParseManager.class).toJsonString(newVersion));
                        newVersion = null;
                        stopSelf();
                    } else {
                        L.e("version", "下载patch md5校验出错");
                        //删除生成的新包和patch包
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (getBaseContext()), FileManager.PATCH_NAME));
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (getBaseContext()), FileManager.TEMP_BUNDLE_NAME));
                        newVersion = null;
                        stopSelf();
                    }


                }

                @Override
                public void onFail(String oldPath, String newPath, String patchPath, Exception e) {
                    L.e("version", "bspath 命令失败");
                    FileManager.deleteFile(new File(FileManager.getTempBundleDir
                            (getBaseContext()), FileManager.PATCH_NAME));
                    FileManager.deleteFile(new File(FileManager.getTempBundleDir
                            (getBaseContext()), FileManager.TEMP_BUNDLE_NAME));
                    stopSelf();
                }
            });
        }


    }

    /**
     * 删除旧的Js包,更新新Js包名称
     */
    private void RenameDeleteFile() {
        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                (getBaseContext()), FileManager.BUNDLE_NAME));
        FileManager.renameFile(FileManager.getTempBundleDir
                        (getBaseContext()),
                FileManager.TEMP_BUNDLE_NAME, FileManager
                        .BUNDLE_NAME);
    }


}
