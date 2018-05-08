package com.benmu.framework.extend.hook.ui.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXDomHandler;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.dom.WXDomTask;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.component.WXVContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Carry on 2018/4/17.
 */

public class HookWXText extends WXText {
    private String TAG = getClass().getName();

    public HookWXText(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String
            instanceId, boolean isLazy) {
        super(instance, dom, parent, instanceId, isLazy);
        Log.e(TAG, "HookWXText init");
        initFontSize();
    }

    public HookWXText(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        Log.e(TAG, "HookWXText init");
        initFontSize();
    }


    public static class Creator implements ComponentCreator {

        public WXComponent createInstance(WXSDKInstance instance, WXDomObject node, WXVContainer
                parent) throws IllegalAccessException, InvocationTargetException,
                InstantiationException {
            return new HookWXText(instance, node, parent);
        }
    }


    @Override
    public void updateExtra(Object extra) {
        super.updateExtra(extra);
        updateFontSize();
    }

    private String mCurrentFontSize = "NORM";
    private String mChangeFontSize;
    private float mCurrentEnlarge;
    private float mCurrentScale = 1;
    private DefaultBroadcastReceiver mReceiver;

    private void initFontSize() {
        registerBroadCast();
        SharedPreferences sp = getContext().getSharedPreferences("JYT_NATIVE_SP", Context
                .MODE_PRIVATE);
        mChangeFontSize = sp.getString("SP_FONTSIZE", null);
    }


    private void updateFontSize() {
        if (getDomObject() != null && getDomObject().getStyles().get(Constants.Name.FONT_SIZE) ==
                null) {
            WXStyle s = getDomObject().getStyles();
            s.put(Constants.Name.FONT_SIZE, 30);
            updateStyle(s);
            return;
        }
        if (mChangeFontSize == null) {
            return;
        }
        WXStyle styles = null;
        WXAttr attrs = null;
        if (getDomObject() != null) {
            styles = getDomObject().getStyles();
            attrs = getDomObject().getAttrs();
            if ((styles != null && "iconfont".equals(styles.get("fontFamily"))) || (attrs != null
                    && attrs.get("changeFont") != null && !Boolean.valueOf((String) attrs.get
                    ("changeFont")))) {
                return;
            }
        }

        float scale = 0;
        //获取fontScale字段
        if (attrs != null && attrs.get("fontScale") != null) {
            float fontScale = Float.valueOf((String) attrs.get("fontScale"));
            mCurrentScale = fontScale / mCurrentScale;
        }
        if (mChangeFontSize.equals(mCurrentFontSize) && mCurrentScale == 1) {
            return;
        }
        //获取scale字段 在标准字体下不产生变化
        if (attrs != null && attrs.get("scale") != null && !(scale > 0)) {
            scale = Float.valueOf((String) attrs.get("scale"));
            float change = getFixedEnlarge(mChangeFontSize, scale);
            float current = getFixedEnlarge(mCurrentFontSize, scale);
            scale = change / current;
        }
        //根据全局字体配置设置字体大小
        if (!(scale > 0)) {
            float current = getEnlarge(mCurrentFontSize);
            float change = getEnlarge(mChangeFontSize);
            scale = change / current * mCurrentScale;
        }
        if (getDomObject() != null && getDomObject().getStyles() != null) {
            WXStyle wxStyle = getDomObject().getStyles();
            Object object = wxStyle.get("fontSize");
            if (object instanceof Integer) {
                int fontSize = (int) object;
                int changeFontSize = Math.round(fontSize * (scale));
                wxStyle.put("fontSize", changeFontSize);

            }
            //设置lineHeight
            Object lineHeight = wxStyle.get("lineHeight");
            if (lineHeight instanceof Integer) {
                int target = (int) lineHeight;
                wxStyle.put("lineHeight", Math.round(target * scale));
            }


            updateStyle(wxStyle);

        }
        mCurrentFontSize = mChangeFontSize;

    }




    public void updateStyle(Map<String,Object> styles){
        Message message = Message.obtain();
        WXDomTask task = new WXDomTask();
        task.instanceId = getInstanceId();
        task.args = new ArrayList<>();

        JSONObject styleJson = new JSONObject(styles);
        task.args.add(getRef());
        task.args.add(styleJson);
        task.args.add(false);//flag pesudo
        message.obj = task;
        message.what = WXDomHandler.MsgType.WX_DOM_UPDATE_STYLE;
        WXSDKManager.getInstance().getWXDomManager().sendMessage(message);
    }

    private float getEnlarge(String fontsize) {
        if ("NORM".equals(fontsize)) {
            return 1;
        } else if ("BIG".equals(fontsize)) {
            return 1.15f;
        } else if ("EXTRALARGE".equals(fontsize)) {
            return 1.3f;
        } else {
            throw new RuntimeException("未知的字体大小" + fontsize);
        }
    }

    private float getFixedEnlarge(String fontsize, float scale) {
        if ("NORM".equals(fontsize)) {
            return 1;
        } else if ("BIG".equals(fontsize)) {
            return scale;
        } else if ("EXTRALARGE".equals(fontsize)) {
            return scale;
        } else {
            throw new RuntimeException("未知的字体大小" + fontsize);
        }
    }


    private void registerBroadCast() {
        mReceiver = new DefaultBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.benmu.jyt.ACTION_GOBALFONTSIZE_CHANGE");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver, filter);
    }


    public class DefaultBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String size = intent.getStringExtra("currentFontSize");
            if (size == null) {
                return;
            }
            mChangeFontSize = size;
            updateFontSize();
        }
    }


    //benmu.org



}
