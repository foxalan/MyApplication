package com.example.adanvace.activity.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.adanvace.R;
import com.example.adanvace.util.L;
import com.example.adanvace.view.MyToast;

import java.util.Random;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO
 * Whether solve :
 */

public class LotteryActivity extends BaseActivity {

    private ImageView iv_start;

    private int currentPosition = 0;

    private String[] titles = {"单反相机", "再来一次", "恭喜发财", "IPAD", "IPHONE", "妹子一只"};

    private int next;

    private boolean isStart = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyToast.init(this, LayoutInflater.from(this));
    }

    @Override
    public int getContentView() {
        return R.layout.activity_lottery;
    }

    @Override
    public void initView() {
        iv_start = (ImageView) findViewById(R.id.iv_start);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isStart) {
                    isStart = true;
                    Random random = new Random();
                    next = (random.nextInt(10) + 100) * 60 + currentPosition;
                    ObjectAnimator anim = ObjectAnimator.ofFloat(iv_start, "rotation", currentPosition, next);
                    anim.setDuration(3000);
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            currentPosition = next;
                            int count = currentPosition / 360;
                            int position = currentPosition - 360 * count;
                            if (position > 30 && position < 90) {
                                MyToast.showMessage(titles[5]);

                            } else if (position > 90 && position < 150) {
                                MyToast.showMessage(titles[0]);
                            } else if (position > 150 && position < 210) {
                                MyToast.showMessage(titles[1]);
                            } else if (position > 210 && position < 270) {
                                MyToast.showMessage(titles[2]);
                            } else if (position > 270 && position < 330) {
                                MyToast.showMessage(titles[3]);
                            } else {
                                MyToast.showMessage(titles[4]);
                            }
                            isStart = false;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    anim.start();


                }


            }
        });
    }
}
