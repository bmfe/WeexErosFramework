package com.benmu.framework.update;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.R;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXConstant;
import com.benmu.framework.http.okhttp.callback.FileCallBack;
import com.benmu.framework.http.okhttp.callback.StringCallback;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.FileManager;
import com.benmu.framework.manager.impl.ModalManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.VersionManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.JsVersionInfoBean;
import com.benmu.framework.model.Md5MapperModel;
import com.benmu.framework.model.VersionBean;
import com.benmu.framework.utils.L;
import com.benmu.framework.utils.Md5Util;
import com.benmu.framework.utils.SharePreferenceUtil;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import me.ele.patch.BsPatch;
import okhttp3.Call;

/**
 * Created by Carry on 2017/11/23.
 * 版本检查
 */

public class VersionChecker {
    private static final String TAG = "VersionChecker";
    private JsVersionInfoBean newVersion;
    private String mUpdateUrl;
    private Context mContext;
    private int mCurrentStatus = Constant.Version.SLEEP;
    private boolean mCustomerUpdate;
    private boolean mDownloadCompleted;

    public VersionChecker(Context context) {
        this.mContext = context;
        mCustomerUpdate = BMWXEnvironment.mPlatformConfig.isCustomBundleUpdate();
    }

    public void checkVersion() {
        if (mCurrentStatus == Constant.Version.UPDATING || mCustomerUpdate) return;
        readyUpdate(null, true);
    }


    public void notifyUpdate() {
        mDownloadCompleted = true;
        if (mCustomerUpdate) {
            postSuccess("更新包准备完成");
        } else {
            showUpdateDialig();
        }
    }


    public void restartApp() {
        if (!mDownloadCompleted) return;
        Activity activity = RouterTracker.peekActivity();
        if (activity == null) return;
        //销毁中介者
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(new
                Intent(WXConstant.MEDIATOR_DESTROY));
        //重启应用
        Intent intent = activity.getPackageManager()
                .getLaunchIntentForPackage(activity.getApplication().getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                .FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        Process.killProcess(Process.myPid());
        activity.finish();
    }

    private void showUpdateDialig() {
        Activity activity = RouterTracker.peekActivity();
        //此时说明栈中没有可用activity 监听入栈事件
        if (activity != null) {
            ModalManager.BmAlert.showAlert(activity, mContext.getResources()
                    .getString(R.string.str_update_title), mContext.getResources()
                    .getString(R.string.str_update_tips), mContext.getResources().getString(R.string
                    .str_ensure), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restartApp();
                }
            }, null, null, null, null, false);
        } else {
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
        }
    }

    @Subscribe
    public void onActivityAttach(Intent intent) {
        if (WXConstant.ACTION_ACTIVITY_ATTACH.equals(intent.getAction())) {
            //有activity入栈 展示弹窗
            showUpdateDialig();
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        }
    }

    public void checkVersion(String url, boolean diff) {
        if (mCurrentStatus == Constant.Version.UPDATING) return;
        readyUpdate(url, diff);
    }

    private void postSuccess(String msg) {
        post(0, msg);
    }

    private void post(int code, String msg) {
        Intent intent = new Intent(WXConstant.ACTION_BUNDLE_DOWNLOADED);
        intent.putExtra("code", code);
        intent.putExtra("msg", msg);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(intent);
    }

    private void postFailed(String msg) {
        post(9, msg);
    }

    private void readyUpdate(String path, boolean diff) {
        mUpdateUrl = BMWXEnvironment.mPlatformConfig.getUrl().getBundleUpdate();
        if (!TextUtils.isEmpty(path)) {
            mUpdateUrl = path;
        }

        if (TextUtils.isEmpty(mUpdateUrl)) return;

        if (Constant.INTERCEPTOR_ACTIVE.equals(SharePreferenceUtil.getInterceptorActive(mContext)
        )) {
            mCurrentStatus = Constant.Version.UPDATING;
            VersionManager versionManager = ManagerFactory.getManagerService(VersionManager.class);
            versionManager.checkBundleUpdate(mContext, mUpdateUrl,
                    diff, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(TAG, "获取更新失败");
                            postFailed("获取更新失败");
                            mCurrentStatus = Constant.Version.SLEEP;
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            VersionBean version = ManagerFactory.getManagerService(ParseManager
                                    .class).parseObject(response,
                                    VersionBean
                                            .class);
                            if (version == null) {
                                Log.e(TAG, "返回结果异常");
                                postFailed("返回结果异常");
                                mCurrentStatus = Constant.Version.SLEEP;
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
                                    postFailed("JS文件查询失败");
                                    mCurrentStatus = Constant.Version.SLEEP;
                                    break;
                                case 4000:
                                    Log.e(TAG, "当前版本已是最新!");
                                    postFailed("当前版本已是最新");
                                    mCurrentStatus = Constant.Version.SLEEP;
                                    break;
                                default:
                                    Log.e(TAG, "resCode:" + version.resCode);
                                    postFailed("resCode:" + version.resCode);
                                    mCurrentStatus = Constant.Version.SLEEP;
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
        versionManager.checkBundleUpdate(mContext, mUpdateUrl,
                false, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        L.e(TAG, "检查全量包失败!，更新失败");
                        mCurrentStatus = Constant.Version.SLEEP;
                        postFailed("检查全量包失败!，更新失败");
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
                mCurrentStatus = Constant.Version.SLEEP;
                return;
            }
            VersionManager versionManager = ManagerFactory.getManagerService(VersionManager.class);
            versionManager.downloadBundle(version.data.path, new FileCallBack(FileManager
                    .getTempBundleDir(mContext).getAbsolutePath(), version.data.diff ? FileManager
                    .PATCH_NAME : FileManager.TEMP_BUNDLE_NAME) {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e(TAG, "下载插分包出错");
                    if (!complete) {
                        downloadCompleteZip();
                    } else {
                        postFailed("下载全量包失败");
                        mCurrentStatus = Constant.Version.SLEEP;
                    }
                }

                @Override
                public void onResponse(File response, int id) {
                    Log.e("version", "下载成功" + response.getAbsolutePath());
                    if (version.data.diff) {
                        bsPatch();
                    } else {
                        File download = new File(FileManager.getTempBundleDir(mContext)
                                , FileManager.TEMP_BUNDLE_NAME);
                        if (checkZipValidate(download)) {
                            RenameDeleteFile();
                            //更改本地jsversion
                            SharePreferenceUtil.setDownLoadVersion(mContext,
                                    ManagerFactory.getManagerService(ParseManager.class)
                                            .toJsonString(newVersion));
                            newVersion = null;
                            mCurrentStatus = Constant.Version.SLEEP;
                            notifyUpdate();
                        } else {
                            L.e(TAG, "更新包md5校验失败，更新失败");
                            FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                    (mContext), FileManager.TEMP_BUNDLE_NAME));
                            newVersion = null;
                            mCurrentStatus = Constant.Version.SLEEP;
                            postFailed("更新包md5校验失败，更新失败");
                        }
                    }

                }


            });
        } catch (Exception e) {
            mCurrentStatus = Constant.Version.SLEEP;
            e.printStackTrace();
        }


    }


    /**
     * MD5 效验
     */
    private boolean checkZipValidate(File file) {
        if (file.exists()) {
            byte[] json = FileManager.extractZip(file, "md5.json");
            if (json == null) return false;
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

        File oldFile = new File(FileManager.getTempBundleDir(mContext), FileManager
                .BUNDLE_NAME);
        File newFile = new File(FileManager.getTempBundleDir(mContext), FileManager
                .TEMP_BUNDLE_NAME);
        File patchFile = new File(FileManager.getTempBundleDir(mContext), FileManager
                .PATCH_NAME);
        if (oldFile.exists() && patchFile.exists()) {
            BsPatch.workAsync(oldFile.getAbsolutePath(), newFile.getAbsolutePath(), patchFile
                    .getAbsolutePath(), new BsPatch.BsPatchListener() {
                @Override
                public void onSuccess(String oldPath, String newPath, String patchPath) {
                    L.e("version", "bspath 命令成功");
                    //验证下载包是否正确
                    File download = new File(FileManager.getTempBundleDir
                            (mContext), FileManager
                            .TEMP_BUNDLE_NAME);
                    boolean validate = checkZipValidate(download);
                    if (validate) {
                        L.e("version", "下载patch md5校验成功");
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (mContext), FileManager.BUNDLE_NAME));
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (mContext), FileManager.PATCH_NAME));
                        FileManager.renameFile(FileManager.getTempBundleDir
                                        (mContext),
                                FileManager.TEMP_BUNDLE_NAME, FileManager
                                        .BUNDLE_NAME);
                        //更改本地jsversion
                        SharePreferenceUtil.setDownLoadVersion(mContext, ManagerFactory
                                .getManagerService(ParseManager.class).toJsonString(newVersion));
                        newVersion = null;
                        mCurrentStatus = Constant.Version.SLEEP;
                        notifyUpdate();
                    } else {
                        L.e("version", "下载patch md5校验出错");
                        //删除生成的新包和patch包
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (mContext), FileManager.PATCH_NAME));
                        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                                (mContext), FileManager.TEMP_BUNDLE_NAME));
                        newVersion = null;
                        mCurrentStatus = Constant.Version.SLEEP;
                        postFailed("更新包md5校验失败，更新失败");
                    }


                }

                @Override
                public void onFail(String oldPath, String newPath, String patchPath, Exception e) {
                    L.e("version", "bspath 命令失败");
                    FileManager.deleteFile(new File(FileManager.getTempBundleDir
                            (mContext), FileManager.PATCH_NAME));
                    FileManager.deleteFile(new File(FileManager.getTempBundleDir
                            (mContext), FileManager.TEMP_BUNDLE_NAME));
                    mCurrentStatus = Constant.Version.SLEEP;
                    postFailed("path命令失败");
                }
            });
        }


    }


    /**
     * 删除旧的Js包,更新新Js包名称
     */
    private void RenameDeleteFile() {
        FileManager.deleteFile(new File(FileManager.getTempBundleDir
                (mContext), FileManager.BUNDLE_NAME));
        FileManager.renameFile(FileManager.getTempBundleDir
                        (mContext),
                FileManager.TEMP_BUNDLE_NAME, FileManager
                        .BUNDLE_NAME);
    }
}
