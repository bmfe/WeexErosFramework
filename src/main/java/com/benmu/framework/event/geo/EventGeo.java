package com.benmu.framework.event.geo;

import android.content.Context;

import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.GeoManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.GeoResultBean;
import com.squareup.otto.Subscribe;
import com.taobao.weex.bridge.JSCallback;

/**
 * Created by Carry on 2017/11/17.
 */

public class EventGeo {
    private JSCallback mCallback;
    private GeoManager mGeoManager;

    public void getLocation(Context context, JSCallback jscallback) {
        this.mCallback = jscallback;
        mGeoManager = ManagerFactory.getManagerService(GeoManager.class);
        mGeoManager.startLocation(context);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().register(this);
    }

    @Subscribe
    public void onLocation(GeoResultBean bean) {
        if (mCallback != null) {
            mCallback.invoke(bean);
        }

        mGeoManager.onPause();
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().unregister(this);
    }

}
