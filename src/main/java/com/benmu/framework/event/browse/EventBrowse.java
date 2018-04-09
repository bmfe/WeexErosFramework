package com.benmu.framework.event.browse;

import android.content.Context;
import android.content.Intent;

import com.benmu.framework.activity.BrowseImgActivity;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.model.BroeserImgModuleBean;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;

/**
 * Created by Carry on 2017/8/21.
 */

public class EventBrowse extends EventGate{

    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        open(weexEventBean.getJsParams(),context);
    }

    public void open(String params, Context context) {

        BroeserImgModuleBean bean = ManagerFactory.getManagerService(ParseManager.class)
                .parseObject(params, BroeserImgModuleBean.class);
        if (bean == null || bean.getImages() == null || bean.getImages().size() == 0) return;

        Intent intent = new Intent().putExtra(Constant.BROWSE_EVENT.BROWSE_IMG_BEAN, bean)
                .setClass(context, BrowseImgActivity.class);
        context.startActivity(intent);
    }
}
