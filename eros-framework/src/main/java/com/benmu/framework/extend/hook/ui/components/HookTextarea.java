package com.benmu.framework.extend.hook.ui.components;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.benmu.framework.extend.hook.HookConstants;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.Textarea;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXResourceUtils;

import java.lang.reflect.Field;

/**
 * Created by Carry on 2018/4/17.
 */

public class HookTextarea extends Textarea {
    private String TAG = getClass().getName();

    public HookTextarea(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean
            isLazy) {
        super(instance, dom, parent, isLazy);
        Log.e(TAG, TAG + "init");
    }


    //benmu.org
    @WXComponentProp(name = HookConstants.NAME.TINTCOLOR)
    public void setTintColor(String tintColor) {

        if (TextUtils.isEmpty(tintColor)) return;
        try {
            EditText editText = getHostView();
            Field cursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            cursorDrawableRes.setAccessible(true);
            int drawableId = cursorDrawableRes.getInt(editText);
            Field mEditor = TextView.class.getDeclaredField("mEditor");
            mEditor.setAccessible(true);
            Object editor = mEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field mCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            mCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[1];
            drawables[0] = editText.getContext().getResources().getDrawable(drawableId);
            drawables[0].setColorFilter(WXResourceUtils.getColor(tintColor), PorterDuff.Mode
                    .SRC_IN);
            mCursorDrawable.set(editor, drawables);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //benmu.org

}
