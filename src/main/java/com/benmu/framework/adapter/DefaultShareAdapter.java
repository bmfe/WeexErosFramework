package com.benmu.framework.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.benmu.framework.R;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ModalManager;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.BaseResultBean;
import com.benmu.framework.model.WebViewParamBean;
import com.benmu.framework.utils.BaseCommonUtil;
import com.benmu.widget.view.BMGridDialog;
import com.taobao.weex.bridge.JSCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carry on 2017/9/26.
 */

public class DefaultShareAdapter {
    private Activity mAct;
    private UMWeb mUMWeb;
    private SHARE_MEDIA[] mPlatforms = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN.toSnsPlatform()
            .mPlatform,
            SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform};
    private JSCallback mSuccess;
    private JSCallback mFailed;
    private ProgressDialog mProgressDialog;

    public void share(Activity activity, String params, JSCallback success, JSCallback fail) {
        if (activity == null || TextUtils.isEmpty(params)) return;
        this.mAct = activity;
        this.mSuccess = success;
        this.mFailed = fail;

        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        final WebViewParamBean.ShareInfo shareInfo = parseManager.parseObject(params,
                WebViewParamBean
                        .ShareInfo.class);
        if (shareInfo == null) return;
        mUMWeb = new UMWeb(shareInfo.getUrl());
        mUMWeb.setTitle(shareInfo.getTitle());
        mUMWeb.setDescription(shareInfo.getContent());
        String image = shareInfo.getImage();
        if (TextUtils.isEmpty(image)) {
            mUMWeb.setThumb(new UMImage(mAct, R.drawable.place_holder));
        } else {
            mUMWeb.setThumb(new UMImage(mAct, image));
        }
        if (shareInfo.getPlatforms() == null) {
            ArrayList<String> platforms = new ArrayList<>();
            platforms.add("Pasteboard");
            //wechat friends
            platforms.add("WechatSession");
            //wechat cricle
            platforms.add("WechatTimeLine");
            shareInfo.setPlatforms(platforms);
        }
        if (!BaseCommonUtil.isWeChatInstall(mAct)) {
            shareInfo.getPlatforms().clear();
            shareInfo.getPlatforms().add("Pasteboard");
        }
        //init share dialog
        List<BMGridDialog.GridItem> items = new ArrayList<>();
        for (String platform : shareInfo.getPlatforms()) {
            switch (platform) {
                case "Pasteboard":
                    items.add(new BMGridDialog.GridItem("", R.drawable.socialize_url, "复制链接",
                            platform));
                    break;
                case "WechatSession":
                    items.add(new BMGridDialog.GridItem("", R.drawable.socialize_wechat, "发送给好友",
                            platform));
                    break;
                case "WechatTimeLine":
                    items.add(new BMGridDialog.GridItem("", R.drawable.socialize_wxcircle,
                            "分享朋友圈", platform));
                    break;
            }
        }

        ModalManager.BmShareDialog.show(mAct, items, new BMGridDialog.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, BMGridDialog.GridItem item, Dialog
                    dialog) {
                if (item == null || item.getTag() == null) return;
                switch ((String) item.getTag()) {
                    case "Pasteboard":
                        copyClipboard(shareInfo.getUrl());
                        break;
                    case "WechatSession":
                        startUmweb(mUMWeb, mPlatforms[0],
                                shareListener);
                        break;
                    case "WechatTimeLine":
                        startUmweb(mUMWeb, mPlatforms[1],
                                shareListener);
                        break;
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }

    private void startUmweb(UMWeb mUMWeb, SHARE_MEDIA mPlatform, UMShareListener shareListener) {
        new ShareAction(mAct).setPlatform(mPlatform).withMedia(mUMWeb).setCallback(shareListener)
                .share();
    }

    private void copyClipboard(String url) {
        BaseCommonUtil.copyString(mAct, url);
        if (mSuccess != null) {
            mSuccess.invoke(new BaseResultBean(0, mAct.getResources().getString(R.string
                    .str_paste_board)));
        }
    }


    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            if (mProgressDialog != null && !mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if (mSuccess != null) {
                mSuccess.invoke(new BaseResultBean(0, mAct.getResources().getString(R.string
                        .str_share_success)));
            } else {
                ModalManager.BmToast.toast(mAct, mAct.getResources().getString
                        (R.string.str_share_success), Toast.LENGTH_SHORT);
            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if (mFailed != null) {
                mFailed.invoke(new BaseResultBean(0, mAct.getResources().getString(R.string
                        .str_share_failed)));
            } else {
                ModalManager.BmToast.toast(mAct, mAct.getResources().getString
                        (R.string.str_share_failed), Toast.LENGTH_SHORT);
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    };

}
