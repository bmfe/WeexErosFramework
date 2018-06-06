package com.benmu.framework.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benmu.framework.R;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.DefaultNavigationAdapter;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.status.StatusBarManager;
import com.benmu.framework.model.NatigatorModel;
import com.benmu.framework.model.NavigatorBarModel;
import com.benmu.framework.model.NavigatorModel;
import com.benmu.framework.model.RouterModel;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by liuyuanxiao on 2018/5/25.
 */

public class MainWeexFragment extends AbstractWeexFragment {
    public static final String PAGE_URL = "rengerPageUrl";
    private View myLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (myLayout == null) {
            myLayout = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_base_main_layout, null);
            mContainer = (ViewGroup) myLayout.findViewById(R.id.layout_container);
        }
        return myLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setPageUrl(getArguments().getString(PAGE_URL));
        renderPage();
        super.onActivityCreated(savedInstanceState);
    }


    public void setNavigator(NavigatorModel navigatorModel) {
        if (!TextUtils.isEmpty(navigatorModel.navigatorModel)) {
            DefaultNavigationAdapter.setNavigationInfo(navigatorModel.navigatorModel, navigatorModel.centerItemJsCallback);
        }
        if (!TextUtils.isEmpty(navigatorModel.leftNavigatorbarModel)) {
            DefaultNavigationAdapter.setLeftItem(navigatorModel.leftNavigatorbarModel, navigatorModel.leftItemJsCallback);
        }
        if (!TextUtils.isEmpty(navigatorModel.rightNavigatorbarModel)) {
            DefaultNavigationAdapter.setLeftItem(navigatorModel.rightNavigatorbarModel, navigatorModel.rightItemJsCallback);
        }
        if (!TextUtils.isEmpty(navigatorModel.centerNavigatorBarModel)) {
            DefaultNavigationAdapter.setLeftItem(navigatorModel.centerNavigatorBarModel, navigatorModel.centerItemJsCallback);
        }
        DefaultNavigationAdapter.setTabbarNavigation(getActivity(), navigatorModel);
    }

}
