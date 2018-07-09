package com.benmu.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benmu.framework.R;
import com.benmu.framework.model.PlatformConfigBean;
import com.benmu.framework.utils.BMHookGlide;
import com.benmu.framework.utils.ResourceUtil;
import com.benmu.widget.view.CellPointTextView;
import com.taobao.weex.utils.WXResourceUtils;

/**
 * tabView  下面的 item
 * Created by liuyuanxiao on 2018/5/23.
 */

public class TableItemView extends LinearLayout {
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private LinearLayout itemRootLayout;
    private ImageView itemIconIv;
    private TextView itemNameTv;
    private int index = -1;
    private String textColor, textSelectColor; // 选中和未选择时的 text 背景颜色
    private String icon, selectedIcon; // 选中和未选择时的 图片地址
    private CircleTextView tb_circleView, tb_circlePoint;

    public TableItemView(Context context) {
        super(context);
        initView(context);
    }

    public TableItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TableItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.view_tab_item_layout, this);
        itemRootLayout = (LinearLayout) view.findViewById(R.id.itemRootLayout);
        itemIconIv = (ImageView) view.findViewById(R.id.itemIconIv);
        itemNameTv = (TextView) view.findViewById(R.id.itemNameTv);
        tb_circleView = (CircleTextView) view.findViewById(R.id.tb_circleTv);
        tb_circlePoint = (CircleTextView) view.findViewById(R.id.tb_circleView);
    }

    public void setData(PlatformConfigBean.TabItem item) {
        Log.e("TableItemView", "name - > " + item.getText());
        itemNameTv.setText(item.getText());
        this.icon = item.getIcon();
        this.selectedIcon = item.getSelectedIcon();
        if (!TextUtils.isEmpty(icon)) {
            BMHookGlide.load(context, icon).into(itemIconIv);
        }
        if (!TextUtils.isEmpty(textColor)) {
            itemNameTv.setTextColor(Color.parseColor(textColor));
        }
        if (index == 0) {
            if (!TextUtils.isEmpty(selectedIcon)) {
                BMHookGlide.load(context, selectedIcon).into(itemIconIv);
            }
            if (!TextUtils.isEmpty(textSelectColor)) {
                itemNameTv.setTextColor(Color.parseColor(textSelectColor));
            }
        }
    }

    public void showPoint(boolean show) {
        if (show) {
            tb_circlePoint.setVisibility(View.VISIBLE);
            tb_circleView.setVisibility(View.GONE);
        } else {
            tb_circlePoint.setVisibility(View.GONE);

        }
    }

    public void setCircText(String text) {
        tb_circlePoint.setVisibility(View.GONE);
        tb_circleView.setVisibility(View.VISIBLE);
        tb_circleView.setText(text);

    }

    public void showCircText(boolean show) {
        int vis = show ? View.VISIBLE : View.GONE;
        tb_circleView.setVisibility(vis);
    }

    public void setCircTextColor(String color) {
        tb_circleView.setTextColor(WXResourceUtils.getColor(color));
    }

    public void setBgColor(String color) {
        tb_circleView.setBackColor(WXResourceUtils.getColor(color));
        tb_circlePoint.setBackColor(WXResourceUtils.getColor(color));
    }


    /**
     * 设置 icon 颜色
     *
     * @param index
     */
    public void setSelector(int index) {
        if (this.index == index) {
            if (!TextUtils.isEmpty(selectedIcon)) {
                BMHookGlide.load(context, selectedIcon).into(itemIconIv);
            }
            if (!TextUtils.isEmpty(textSelectColor)) {
                itemNameTv.setTextColor(Color.parseColor(textSelectColor));
            }

        } else {
            if (!TextUtils.isEmpty(icon)) {
                BMHookGlide.load(context, icon).into(itemIconIv);
            }
            if (!TextUtils.isEmpty(textColor)) {
                itemNameTv.setTextColor(Color.parseColor(textColor));
            }
        }
    }

    /**
     * 设置 文字颜色
     *
     * @param textColor
     * @param textSelectColor
     */
    public void setTextColor(String textColor, String textSelectColor) {
        this.textColor = textColor;
        this.textSelectColor = textSelectColor;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
