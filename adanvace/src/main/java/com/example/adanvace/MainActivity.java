package com.example.adanvace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.adanvace.activity.animation.AnimationActivity;
import com.example.adanvace.activity.animation.AnimatorActivity;
import com.example.adanvace.activity.view.ContactActivity;
import com.example.adanvace.activity.view.CustomActivity;
import com.example.adanvace.activity.view.LotteryActivity;
import com.example.adanvace.activity.view.RadiusActivity;
import com.example.adanvace.activity.view.RefreshListViewActivity;
import com.example.adanvace.activity.view.ScrollViewActivity;
import com.example.adanvace.activity.view.SoundActivity;
import com.example.adanvace.activity.view.VerticalActivity;
import com.example.adanvace.activity.view.ViewPagerActivity;
import com.example.adanvace.activity.view.VolleyActivity;
import com.example.adanvace.activity.viewgroup.AdapterActivity;
import com.example.adanvace.activity.viewgroup.FlowViewGroupActivity;
import com.example.adanvace.activity.viewgroup.HorizontalScrollViewActivity;
import com.example.adanvace.activity.viewgroup.JigsawActivity;
import com.example.adanvace.activity.viewgroup.RecyclerActivity;
import com.example.adanvace.activity.viewgroup.ViewGroupActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_vertical:
                startActivity(new Intent(MainActivity.this, VerticalActivity.class));
                break;
            case R.id.bt_view_image:
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
                break;
            case R.id.bt_view_radius:
                startActivity(new Intent(MainActivity.this, RadiusActivity.class));
                break;
            case R.id.bt_view_sound:
                startActivity(new Intent(MainActivity.this, SoundActivity.class));
                break;
            case R.id.bt_volley:
                startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                break;
            case R.id.bt_database:
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            case R.id.bt_viewpager:
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                break;
            case R.id.bt_scrollView:
                startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                break;
            case R.id.bt_refresh_listView:
                startActivity(new Intent(MainActivity.this, RefreshListViewActivity.class));
                break;
            case R.id.bt_view_group:
                startActivity(new Intent(MainActivity.this, ViewGroupActivity.class));
                break;
            case R.id.bt_view_group_flow:
                startActivity(new Intent(MainActivity.this, FlowViewGroupActivity.class));
                break;
            case R.id.bt_custom_adapter_listView:
                startActivity(new Intent(MainActivity.this, AdapterActivity.class));
                break;
            case R.id.bt_custom_adapter_recyclerView:
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                break;
            case R.id.bt_lottery:
                startActivity(new Intent(MainActivity.this, LotteryActivity.class));
                break;
            case R.id.bt_animation:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case R.id.bt_animator:
                startActivity(new Intent(MainActivity.this, AnimatorActivity.class));
                break;
            case R.id.bt_horizontalScrollView:
                startActivity(new Intent(MainActivity.this, HorizontalScrollViewActivity.class));
                break;
            case R.id.bt_jigsaw:
                startActivity(new Intent(MainActivity.this, JigsawActivity.class));
                break;

        }
    }


}
