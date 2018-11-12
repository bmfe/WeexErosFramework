package com.eros.framework.extend.comoponents;

import static com.taobao.weex.dom.WXStyle.UNSET;
import static com.taobao.weex.utils.WXUtils.isUndefined;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;

import com.eros.framework.extend.comoponents.view.BMWXTextView;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.TextDecorationSpan;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXCustomStyleSpan;
import com.taobao.weex.dom.WXLineHeightSpan;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.layout.MeasureMode;
import com.taobao.weex.layout.MeasureSize;
import com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.flat.FlatComponent;
import com.taobao.weex.ui.flat.widget.TextWidget;
import com.taobao.weex.ui.view.WXTextView;
import com.taobao.weex.utils.FontDO;
import com.taobao.weex.utils.TypefaceUtil;
import com.taobao.weex.utils.WXDomUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXResourceUtils;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Created by Carry on 17/3/27.
 */
public class BMWXText extends WXComponent<BMWXTextView> {
    public BMWXText(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
        registerBroadCast();
        initFontSize();
    }


    public static final int sDEFAULT_SIZE = 32;
    private DefaultBroadcastReceiver mReceiver;
    private Layout mCurrentLayout;
    private String mCurrentFontSize = "NORM";
    private String mChangeFontSize;
    private float mCurrentEnlarge;
    private float mCurrentScale = 1;
    private boolean has = false;

    public static class Creator implements ComponentCreator {

        public WXComponent createInstance(WXSDKInstance instance, WXVContainer
                parent, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException,
                InstantiationException {
            return new WXText(instance, parent, basicComponentData);
        }
    }

    @Deprecated
    public BMWXText(WXSDKInstance instance, WXVContainer parent, String
            instanceId, boolean isLazy, BasicComponentData basicComponentData) {
        this(instance, parent, basicComponentData);
    }


    @Override
    protected BMWXTextView initComponentHostView(@NonNull Context context) {
        BMWXTextView textView = new BMWXTextView(context);
        return textView;
    }

    @Override
    public void updateExtra(Object extra) {
        if (extra instanceof Layout &&
                getHostView() != null && !extra.equals(getHostView().getTextLayout())) {

            final Layout layout = (Layout) extra;
            getHostView().setTextLayout(layout);
            getHostView().invalidate();
        }
        updateFontSize();
//        if (!has) {
//            WXStyle styles = getDomObject().getStyles();
//            styles.put("width", 1080);
//            styles.put("height", Math.ceil(38.8*750/1080.0));
//            updateStyle(styles);
//            has = true;
//        }
    }

    @Override
    public void refreshData(WXComponent component) {
        super.refreshData(component);
        if (component instanceof WXText) {
            updateExtra(component.getExtra());
        }
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case Constants.Name.LINES:
            case Constants.Name.FONT_SIZE:
            case Constants.Name.FONT_WEIGHT:
            case Constants.Name.FONT_STYLE:
            case Constants.Name.COLOR:
            case Constants.Name.TEXT_DECORATION:
            case Constants.Name.FONT_FAMILY:
            case Constants.Name.TEXT_ALIGN:
            case Constants.Name.TEXT_OVERFLOW:
            case Constants.Name.LINE_HEIGHT:
            case Constants.Name.VALUE:
                return true;
            default:
                return super.setProperty(key, param);
        }
    }

    /**
     * Flush view no matter what height and width the  specifies.
     *
     * @param extra must be a {@link Layout} object, otherwise, nothing will happen.
     */
    private void flushView(Object extra) {

    }


    @Override
    protected Object convertEmptyProperty(String propName, Object originalValue) {
        switch (propName) {
            case Constants.Name.FONT_SIZE:
                return WXText.sDEFAULT_SIZE;
            case Constants.Name.COLOR:
                return "black";
        }
        return super.convertEmptyProperty(propName, originalValue);
    }


    /**
     * 自定义方法start
     **/
    private void initFontSize() {
        SharedPreferences sp = getContext().getSharedPreferences("JYT_NATIVE_SP", Context
                .MODE_PRIVATE);
        mChangeFontSize = sp.getString("SP_FONTSIZE", null);
    }

    private void registerBroadCast() {
        mReceiver = new DefaultBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.benmu.jyt.ACTION_GOBALFONTSIZE_CHANGE");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver, filter);
    }


    private void updateFontSize() {
        if (mChangeFontSize == null) {
            return;
        }

        WXStyle styles = null;
        WXAttr attrs = null;
        styles = getStyles();
        attrs = getAttrs();
        if ("iconfont".equals(styles.get("fontFamily")) || attrs.get("changeFont") != null && !Boolean.valueOf((String) attrs.get
                ("changeFont"))) {
            return;
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
        WXStyle wxStyle = getStyles();
        Object object = wxStyle.get("fontSize");
        if (object instanceof Integer) {
            int fontSize = (int) object;
            int changeFontSize = (int) (fontSize * (scale));
            wxStyle.put("fontSize", changeFontSize);

        }
        //设置lineHeight
        Object lineHeight = wxStyle.get("lineHeight");
        if (lineHeight instanceof Integer) {
            int target = (int) lineHeight;
            wxStyle.put("lineHeight", (int) (target * scale));
        }


        updateStyle(wxStyle);

        mCurrentFontSize = mChangeFontSize;

    }

    /**
     * 自定义方法end
     **/


    /**
     * 自定义方法start
     **/

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


    public void updateStyle(Map<String, Object> styles) {
//        Message message = Message.obtain();
//        WXDomTask task = new WXDomTask();
//        task.instanceId = getInstanceId();
//        task.args = new ArrayList<>();
//
//        JSONObject styleJson = new JSONObject(styles);
//        task.args.add(getRef());
//        task.args.add(styleJson);
//        task.args.add(false);//flag pesudo
//        message.obj = task;
//        message.what = WXDomHandler.MsgType.WX_DOM_UPDATE_STYLE;
//        WXSDKManager.getInstance().getWXDomManager().sendMessage(message);

//        WXBridgeManager.getInstance().post(new Runnable() {
//            @Override
//            public void run() {
//                WXBridgeManager.getInstance().setDefaultRootSize(getInstanceId(), realWidth, realHeight, isWidthWrapContent,
//                        isHeightWrapContent);
//            }
//        });
    }

}


