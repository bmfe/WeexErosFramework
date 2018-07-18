package com.benmu.framework.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.benmu.framework.R;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.DefaultNavigationAdapter;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.event.TabbarEvent;
import com.benmu.framework.fragment.MainWeexFragment;
import com.benmu.framework.model.NatigatorModel;
import com.benmu.framework.model.NavigatorModel;
import com.benmu.framework.model.PlatformConfigBean;
import com.benmu.framework.model.TabbarBadgeModule;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.widget.utils.ColorUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 首页 tebView
 * Created by liuyuanxiao on 2018/5/24.
 */

public class TableView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private LinearLayout llTabBar;
    private ImageView borderLine;
    private NoScrollViewPager viewpager;
    private PlatformConfigBean.TabBar tabBarBean;
    private List<MainWeexFragment> fragments;
    private MyFragmentAdapter fragmentAdapter;
    private SparseArray<NavigatorModel> navigatorArray;
    private Activity activity;
    private TabbarEvent.TabbarListen tabbarListen;

    public TableView(Context context) {
        super(context);
        initView(context);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        activity = (Activity) context;
        this.inflater = LayoutInflater.from(context);
        fragments = new ArrayList<>();
        view = inflater.inflate(R.layout.view_tab_layout, this);
        llTabBar = (LinearLayout) view.findViewById(R.id.llTabBar);
        borderLine = (ImageView) view.findViewById(R.id.borderLine);
        viewpager = (NoScrollViewPager) view.findViewById(R.id.viewpager);
    }

    public void setData(PlatformConfigBean.TabBar tabBar) {
        this.tabBarBean = tabBar;
        navigatorArray = new SparseArray<>();
        // 设置Tab 上面线的颜色
        if (!TextUtils.isEmpty(tabBar.getBorderColor())) {
            borderLine.setBackgroundColor(ColorUtils.getColor(tabBar.getBorderColor()));
        }
        // 设置 Tab 背景
        if (!TextUtils.isEmpty(tabBar.getBackgroundColor())) {
            llTabBar.setBackgroundColor(ColorUtils.getColor(tabBar.getBackgroundColor()));
        }
        fragmentAdapter = new MyFragmentAdapter(((AbstractWeexActivity) context).getSupportFragmentManager(), fragments);
        initItem(tabBar);
        viewpager.setAdapter(fragmentAdapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(5);

        DefaultNavigationAdapter.setTabbarNavigation(activity, navigatorArray.get(0));

    }


    /**
     * 初始化各个Item
     *
     * @param tabBar
     */
    private void initItem(PlatformConfigBean.TabBar tabBar) {
        List<PlatformConfigBean.TabItem> items = tabBar.getList();
        // 循环add  tab Item
        for (int i = 0; i < items.size(); i++) {
            PlatformConfigBean.TabItem item = items.get(i);
            TableItemView itemView = new TableItemView(context);
            LinearLayout.LayoutParams weight1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            itemView.setLayoutParams(weight1);
            itemView.setTextColor(tabBar.getColor(), tabBar.getSelectedColor());
            itemView.setIndex(i);
            itemView.setData(item);
            llTabBar.addView(itemView);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewpager.setCurrentItem(((TableItemView) v).getIndex(), false);
                }
            });
            // new fragment
            initFragment(item, i);
        }
    }

    public void setBadge(TabbarBadgeModule module) {
        TableItemView itemView = (TableItemView) llTabBar.getChildAt(module.getIndex());
        if (!TextUtils.isEmpty(module.getTextColor())) {
            itemView.setCircTextColor(module.getTextColor());
        }
        if (!TextUtils.isEmpty(module.getBgColor())) {
            itemView.setBgColor(module.getBgColor());
        }
        if (module.getValue() == 0) {
            itemView.showPoint(true);
        } else {
            itemView.setCircText(String.valueOf(module.getValue()));
        }

    }

    public void hideBadge(int index) {
        TableItemView itemView = (TableItemView) llTabBar.getChildAt(index);
        itemView.showPoint(false);
        itemView.showCircText(false);
    }

    public void openPage(int index) {
        viewpager.setCurrentItem(index);
    }

    /**
     * 初始化 Fragment
     */

    private void initFragment(PlatformConfigBean.TabItem item, int index) {
        MainWeexFragment fragment = new MainWeexFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MainWeexFragment.PAGE_URL, item.getPagePath());
        fragment.setArguments(bundle);
        fragments.add(fragment);
        NavigatorModel model = new NavigatorModel();
        model.navigatorModel = getNavStr(item);
        navigatorArray.append(index, model);
    }


    private String getNavStr(PlatformConfigBean.TabItem item) {
        NatigatorModel model = new NatigatorModel();
        model.setNavShow(item.isNavShow());
        model.setTitle(item.getNavTitle());
        return JSON.toJSONString(model);
    }


    public WXSDKInstance getWXSDKInstance() {
        MainWeexFragment fragment = fragments.get(viewpager.getCurrentItem());
        return fragment.getWXSDKInstance();
    }

    public void refresh() {
        MainWeexFragment fragment = fragments.get(viewpager.getCurrentItem());
        fragment.refresh();
    }

    /**
     * ViewPager 滑动时 动态切换底部按钮的 文字颜色和 图片
     *
     * @param index
     */
    private void setCurrentItem(int index) {
        for (int i = 0; i < llTabBar.getChildCount(); i++) {
            TableItemView itemView = (TableItemView) llTabBar.getChildAt(i);
            itemView.setSelector(index);
        }
        MainWeexFragment fragment = fragments.get(index);
        fragment.setNavigator(navigatorArray.get(index));
    }


    /**
     * 接通 navigator ，前端可以直接 使用 navigator  设置到 fragment
     *
     * @param weexEventBean 参数对象
     * @return
     */
    public boolean setNaigation(WeexEventBean weexEventBean) {
        String params = weexEventBean.getJsParams();
        JSCallback jsCallback = weexEventBean.getJscallback();
        String type = weexEventBean.getKey();

        int currentIndex = viewpager.getCurrentItem();

        for (int i = 0; i < fragments.size(); i++) {
            MainWeexFragment fragment = fragments.get(i);
            if (fragment.getWxInstanseHasCode() == (int) weexEventBean.getExpand()) {
                NavigatorModel navigatorModel = navigatorArray.get(i);
                switch (type) {
                    case WXEventCenter.EVENT_NAVIGATIONINFO: //setNavigationInfo
                        navigatorModel.navigatorModel = params;
                        if (currentIndex == i) {
                            DefaultNavigationAdapter.setNavigationInfo(params, jsCallback);
                        }
                        break;
                    case WXEventCenter.EVENT_LEFTITEM: //setLeftItem
                        navigatorModel.leftNavigatorbarModel = params;
                        navigatorModel.leftItemJsCallback = jsCallback;
                        if (currentIndex == i) {
                            DefaultNavigationAdapter.setLeftItem(params, jsCallback);
                        }
                        break;
                    case WXEventCenter.EVENT_RIGHTITEM://setRightItem
                        navigatorModel.rightNavigatorbarModel = params;
                        navigatorModel.rightItemJsCallback = jsCallback;
                        if (currentIndex == i) {
                            DefaultNavigationAdapter.setRightItem(params, jsCallback);
                        }
                        break;
                    case WXEventCenter.EVENT_CENTERITEM: //setCenterItem
                        navigatorModel.centerNavigatorBarModel = params;
                        navigatorModel.centerItemJsCallback = jsCallback;
                        if (currentIndex == i) {
                            DefaultNavigationAdapter.setCenterItem(params, jsCallback);
                        }
                        break;
                }
                if (currentIndex == i) {
                    DefaultNavigationAdapter.setTabbarNavigation(activity, navigatorModel);
                }
            }

        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentItem(position);
        if (tabbarListen != null) {
            tabbarListen.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * ViewPage Fragment 适配器
     */
    private static class MyFragmentAdapter extends FragmentPagerAdapter {
        private List<MainWeexFragment> fragments;

        public MyFragmentAdapter(FragmentManager fm, List<MainWeexFragment> fragments) {
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

    private int getRandom() {
        Random rand = new Random();
        return rand.nextInt(160);
    }

    public int getCurrentIndex() {
        return viewpager.getCurrentItem();
    }

    public void setTabbarListen(TabbarEvent.TabbarListen tabbarListen) {
        this.tabbarListen = tabbarListen;
    }

    public void clearWatch() {
        this.tabbarListen = null;
    }

}
