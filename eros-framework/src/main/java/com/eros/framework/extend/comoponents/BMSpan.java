//package com.eros.framework.extend.comoponents;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.text.TextPaint;
//import android.text.style.ClickableSpan;
//import android.view.View;
//
//import com.taobao.weex.WXSDKInstance;
//import com.taobao.weex.dom.WXDomObject;
//import com.taobao.weex.ui.action.BasicComponentData;
//import com.taobao.weex.ui.component.WXComponent;
//import com.taobao.weex.ui.component.WXVContainer;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by Carry on 2017/6/14.
// */
//
//public class BMSpan extends WXComponent<View> {
//    private Set<String> mAppendEvents = new HashSet<>();
//
//    public BMSpan(WXSDKInstance instance,  WXVContainer parent, String
//            instanceId, boolean isLazy, BasicComponentData basicComponentData) {
//        super(instance,parent, instanceId, isLazy,basicComponentData);
//    }
//
//    public BMSpan(WXSDKInstance instance,WXVContainer parent, boolean isLazy, BasicComponentData basicComponentData) {
//        super(instance, parent, isLazy,basicComponentData);
//    }
//
//    public BMSpan(WXSDKInstance instance,  WXVContainer parent, BasicComponentData basicComponentData) {
//        super(instance, parent,basicComponentData);
//        postBubbleEvent(parent);
//    }
//
//
//    public void postBubbleEvent(WXComponent parent) {
//        if (parent == null) return;
//        if (parent instanceof BMRich) {
//            BMRich bmRichText = (BMRich) parent;
//            bmRichText.receiveBubbleEvent(object);
//        } else {
//            postBubbleEvent(parent.getParent());
//        }
//    }
//
//
//    @Override
//    protected View initComponentHostView(@NonNull Context context) {
//        return new View(context);
//    }
//
//    @Override
//    public void updateExtra(Object extra) {
//        super.updateExtra(extra);
//    }
//
//    static class BMRichClickSpan extends ClickableSpan {
//        private final View.OnClickListener mListener;
//
//        public BMRichClickSpan(View.OnClickListener mListener) {
//            this.mListener = mListener;
//        }
//
//        @Override
//        public void onClick(View v) {
//            mListener.onClick(v);
//        }
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            ds.setUnderlineText(false);    //去除超链接的下划线
//        }
//    }
//}
//
//
//
