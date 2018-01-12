package com.example.adanvace.activity.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.util.L;

/**
 * Function :简单动画
 * Author : Alan
 * Modify Date : 22/8/17
 * KNOW: 1.ALPHA动画(渐变动画)　要设置开始与结尾,是0-1之间的数值,0是完全看不见
 * 2.Rotate(旋转动画)　要标明中心点,与旋转大小,360为一圈
 * 3.Scale(缩放动画)　要标明中心点,是0-1之间的数值,0是没有了
 * 4.Translate(移动动画)
 */

public class AnimationActivity extends BaseActivity {

    private Animation animation_alpha;
    private Animation animation_rotate;
    private Animation animation_scale;
    private Animation animation_translate;

    private ImageView iv_anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_animation;
    }

    @Override
    public void initView() {
        iv_anim = (ImageView) findViewById(R.id.iv_anim);
    }

    @Override
    public void initData() {

        animation_alpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        animation_alpha.setDuration(2000);
        /**
         * 保留动画后的状态
         */
        animation_alpha.setFillAfter(true);

        animation_rotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        animation_rotate.setDuration(3000);
        animation_rotate.setFillAfter(true);

        animation_scale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animation_scale.setDuration(3000);
        animation_scale.setFillAfter(true);

        animation_translate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        animation_translate.setDuration(3000);
        animation_translate.setFillAfter(true);

    }

    @Override
    public void initEvent() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_animation_alpha:
                iv_anim.startAnimation(animation_alpha);
                break;
            case R.id.bt_animation_rotation:
                iv_anim.startAnimation(animation_rotate);
                break;
            case R.id.bt_animation_scale:
                iv_anim.startAnimation(animation_scale);
                break;
            case R.id.bt_animation_translate:
                iv_anim.startAnimation(animation_translate);
                break;
            case R.id.bt_animation_scale_alpha:

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_anim, "scaleX", 1.0f, 0.0f);
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float x = (float) animation.getAnimatedValue();
                        L.d("x:"+x);
                        iv_anim.setScaleY(x);
                        iv_anim.setAlpha(x);
                    }
                });
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                break;
        }
    }
}
