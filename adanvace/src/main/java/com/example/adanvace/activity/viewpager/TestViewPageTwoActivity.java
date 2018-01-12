package com.example.adanvace.activity.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adanvace.R;
import com.example.adanvace.fragment.TestFragmentFour;
import com.example.adanvace.fragment.TestFragmentOne;
import com.example.adanvace.fragment.TestFragmentThree;
import com.example.adanvace.fragment.TestFragmentTwo;

/**
 * Function : 使用getFragmentManager 来管理Fragment
 * Author : Alan
 * Modify Date : 11/8/17
 * Issue : TODO 掌握的很不好
 * Whether solve :
 */

public class TestViewPageTwoActivity extends FragmentActivity {

    private TestFragmentOne fragment_01;
    private TestFragmentTwo fragment_02;
    private TestFragmentThree fragment_03;
    private TestFragmentFour fragment_04;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Button bt_test_02_01;
    private Button bt_test_02_02;
    private Button bt_test_02_03;
    private Button bt_test_02_04;

    private ButtonClick buttonClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initData();
        initEvent();
    }


    public int getContentView() {
        return R.layout.activity_viewpager_test_02;
    }


    public void initView() {

        fragment_01 = new TestFragmentOne();
        fragment_02 = new TestFragmentTwo();
        fragment_03 = new TestFragmentThree();
        fragment_04 = new TestFragmentFour();

        bt_test_02_01 = (Button) findViewById(R.id.bt_test_02_01);
        bt_test_02_02 = (Button) findViewById(R.id.bt_test_02_02);
        bt_test_02_03 = (Button) findViewById(R.id.bt_test_02_03);
        bt_test_02_04 = (Button) findViewById(R.id.bt_test_02_04);
    }


    public void initData() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.gv_fl, fragment_03);
        fragmentTransaction.commit();
        buttonClick = new ButtonClick();
    }


    public void initEvent() {

        bt_test_02_01.setOnClickListener(buttonClick);
        bt_test_02_02.setOnClickListener(buttonClick);
        bt_test_02_03.setOnClickListener(buttonClick);
        bt_test_02_04.setOnClickListener(buttonClick);

    }

    private class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_test_02_01:
                    showSelectFragment(1);

                    break;
                case R.id.bt_test_02_02:
                    showSelectFragment(2);

                    break;
                case R.id.bt_test_02_03:
                    showSelectFragment(3);

                    break;
                case R.id.bt_test_02_04:
                    showSelectFragment(4);
                    break;
            }

        }
    }

    private void showSelectFragment(int i) {

        /**
         * 先隐藏所有的Fragment
         */
        //hideAllFragment();

        switch (i) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.gv_fl, fragment_01).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.gv_fl, fragment_02).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.gv_fl, fragment_03).commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.gv_fl, fragment_04).commit();

                break;
        }
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commitAllowingStateLoss();
    }

    private void hideAllFragment() {

        if (fragment_01 != null) {
            fragmentTransaction.hide(fragment_01);
            Log.d("ALAN", "HIDE1");
        }

        if (fragment_02 != null) {
            fragmentTransaction.hide(fragment_02);
            Log.d("ALAN", "HIDE2");
        }

        if (fragment_03 != null) {
            fragmentTransaction.hide(fragment_03);
            Log.d("ALAN", "HIDE3");
        }

        if (fragment_04 != null) {
            fragmentTransaction.hide(fragment_04);
            Log.d("ALAN", "HIDE4");
        }

    }
}
