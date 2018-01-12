package com.example.mybulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.mybulter.R;
import com.example.mybulter.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseActivity {

    private int[] image_ids = {R.drawable.guide_concise, R.drawable.guide_smart, R.drawable.guide_strong};
    private List<ImageView> viewList;

    private ViewPager vp_guide;
    private ImageButton ib_black;
    private ImageButton ib_point_1;
    private ImageButton ib_point_2;
    private ImageButton ib_point_3;

    private List<ImageButton> buttonList = new ArrayList<>();

    private ButtonClick buttonClick;

    private SharedPreferencesUtil util;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        ib_black = (ImageButton) findViewById(R.id.ib_black);
        ib_point_1 = (ImageButton) findViewById(R.id.ib_point_1);
        ib_point_2 = (ImageButton) findViewById(R.id.ib_point_2);
        ib_point_3 = (ImageButton) findViewById(R.id.ib_point_3);

        buttonList.add(ib_point_1);
        buttonList.add(ib_point_2);
        buttonList.add(ib_point_3);

    }

    @Override
    public void initData() {
        util = new SharedPreferencesUtil(this);
        util.setIsFirst(false);

        viewList = new ArrayList<>();

        for (int i = 0; i < image_ids.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(image_ids[i]);
            viewList.add(image);
        }

    }

    @Override
    public void initEvent() {


        ib_point_1.setBackgroundResource(R.drawable.point_on);

        vp_guide.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {


                ((ViewPager) container).addView(viewList.get(position));

                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(viewList.get(position));
            }
        });

        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setButtonGreen(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp_guide.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
            }
        });


        buttonClick = new ButtonClick();
        ib_black.setOnClickListener(buttonClick);
        ib_point_1.setOnClickListener(buttonClick);
        ib_point_2.setOnClickListener(buttonClick);
        ib_point_3.setOnClickListener(buttonClick);

    }


    /**
     * 图片的初始化,通过new ImageView(this)
     * View 使用LayoutInflater
     */


    private void setButtonGreen(int position) {

        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setBackgroundResource(R.drawable.point_off);
        }
        buttonList.get(position).setBackgroundResource(R.drawable.point_on);
    }

    /**
     * 此处待优化,
     */

    class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ib_black:
                    startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                    finish();
                    break;
                case R.id.ib_point_1:
                    setButtonGreen(0);
                    vp_guide.setCurrentItem(0);
                    break;
                case R.id.ib_point_2:
                    setButtonGreen(1);
                    vp_guide.setCurrentItem(1);
                    break;
                case R.id.ib_point_3:
                    setButtonGreen(2);
                    vp_guide.setCurrentItem(2);
                    break;
                default:
                    break;
            }
        }
    }


}
