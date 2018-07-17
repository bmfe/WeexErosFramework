package com.benmu.framework.event.camera;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.benmu.framework.constant.Constant;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ImageManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.PersistentManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.ScanImageBean;
import com.benmu.framework.model.UploadImageBean;
import com.benmu.framework.model.UploadResultBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.utils.JsPoster;
import com.benmu.framework.utils.PermissionUtils;
import com.benmu.wxbase.EventGate;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.common.HybridBinarizer;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by liuyuanxiao on 18/1/4.
 */

public class EventImage extends EventGate {
    private JSCallback mPickCallback;

    @Override
    public void perform(Context context, WeexEventBean weexEventBean, String type) {

        String params = weexEventBean.getJsParams();

        if (WXEventCenter.EVENT_IMAGE_PICK.equals(type)) {
            pick(weexEventBean.getJsParams(), context, weexEventBean.getJscallback());
        } else if (WXEventCenter.EVENT_IMAGE_SCAN.equals(type)) {
            scan(params, context, weexEventBean.getJscallback());
        }
    }

    public void pick(String json, Context context, JSCallback jsCallback) {
        //Manifest.permission.READ_EXTERNAL_STORAGE 权限申请
        if (!PermissionUtils.checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return;
        }
        mPickCallback = jsCallback;
        UploadImageBean bean = ManagerFactory.getManagerService(ParseManager.class).parseObject
                (json, UploadImageBean.class);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
        ImageManager imageManager = ManagerFactory.getManagerService(ImageManager.class);
        if (bean.allowCrop && bean.maxCount == 1) {
            //上传头像
            imageManager.pickAvatar(context, bean, Constant.ImageConstants.IMAGE_NOT_UPLOADER_PICKER);
        } else if (bean.maxCount > 0) {
            imageManager.pickPhoto(context, bean, Constant.ImageConstants.IMAGE_NOT_UPLOADER_PICKER);
        }
    }

    public void scan(String json, Context context, JSCallback jsCallback) {

        ScanImageBean bean = ManagerFactory.getManagerService(ParseManager.class).parseObject
                (json, ScanImageBean.class);

        Bitmap bitmap;

        String path = bean.path;

        bitmap = BitmapFactory.decodeFile(path);

        // 获取bitmap的宽高，像素矩阵
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader reader = new MultiFormatReader();

        String result = "";

        try {

            result = reader.decode(binaryBitmap).getText();
            JsPoster.postSuccess(result, jsCallback);
            return;
        } catch (NotFoundException e) {

            e.printStackTrace();

        } catch (ChecksumException e) {

            e.printStackTrace();

        } catch (FormatException e) {

            e.printStackTrace();

        }
        JsPoster.postFailed(jsCallback);


    }

    @Subscribe
    public void OnUploadResult(UploadResultBean uploadResultBean) {
        if (uploadResultBean != null && mPickCallback != null) {
            JsPoster.postSuccess(uploadResultBean.data, mPickCallback);
        }

        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
        mPickCallback = null;
        ManagerFactory.getManagerService(PersistentManager.class).deleteCacheData(Constant
                .ImageConstants.UPLOAD_IMAGE_BEAN);
    }
}
