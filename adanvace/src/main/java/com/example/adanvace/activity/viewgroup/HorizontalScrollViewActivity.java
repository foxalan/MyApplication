package com.example.adanvace.activity.viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.view.MyToast;

/**
 * Function : 自定义横向滑动的ScrollView
 * Author : Alan
 * Modify Date : 24/8/17
 * Issue : TODO
 * Whether solve :
 */

public class HorizontalScrollViewActivity extends BaseActivity {

    private int[] imageIds = {R.drawable.ipad, R.drawable.iphone, R.drawable.meizi,
            R.drawable.danfan, R.drawable.f015, R.drawable.f040};

    private LinearLayout ll_gallery;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_horizontalscrollview;
    }

    @Override
    public void initView() {
        ll_gallery = (LinearLayout) findViewById(R.id.ll_gallery);
    }

    @Override
    public void initData() {
        inflater = LayoutInflater.from(this);
    }

    @Override
    public void initEvent() {
        for (int i = 0; i < imageIds.length; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.width = 300;
            layoutParams.height = 300;

            View view = inflater.inflate(R.layout.list_gallery,null);
            ImageView iv_gallery = (ImageView) view.findViewById(R.id.iv_list_gallery);
            iv_gallery.setImageResource(imageIds[i]);

            TextView tv_gallery = (TextView) view.findViewById(R.id.tv_list_gallery);
            tv_gallery.setText("mv"+i);

            iv_gallery.setLayoutParams(layoutParams);


//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(imageIds[i]);
//            imageView.setLayoutParams(layoutParams);
//            final int position = i;
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MyToast.showMessage("点击了第" + position + "个");
//                }
//            });
            ll_gallery.addView(view);
        }
    }
}
