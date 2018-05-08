package com.benmu.framework.extend.hook.ui.components;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.benmu.framework.extend.hook.ui.view.HookWXImageView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXImageView;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Carry on 2018/4/17.
 */

public class HookImage extends WXImage {
    private String TAG = getClass().getName();

    public HookImage(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String
            instanceId, boolean isLazy) {
        super(instance, dom, parent, instanceId, isLazy);
        Log.e(TAG, TAG + "init");
    }

    public HookImage(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        Log.e(TAG, TAG + "init");
    }

    @Override
    protected ImageView initComponentHostView(@NonNull Context context) {
        WXImageView view = new HookWXImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setCropToPadding(true);
        }
        view.holdComponent(this);
        return view;
    }


    public static class Ceator implements ComponentCreator {
        public WXComponent createInstance(WXSDKInstance instance, WXDomObject node, WXVContainer parent) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            return new HookImage(instance,node,parent);
        }
    }
}
