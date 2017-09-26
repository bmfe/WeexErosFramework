package com.benmu.framework.manager.impl;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.benmu.framework.R;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.manager.Manager;
import com.benmu.widget.view.BMAlert;
import com.benmu.widget.view.BMGridDialog;
import com.benmu.widget.view.BMLoding;
import com.benmu.widget.view.loading.SVProgressHUD;

import java.util.List;

import static com.benmu.widget.view.loading.SVProgressHUD.SVProgressHUDMaskType.BlackCancel;
import static com.benmu.widget.view.loading.SVProgressHUD.SVProgressHUDMaskType.Clear;
import static com.benmu.widget.view.loading.SVProgressHUD.SVProgressHUDMaskType.ClearCancel;

/**
 * Created by Carry on 2017/8/7.
 */

public class ModalManager extends Manager {
    public static class BmAlert {
        private static BMAlert mBmAlert = null;

        public static void showAlert(Context context, String title, String message, String okBtn,
                                     DialogInterface.OnClickListener okListenner, String cancelBtn,
                                     DialogInterface.OnClickListener cancelListenner, String
                                             titleAlign, String contentAlign) {
            BMAlert.Builder builder = new BMAlert.Builder(context);
            builder.setTitle(title).setMessage(message).setPositiveButton(okBtn, okListenner)
                    .setTitleAlign(titleAlign).setMessageAlign(contentAlign);
            if (!TextUtils.isEmpty(cancelBtn)) {
                builder.setNegativeButton(cancelBtn, cancelListenner);
            }
            mBmAlert = builder.create();
            if (mBmAlert != null && !mBmAlert.isShowing() && !((Activity) context).isFinishing()) {
                mBmAlert.show();
            }
        }
    }

    public static class BmLoading {
        private static BMLoding mBmLoading = null;

        public static void showLoading(Context context, final String message, boolean
                canWatchOutsideTouch) {
            if (context instanceof AbstractWeexActivity) {
                final AbstractWeexActivity activity = (AbstractWeexActivity) context;
                if (activity.isFinishing()) return;
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    SVProgressHUD.showWithStatus(activity, message, BlackCancel);
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SVProgressHUD.showWithStatus(activity, message, BlackCancel);
                        }
                    });
                }
            }
        }

        public static void dismissLoading(Context context) {
            if (context instanceof AbstractWeexActivity) {
                final AbstractWeexActivity activity = (AbstractWeexActivity) context;
                if (activity.isFinishing()) return;
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    SVProgressHUD.dismiss(activity);
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SVProgressHUD.dismiss(activity);
                        }
                    });
                }
            }

        }
    }

    public static class BmToast {
        private static Toast mToast = null;

        private static void makeToast(Context context, String message, int duration) {

            if (TextUtils.isEmpty(message) || context == null) {
                return;
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (mToast == null) {
                    mToast = Toast.makeText(context, message, duration);
                    //mToast.setGravity(Gravity.CENTER, 0, 0);
                }
                mToast.setDuration(duration);
                mToast.setText(message);
                mToast.show();
            } else {
                Log.i("BMModalManager", "toast can not show in child thread");
            }
        }

        public static void toast(Context context, String message, int duration) {
            makeToast(context, message, duration);
        }

    }

    public static class BmShareDialog {

        private static BMGridDialog mDialog;

        public static void show(Activity activity, List<BMGridDialog.GridItem> list, BMGridDialog
                .OnItemClickListener onItemClickListener) {
            if (list == null) return;
            BMGridDialog.Builder builder = new BMGridDialog.Builder(activity, R.style
                    .ActionSheetDialogStyle);
            mDialog = builder.setGravity(Gravity.BOTTOM).setNegativeButton("取消",
                    null).setAdapter(new
                    BMGridDialog.Adapter(activity, list, 4)).setOnItemClickListenner
                    (onItemClickListener).build();
            mDialog.show();
        }

        public static void dismiss() {
            if (mDialog != null) {
                mDialog.hide();
            }
        }
    }
}
