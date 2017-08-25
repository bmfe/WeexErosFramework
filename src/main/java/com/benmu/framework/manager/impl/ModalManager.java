package com.benmu.framework.manager.impl;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.manager.Manager;
import com.benmu.widget.view.BMAlert;
import com.benmu.widget.view.BMLoding;

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

        public static void showLoading(Context context, String message, boolean
                canWatchOutsideTouch) {
            if (context instanceof AbstractWeexActivity) {
                AbstractWeexActivity activity = (AbstractWeexActivity) context;
                //TODO
                if (activity.getLoading() == null) {
                    BMLoding loading = new BMLoding(activity);
                    loading.setWatchOutsideTouch(canWatchOutsideTouch);
                    if (!activity.isFinishing() && Looper.myLooper() == Looper.getMainLooper()) {
                        activity.setLoading(loading);
                        activity.getLoading().show();
                    }
                } else {
                    if (!activity.getLoading().isShowing() && !activity.isFinishing() && Looper
                            .myLooper() == Looper.getMainLooper()) {
                        activity.getLoading().show();
                    }
                }
            }
        }

        public static void dismissLoading(Context context) {
            if (context instanceof AbstractWeexActivity) {
                AbstractWeexActivity activity = (AbstractWeexActivity) context;
                if (activity.getLoading() != null && activity.getLoading().isShowing() && !activity
                        .isFinishing() && Looper
                        .myLooper() == Looper.getMainLooper()) {
                    activity.getLoading().dismiss();
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
}
