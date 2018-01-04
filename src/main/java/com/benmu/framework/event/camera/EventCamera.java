package com.benmu.framework.event.camera;

import com.google.zxing.integration.android.IntentIntegrator;

import android.app.Activity;
import android.content.Context;

import com.benmu.framework.R;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.CameraManager;
import com.benmu.framework.manager.impl.ImageManager;
import com.benmu.framework.manager.impl.ModalManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.PersistentManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.CameraResultBean;
import com.benmu.framework.model.UploadImageBean;
import com.benmu.framework.model.UploadResultBean;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/8/16.
 */

public class EventCamera {
    private JSCallback mUploadAvatar, mScanCallback, mScreenShotCallback;
    private Context mUploadContext;

    public void scan(JSCallback jscallback, Context context) {
        mScanCallback = jscallback;
        CameraManager cameraManager = ManagerFactory.getManagerService(CameraManager.class);
        CameraManager.ScanConfig.ConfigBuilder builder = new CameraManager.ScanConfig
                .ConfigBuilder();
        builder.setBeepEnable(true).setCodeFormat(IntentIntegrator.ALL_CODE_TYPES).setContext(
                (Activity) context).setPrompt(context.getResources().getString(R.string
                .capture_qrcode_prompt));
        cameraManager.scanCode(builder.build());
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }

    @Subscribe
    public void OnScanResult(CameraResultBean bean) {
        if (this.mScanCallback == null || bean == null) return;
        mScanCallback.invoke(bean);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }


    public void uploadImage(String json, Context context, JSCallback jsCallback) {
        mUploadAvatar = jsCallback;
        mUploadContext = context;
        UploadImageBean bean = ManagerFactory.getManagerService(ParseManager.class).parseObject
                (json, UploadImageBean.class);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
        ImageManager imageManager = ManagerFactory.getManagerService(ImageManager.class);
        if (bean.allowCrop && bean.maxCount == 1) {
            //上传头像
            imageManager.pickAvatar(context, bean);
        } else if (bean.maxCount > 0) {
            imageManager.pickPhoto(context, bean);
        }

    }

    public void openCamera(String json, Context context, JSCallback jsCallback) {
        mUploadAvatar = jsCallback;
        mUploadContext = context;
        UploadImageBean bean = ManagerFactory.getManagerService(ParseManager.class).parseObject
                (json, UploadImageBean.class);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
        ImageManager imageManager = ManagerFactory.getManagerService(ImageManager.class);
        imageManager.openCamera(context, bean);
    }


    @Subscribe
    public void OnUploadResult(UploadResultBean uploadResultBean) {
        if (uploadResultBean != null && mUploadAvatar != null) {
            mUploadAvatar.invoke(uploadResultBean);
        }
        if (uploadResultBean != null && mScreenShotCallback != null) {
            mScreenShotCallback.invoke(uploadResultBean);
        }

        ModalManager.BmLoading.dismissLoading(mUploadContext);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        mScreenShotCallback = null;
        mUploadAvatar = null;
        ManagerFactory.getManagerService(PersistentManager.class).deleteCacheData(Constant
                .ImageConstants.UPLOAD_IMAGE_BEAN);
    }
}
