package com.example.signdemo;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private GridView gridView;
    private PagerAdapter pagerAdapter;
    private LayoutInflater layoutInflater;
    private GridViewAdapter gridViewAdapter;
    private List<String> stringList;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutInflater = LayoutInflater.from(this);
        gridView = (GridView) layoutInflater.inflate(R.layout.grid_view, null);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        initData();

        gridViewAdapter = new GridViewAdapter(this, stringList);
        gridView.setAdapter(gridViewAdapter);


        pagerAdapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(gridView);
                return gridView;
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {




        stringList = new ArrayList<>();

        for (int i = 1; i <= 42; i++) {
            stringList.add(i + " ");
        }

    }
}
