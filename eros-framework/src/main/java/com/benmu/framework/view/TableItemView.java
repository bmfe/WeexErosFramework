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
import com.benmu.widget.view.CellPointTextView;

/**
 * tabView  下面的 item
 * Created by liuyuanxiao on 2018/5/23.
 */

public class TableItemView extends LinearLayout {
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private LinearLayout itemRootLayout;
    private ImageView itemIconIv, ivFindCell;
    private TextView itemNameTv;
    private CellPointTextView ivGroupCell;
    private int index = -1;
    private String textColor, textSelectColor; // 选中和未选择时的 text 背景颜色
    private String icon, selectedIcon; // 选中和未选择时的 图片地址

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
        ivFindCell = (ImageView) view.findViewById(R.id.ivFindCell);
        itemNameTv = (TextView) view.findViewById(R.id.itemNameTv);
        ivGroupCell = (CellPointTextView) view.findViewById(R.id.ivGroupCell);
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
