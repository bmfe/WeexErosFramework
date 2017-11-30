package com.benmu.framework.manager.impl;

import android.Manifest;
import android.content.Context;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapsInitializer;
import com.benmu.framework.BMWXEnvironment;
import com.benmu.framework.R;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.GeoResultBean;
import com.benmu.framework.model.PlatformConfigBean;

import java.util.List;

/**
 * Created by Carry on 2017/11/17.
 */

public class GeoManager extends Manager implements AMapLocationListener {
    private AMapLocationClient mClient;
    private static final long LOCATE_INTERVAL = 2000;
    private boolean mInit = false;

    public void init() {
        PlatformConfigBean.Amap amap = BMWXEnvironment.mPlatformConfig.getAmap();
        if (amap != null) {
            String androidAppKey = amap.getAndroidAppKey();
            if (!TextUtils.isEmpty(androidAppKey) && !Constant.AMAP_KEY.equals(androidAppKey)) {
                MapsInitializer.setApiKey(androidAppKey);
                AMapLocationClient.setApiKey(androidAppKey);

            } else {
                MapsInitializer.setApiKey(Constant.AMAP_KEY);
                AMapLocationClient.setApiKey(Constant.AMAP_KEY);
            }
            mInit = true;
        }
    }


    public void startLocation(final Context context) {
        if (!mInit) {
            GeoResultBean bean = new GeoResultBean();
            bean.msg = BMWXEnvironment.mApplicationContext.getResources().getString(R.string
                    .str_init_failed);
            bean.resCode = 9;
            ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(bean);
            return;
        }
        PermissionManager permissionManager = ManagerFactory.getManagerService(PermissionManager
                .class);

        if (permissionManager.hasPermissions(context, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS)) {
            if (mClient == null) {
                initLocationClient(context);
            }
            mClient.startLocation();
        } else {
            //request permisson
            final String[] needPermission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
            permissionManager.requestPermissions(context, new PermissionManager
                    .PermissionListener() {

                @Override
                public void onPermissionsGranted(List<String> perms) {
                    if (perms != null && perms.size() == needPermission.length) {
                        //all granted
                        if (mClient == null) {
                            initLocationClient(context);
                        }
                        mClient.startLocation();
                    } else {
                        //failed
                        GeoResultBean bean = new GeoResultBean();
                        bean.msg = BMWXEnvironment.mApplicationContext.getResources().getString(R
                                .string
                                .str_privilege_deny);
                        bean.resCode = 9;
                        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                                (bean);
                    }
                }

                @Override
                public void onPermissionsDenied(List<String> perms) {
                    GeoResultBean bean = new GeoResultBean();
                    bean.msg = BMWXEnvironment.mApplicationContext.getResources().getString(R.string
                            .str_privilege_deny);
                    bean.resCode = 9;
                    ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                            (bean);
                }

                @Override
                public void onPermissionRequestRejected() {

                }
            }, needPermission);

        }
    }

    private void initLocationClient(Context context) {
        mClient = new AMapLocationClient(context);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode
                .Hight_Accuracy);
        mLocationOption.setInterval(LOCATE_INTERVAL);
        mClient.setLocationOption(mLocationOption);
        mClient.setLocationListener(this);
    }

    public void onPause() {
        if (mClient != null) {
            mClient.stopLocation();
        }
    }

    public void onDestory() {
        if (mClient != null) {
            mClient.onDestroy();
            mClient = null;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        GeoResultBean bean = new GeoResultBean();
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            //locate success
            bean.msg = BMWXEnvironment.mApplicationContext.getResources().getString(R.string
                    .str_locate_success);
            bean.resCode = 0;
            GeoResultBean.Geo geo = new GeoResultBean().new Geo();
            geo.setLat(aMapLocation.getLatitude());
            geo.setLng(aMapLocation.getLongitude());
            bean.setData(geo);
        } else {
            //locate failed
            bean.msg = BMWXEnvironment.mApplicationContext.getResources().getString(R.string
                    .str_locate_failed);
            bean.resCode = 9;
        }

        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(bean);

    }
}
