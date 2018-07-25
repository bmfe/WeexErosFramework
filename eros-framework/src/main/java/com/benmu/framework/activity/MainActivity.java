package com.benmu.framework.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.R;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.event.TabbarEvent;
import com.benmu.framework.manager.impl.GlobalEventManager;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.TabbarBadgeModule;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.framework.view.TableView;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;

public class MainActivity extends AbstractWeexActivity {
    private FrameLayout layout_container;
    private ViewStub viewStub_tabView;
    private TableView tableView;
    private BroadcastReceiver mReloadReceiver;
    private RouterModel routerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidBug5497Workaround.assistActivity(this);
        routerModel = (RouterModel) getIntent().getSerializableExtra(Constant.ROUTERPARAMS);
        if (Constant.TABBAR.equals(routerModel.url)) {
            initTabView();
        } else {
            layout_container = (FrameLayout) findViewById(R.id.layout_container);
            initView();
            renderPage();
        }
        initReloadReceiver();
    }


    private void initReloadReceiver() {
        mReloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!Constant.TABBAR.equals(routerModel.url)) {
                    renderPage();
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mReloadReceiver, new
                IntentFilter(WXSDKEngine.JS_FRAMEWORK_RELOAD));
    }

    private void initView() {
        mContainer = (ViewGroup) findViewById(R.id.layout_container);
    }

    private void initTabView() {
        viewStub_tabView = (ViewStub) findViewById(R.id.vs_tabView);
        viewStub_tabView.inflate();
        tableView = (TableView) findViewById(R.id.tabView);
        tableView.setData(BMWXEnvironment.mPlatformConfig.getTabBar());

    }


    @Override
    public boolean navigationListenter(WeexEventBean weexEventBean) {
        if (tableView != null) {
            return tableView.setNaigation(weexEventBean);
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (isHomePage() && BMWXEnvironment.mPlatformConfig.isAndroidIsListenHomeBack()) {
                WXSDKInstance wxsdkInstance = getWXSDkInstance();
                if (wxsdkInstance != null) {
                    GlobalEventManager.homeBack(wxsdkInstance);
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public WXSDKInstance getWXSDkInstance() {
        return (tableView != null) ? tableView.getWXSDKInstance() : super.getWXSDkInstance();

    }

    @Override
    public void refresh() {
        if (tableView != null) {
            tableView.refresh();
        } else {
            super.refresh();
        }
    }

    public void setBadge(TabbarBadgeModule module) {
        if (tableView != null) {
            tableView.setBadge(module);
        }
    }

    public void hideBadge(int index) {
        if (tableView != null) {
            tableView.hideBadge(index);
        }
    }

    public void openPage(int index) {
        if (tableView != null) {
            tableView.openPage(index);
        }
    }

    public int getPageIndex() {
        if (tableView != null) {
            return tableView.getCurrentIndex();
        }
        return -1;
    }

    public void watchIndex(TabbarEvent.TabbarListen tabbarListen) {
        if (tableView != null) {
            tableView.setTabbarListen(tabbarListen);
        }
    }

    public void clearWatch() {
        if (tableView != null) {
            tableView.clearWatch();
        }
    }
}
