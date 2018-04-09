package com.benmu.framework.event.router;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.RouterManager;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.wxbase.EventGate;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by liuyuanxiao on 17/12/6.
 */

public class EventFinish extends EventGate{
    @Override
    public void perform(Context context, WeexEventBean weexEventBean) {
        finish(context, weexEventBean.getJscallback());
    }

    public void finish(Context context, JSCallback jscallback) {
        RouterManager routerManager = ManagerFactory.getManagerService(RouterManager.class);
        boolean result = routerManager.finish(context);

//        if (activity != null && activity instanceof AbsWeexActivity) {
//            AbsWeexActivity target = (AbsWeexActivity) activity;
//            if (target.mUrl != null) {
//                target.refresh();
//                if (jscallback != null) {
//                    BaseResultBean bean = new BaseResultBean();
//                    bean.resCode = 0;
//                    bean.msg = activity.getClass().getSimpleName() + "刷新成功";
//                    jscallback.invoke(bean);
//                }
//            }
//        }
    }
}
