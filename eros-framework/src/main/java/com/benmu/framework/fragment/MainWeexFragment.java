package com.benmu.framework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benmu.framework.R;

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

}
