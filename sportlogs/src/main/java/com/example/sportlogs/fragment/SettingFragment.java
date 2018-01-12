package com.example.sportlogs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sportlogs.R;

/**
 * Function :　
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class SettingFragment extends BaseFragment {

    private static SettingFragment settingFragment;

    public static SettingFragment getInstance(){
        if (settingFragment == null){
            settingFragment = new SettingFragment();
        }

        return settingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);

        return view;
    }
}
