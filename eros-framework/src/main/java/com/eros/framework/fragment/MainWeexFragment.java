package com.eros.framework.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eros.framework.R;
import com.eros.framework.adapter.DefaultNavigationAdapter;
import com.eros.framework.model.NavigatorModel;
import com.taobao.weex.WXSDKEngine;

/**
 * Created by liuyuanxiao on 2018/5/25.
 */

public class MainWeexFragment extends AbstractWeexFragment {
    public static final String PAGE_URL = "rengerPageUrl";
    private View myLayout;
    private BroadcastReceiver mReloadReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (myLayout == null) {
            myLayout = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_base_main_layout, null);
            mContainer = (ViewGroup) myLayout.findViewById(R.id.layout_container);
        }
        initReloadReceiver();
        return myLayout;
    }


    private void initReloadReceiver() {
        mReloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (getUserVisibleHint()) {
                    renderPage();

                }
            }
        };
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReloadReceiver, new
                IntentFilter(WXSDKEngine.JS_FRAMEWORK_RELOAD));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setPageUrl(getArguments().getString(PAGE_URL));
        renderPage();
        super.onActivityCreated(savedInstanceState);
    }


    public void setNavigator(NavigatorModel navigatorModel) {
        DefaultNavigationAdapter.setNavigationInfo(navigatorModel.navigatorModel, navigatorModel.centerItemJsCallback);
        DefaultNavigationAdapter.setLeftItem(navigatorModel.leftNavigatorbarModel, navigatorModel.leftItemJsCallback);
        DefaultNavigationAdapter.setRightItem(navigatorModel.rightNavigatorbarModel, navigatorModel.rightItemJsCallback);
        if (!TextUtils.isEmpty(navigatorModel.centerNavigatorBarModel)) {
            DefaultNavigationAdapter.setCenterItem(navigatorModel.centerNavigatorBarModel, navigatorModel.centerItemJsCallback);
        }
        DefaultNavigationAdapter.setTabbarNavigation(getActivity(), navigatorModel);
    }

}
