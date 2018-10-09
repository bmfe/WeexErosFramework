package com.eros.framework.event.nav;


import android.content.Context;

import com.eros.framework.activity.AbstractWeexActivity;
import com.eros.framework.model.WeexEventBean;
import com.eros.wxbase.EventGate;

/**
 * Created by liuyuanxiao on 2018/5/29.
 */

public class NavigationEventGate extends EventGate {

    public boolean setNavitionListenter(WeexEventBean weexEventBean) {
        Context context = weexEventBean.getContext();
        if (context != null) {
            if (context instanceof AbstractWeexActivity) {
                AbstractWeexActivity abs = (AbstractWeexActivity) context;
                return abs.navigationListenter(weexEventBean);
            }
        }
        return false;
    }
}
