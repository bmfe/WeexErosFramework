package com.benmu.framework.extend.plug_in.amap.component.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.benmu.framework.R;
import com.benmu.framework.extend.plug_in.amap.component.WXMapInfoWindowComponent;
import com.benmu.framework.extend.plug_in.amap.component.WXMapViewComponent;


/**
 * Created by Carry on 2017/5/16.
 */

public class BMCustomerInfoWindowAdapter implements AMap.InfoWindowAdapter {
    private WXMapViewComponent mMapView;

    public BMCustomerInfoWindowAdapter(WXMapViewComponent mapViewComponent) {
        this.mMapView = mapViewComponent;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        return render(marker);
    }


    private View render(Marker marker) {
        WXMapInfoWindowComponent wxMapInfoWindowComponent = mMapView
                .getCachedInfoWindow().get(marker.getId());
        if (wxMapInfoWindowComponent != null) {
            mMapView.getHostView().removeView(wxMapInfoWindowComponent.getHostView
                    ());
            return wxMapInfoWindowComponent.getHostView();
        }
        View view = LayoutInflater.from(mMapView.getContext()).inflate(R.layout
                .layout_amap_window, null);
        WindowInfo info = (WindowInfo) marker.getObject();
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_subTitle = (TextView) view.findViewById(R.id.tv_subTitle);
        if (!TextUtils.isEmpty(info.getTitle())) {
            tv_title.setText(info.getTitle());
            tv_title.setVisibility(View.VISIBLE);
        } else {
            tv_title.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(info.getSubTitle())) {
            tv_subTitle.setText(info.getSubTitle());
            tv_subTitle.setVisibility(View.VISIBLE);
        } else {
            tv_subTitle.setVisibility(View.GONE);
        }

        return view;
    }


    @Override
    public View getInfoContents(Marker marker) {
        return render(marker);
    }


    public static class WindowInfo {
        private String title;
        private String subTitle;

        public WindowInfo(String title, String subTitle) {
            this.title = title;
            this.subTitle = subTitle;
        }

        public WindowInfo() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }
    }
}
