package com.example.adanvace.activity.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.adanvace.R;
import com.example.adanvace.viewgroup.VerticalLinearLayout;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter &
 */

public class VerticalActivity extends Activity{

    private VerticalLinearLayout verticalLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verterical);

        verticalLinearLayout = (VerticalLinearLayout) findViewById(R.id.vl_layout);

        verticalLinearLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener() {
            @Override
            public void onPageChange(int currentPage) {
                Toast.makeText(VerticalActivity.this, "第"+(currentPage+1)+"页", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
