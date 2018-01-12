package com.example.adanvace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adanvace.R;

/**
 * Function :
 * Author : Alan
 * Modify Date : 11/8/17
 * Issue : TODO
 * Whether solve :
 */

public class TestFragmentThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three,null);
    }
}
