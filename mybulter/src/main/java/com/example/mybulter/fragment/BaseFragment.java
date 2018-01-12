package com.example.mybulter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 22/7/17$
 * Input Parameter &
 */

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getContentView(),null);

        initViews();

        initData();

        initEvent();

        return view;
    }

    public abstract int getContentView();

    public abstract int initViews();

    public abstract int initData();

    public abstract int initEvent();
}
