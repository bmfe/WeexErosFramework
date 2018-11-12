package com.eros.framework.extend.comoponents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXFrameLayout;

/**
 * Created by Carry on 17/1/19.
 */

public class BMView extends WXVContainer<WXFrameLayout> {
    private WXFrameLayout layout;

    public BMView(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent,basicComponentData);

    }

    @Override
    protected WXFrameLayout initComponentHostView(@NonNull Context context) {
        layout = new WXFrameLayout(context);
        return layout;
    }

    @WXComponentProp(name = "hidden")
    public void setVisibility(Boolean visibility) {
        if (visibility) {
            layout.setVisibility(View.GONE);
        } else {
            layout.setVisibility(View.VISIBLE);
        }


    }


    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
    }
}
