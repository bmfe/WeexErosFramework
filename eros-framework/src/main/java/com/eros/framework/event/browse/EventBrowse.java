package com.eros.framework.event.browse;

import android.content.Context;
import android.content.Intent;

import com.eros.framework.activity.BrowseImgActivity;
import com.eros.framework.constant.Constant;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.model.BroeserImgModuleBean;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;

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
