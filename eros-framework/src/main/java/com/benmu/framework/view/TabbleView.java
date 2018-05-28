package com.benmu.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.benmu.framework.R;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.fragment.MainWeexFragment;
import com.benmu.framework.model.PlatformConfigBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页 tebView
 * Created by liuyuanxiao on 2018/5/24.
 */

public class TabbleView extends RelativeLayout {
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private LinearLayout llTabBar;
    private ImageView borderLine;
    private ViewPager viewpager;
    private PlatformConfigBean.TabBar tabBarBean;
    private List<Fragment> fragments;
    private MyFragmentAdapter fragmentAdapter;

    public TabbleView(Context context) {
        super(context);
        initView(context);
    }

    public TabbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        fragments = new ArrayList<>();
        view = inflater.inflate(R.layout.view_tab_layout, this);
        llTabBar = (LinearLayout) view.findViewById(R.id.llTabBar);
        borderLine = (ImageView) view.findViewById(R.id.borderLine);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    public void setData(PlatformConfigBean.TabBar tabBar) {
        this.tabBarBean = tabBar;
        // 设置Tab 上面线的颜色
        if (!TextUtils.isEmpty(tabBar.getBorderColor())) {
            borderLine.setBackgroundColor(Color.parseColor(tabBar.getBorderColor()));
        }
        // 设置 Tab 背景
        if (!TextUtils.isEmpty(tabBar.getBackgroundColor())) {
            llTabBar.setBackgroundColor(Color.parseColor(tabBar.getBorderColor()));
        }
        fragmentAdapter = new MyFragmentAdapter(((AbstractWeexActivity) context).getSupportFragmentManager(), fragments);
        initItem(tabBarBean.getList());
        viewpager.setAdapter(fragmentAdapter);
    }

    private void initItem(List<PlatformConfigBean.TabItem> items) {
        // 循环add  tab Item
        for (int i = 0; i < items.size(); i++) {
            Log.e("TabbleView", "url - > " + items.get(i).getPagePath() + " name - > " + items.get(i).getText());
            PlatformConfigBean.TabItem item = items.get(i);
            TableItemView itemView = new TableItemView(context);
            LinearLayout.LayoutParams weight1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            itemView.setLayoutParams(weight1);
            itemView.setData(item);
            itemView.setIndex(i);
            llTabBar.addView(itemView);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewpager.setCurrentItem(((TableItemView) v).getIndex());
                }
            });
            // new fragment
            initFragment(item);
        }
    }


    private void initFragment(PlatformConfigBean.TabItem item) {
        MainWeexFragment fragment = new MainWeexFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MainWeexFragment.PAGE_URL, item.getPagePath());
        fragment.setArguments(bundle);
        fragments.add(fragment);
    }

    /**
     * ViewPage Fragment 适配器
     */
    private static class MyFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    private static class ViewPagerChangeListenr implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
