package com.benmu.framework.extend.plug_in.amap.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.benmu.framework.R;
import com.benmu.framework.extend.plug_in.amap.component.adapter.BMCustomerInfoWindowAdapter;
import com.benmu.framework.extend.plug_in.amap.util.Constant;
import com.benmu.framework.utils.BMHookGlide;
import com.benmu.framework.utils.ImageUtil;
import com.benmu.framework.utils.L;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by budao on 2017/2/9.
 */

public class WXMapMarkerComponent extends WXComponent<View> {
    private Marker mMarker;
    private MapView mMapView;
    private BMCustomerInfoWindowAdapter.WindowInfo mWindowInfo;
    private int mPinHeight;
    private int mPinWidth;
    private double mScale;

    public WXMapMarkerComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent,
                                String instanceId, boolean isLazy) {
        super(instance, dom, parent, instanceId, isLazy);
    }

    public WXMapMarkerComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent,
                                boolean isLazy) {
        super(instance, dom, parent, isLazy);
    }

    public WXMapMarkerComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);

    }


    @Override
    protected View initComponentHostView(@NonNull Context context) {
        if (getParent() != null && getParent() instanceof WXMapViewComponent) {
            mMapView = ((WXMapViewComponent) getParent()).getHostView();
            String title = (String) getDomObject().getAttrs().get(Constant.Name.TITLE);
            String icon = (String) getDomObject().getAttrs().get(Constant.Name.ICON);
            String position = getDomObject().getAttrs().get(Constant.Name.POSITION).toString();
            initMarker(title, position, icon);
        }
        mScale = WXViewUtils.defaultPixelScaleFactor(getContext());
        // FixMe： 只是为了绕过updateProperties中的逻辑检查
        return new View(context);
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case Constants.Name.POSITION:
                String position = WXUtils.getString(param, null);
                if (position != null)
                    setPosition(position);
                return true;
        }
        return super.setProperty(key, param);
    }

    @WXComponentProp(name = Constant.Name.TITLE)
    public void setTitle(String title) {
        setMarkerTitle(title);
    }


    @WXComponentProp(name = Constant.Name.SUBTITLE)
    public void setSubTitle(String subTitle) {
        setMarkerSubTitle(subTitle);
    }


    @WXComponentProp(name = Constant.Name.PINHEIGHT)
    public void setPinHeight(String pinHeight) {
        if (!TextUtils.isEmpty(pinHeight)) {
            mPinHeight = WXUtils.getInt(pinHeight);
        }

    }

    @WXComponentProp(name = Constant.Name.PINWIDTH)
    public void setPinWidth(String pinWidth) {
        if (!TextUtils.isEmpty(pinWidth)) {
            mPinWidth = WXUtils.getInt(pinWidth);
        }
    }

    private void setMarkerSubTitle(String subTitle) {
        if (mWindowInfo == null) {
            mWindowInfo = new BMCustomerInfoWindowAdapter.WindowInfo();
        }
        mWindowInfo.setSubTitle(subTitle);
        mMarker.setObject(mWindowInfo);
    }

    @WXComponentProp(name = Constant.Name.ICON)
    public void setIcon(String icon) {
        setMarkerIcon(icon);
    }

    @WXComponentProp(name = Constant.Name.HIDE_CALL_OUT)
    public void setHideCallOut(Boolean hide) {
        setMarkerHideCallOut(hide);
    }

    @WXComponentProp(name = Constant.Name.POSITION)
    public void setPosition(String position) {
        setMarkerPosition(position);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (mMarker != null) {
            mMarker.remove();
        }
    }

    public Marker getMarker() {
        return mMarker;
    }

    public void onClick() {
        getInstance().fireEvent(getRef(), Constants.Event.CLICK);
    }

    private void initMarker(String title, String position, String icon) {
        final MarkerOptions markerOptions = new MarkerOptions();
        //设置Marker可拖动
        markerOptions.draggable(true);
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOptions.setFlat(true);
        mMarker = mMapView.getMap().addMarker(markerOptions);
        setMarkerIcon(icon);
    }


    private Bitmap getDesireBitmap(Bitmap src) {
        if (src != null) {
            if (mPinHeight > 0 && mPinWidth > 0) {
                return ImageUtil.zoomImage(src, (float) (mPinWidth / mScale), (float)
                        (mPinHeight / mScale));
            }
        }
        return src;
    }


    private void setMarkerIcon(final String icon) {
        BMHookGlide.load(getContext(),icon).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                    GlideDrawable> glideAnimation) {
                if (resource instanceof GifDrawable) {
                    //设置maker为gif
                    handleGif((GifDrawable) resource);
                } else {
                    //maker为普通图片
                    handleNormal(resource);
                }
            }
        });
    }


    private void handleNormal(final GlideDrawable resource) {
        if (getHostView() == null) return;
        getHostView().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (resource instanceof GlideBitmapDrawable) {
                    GlideBitmapDrawable bitmapDrawable = (GlideBitmapDrawable) resource;
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap
                            (getDesireBitmap(bitmapDrawable
                                    .getBitmap()));
                    mMarker.setIcon(bitmapDescriptor);
                } else {
                    L.e("amap", "错误的图片资源类型");
                }
            }
        }, 100);
    }


    private void handleGif(GifDrawable resource) {
        com.bumptech.glide.gifdecoder.GifDecoder decoder = resource.getDecoder();
        ArrayList<BitmapDescriptor> bitmapDescriptors = new ArrayList<BitmapDescriptor>();
        while (decoder.getCurrentFrameIndex() < decoder.getFrameCount() - 1) {
            decoder.advance();
            Bitmap frame = decoder.getNextFrame();
            bitmapDescriptors.add(BitmapDescriptorFactory.fromBitmap(getDesireBitmap(frame)));
        }
        Log.e("size", "size:" + bitmapDescriptors.size());
        MarkerOptions options = mMarker.getOptions();
        options.icons(bitmapDescriptors);
        options.period(2);
        mMarker.setMarkerOptions(options);
    }


    private void setMarkerHideCallOut(Boolean hide) {
        if (mMarker != null) {
            if (hide) {
                mMarker.setClickable(!hide);
            }
        }
    }

    private void setMarkerPosition(String position) {
        try {
            JSONArray jsonArray = new JSONArray(position);
            LatLng latLng = new LatLng(jsonArray.optDouble(1), jsonArray.optDouble(0));
            MarkerOptions markerOptions = mMarker.getOptions();
            markerOptions.position(latLng);
            mMarker.setMarkerOptions(markerOptions);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setMarkerTitle(String title) {
        MarkerOptions markerOptions = mMarker.getOptions();
        markerOptions.title("");
        mMarker.setMarkerOptions(markerOptions);
        if (mWindowInfo == null) {
            mWindowInfo = new BMCustomerInfoWindowAdapter.WindowInfo();
        }
        mWindowInfo.setTitle(title);
        mMarker.setObject(mWindowInfo);
    }

}
