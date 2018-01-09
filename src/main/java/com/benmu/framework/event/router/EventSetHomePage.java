package com.benmu.framework.event.router;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.StorageManager;
import com.benmu.framework.model.RouterModel;

/**
 * Created by liuyuanxiao on 18/1/4.
 */

public class EventSetHomePage {
    @SuppressWarnings("WrongConstant")
    public void setHomePage(Context context, String params) {
        StorageManager storageManager = ManagerFactory.getManagerService(StorageManager.class);
        storageManager.setData(context, Constant.SP.SP_HOMEPAGE_URL, params);


        String homePage = BMWXEnvironment.mPlatformConfig.getPage().getHomePage();
        RouterModel router = new RouterModel(homePage, Constant.ACTIVITIES_ANIMATION
                .ANIMATION_PUSH, null, null, false, null);
        Intent intent = performStartActivity(router, Constant.BMPAGE_CATEGORY);
        PendingIntent restartIntent = PendingIntent.getActivity(
                context.getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_CLEAR_TASK);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100,
                restartIntent);
    }

    private Intent performStartActivity(RouterModel routerModel, String bmpageCategory) {
        String pathUrl = routerModel.url;
        if (TextUtils.isEmpty(pathUrl)) return null;
        Uri pathUri = Uri.parse(pathUrl);
        if (!TextUtils.equals("http", pathUri.getScheme()) && !TextUtils.equals("https", pathUri
                .getScheme())) {
            pathUri = Uri.parse(BMWXEnvironment.mPlatformConfig.getUrl().getJsServer() +
                    "/dist/js" + pathUrl);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra(Constant.ROUTERPARAMS, routerModel);
        intent.setData(pathUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addCategory(bmpageCategory);
        return intent;
    }
}
