package com.laughface.animatorset;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/11/10.
 */
public class Animator1Activity extends FragmentActivity {

    private TextView iv_1;
    private TextView iv_2;
    private TextView iv_3;
    private TextView iv_4;

    private ValueAnimator mIv_1Anima;
    private ValueAnimator mIv_2Anima;
    private ValueAnimator mIv_3Anima;
    private ValueAnimator mIv_4Anima;

    private ValueAnimator mNull1Animator;
    private ValueAnimator mNull2Animator;
    private ValueAnimator mNull3Animator;
    private AnimatorSet mAnimatorSet;

    private boolean isAnimationRun;

    private Button btn_start_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation1);
        iv_1 = (TextView) findViewById(R.id.iv_1);
        iv_2 = (TextView) findViewById(R.id.iv_2);
        iv_3 = (TextView) findViewById(R.id.iv_3);
        iv_4 = (TextView) findViewById(R.id.iv_4);

        btn_start_anim = (Button) findViewById(R.id.btn_start_anim);
        btn_start_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimator();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        startAnimationForTypeView();
    }

    private void startAnimationForTypeView() {
        iv_1.setAlpha(0);
        iv_2.setAlpha(0);
        iv_3.setAlpha(0);
        iv_4.setAlpha(0);
        if (mIv_1Anima == null) {
            mIv_1Anima = ValueAnimator.ofFloat(1.3f, 0.8f, 1.0f);
            mIv_1Anima.setInterpolator(new LinearInterpolator());
            mIv_1Anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    iv_1.setScaleX((float) animation.getAnimatedValue());
                    iv_1.setScaleY((float) animation.getAnimatedValue());
                    iv_1.setAlpha(animation.getAnimatedFraction());
                }
            });
            mIv_1Anima.setDuration(2000);

            mIv_2Anima = ValueAnimator.ofFloat(1.3f, 0.8f, 1.0f);
            mIv_2Anima.setInterpolator(new LinearInterpolator());
            mIv_2Anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    iv_2.setScaleX((float) animation.getAnimatedValue());
                    iv_2.setScaleY((float) animation.getAnimatedValue());
                    iv_2.setAlpha(animation.getAnimatedFraction());
                }
            });
            mIv_2Anima.setDuration(2000);

            mIv_3Anima = ValueAnimator.ofFloat(1.3f, 0.8f, 1.0f);
            mIv_3Anima.setInterpolator(new LinearInterpolator());
            mIv_3Anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    iv_3.setScaleX((float) animation.getAnimatedValue());
                    iv_3.setScaleY((float) animation.getAnimatedValue());
                    iv_3.setAlpha(animation.getAnimatedFraction());
                }
            });
            mIv_3Anima.setDuration(2000);

            mIv_4Anima =
                    ValueAnimator.ofFloat(1.3f, 0.8f, 1.0f);
            mIv_4Anima.setInterpolator(new LinearInterpolator());
            mIv_4Anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    iv_4.setScaleX((float) animation.getAnimatedValue());
                    iv_4.setScaleY((float) animation.getAnimatedValue());
                    iv_4.setAlpha(animation.getAnimatedFraction());
                }
            });
            mIv_4Anima.setDuration(2000);

            mNull1Animator = ValueAnimator.ofFloat(1.0F, 0F);
            mNull1Animator.setDuration(800);
            mNull2Animator = ValueAnimator.ofFloat(1.0F, 0F);
            mNull2Animator.setDuration(800);
            mNull3Animator = ValueAnimator.ofFloat(1.0F, 0F);
            mNull3Animator.setDuration(800);

            mAnimatorSet = new AnimatorSet();
            //两点一线,固定动画执行位置
            mAnimatorSet.play(mIv_1Anima).with(mNull1Animator);
            mAnimatorSet.play(mNull1Animator).before(mIv_2Anima);
            mAnimatorSet.play(mNull1Animator).before(mNull2Animator);
            mAnimatorSet.play(mNull2Animator).with(mIv_2Anima);
            mAnimatorSet.play(mNull2Animator).before(mIv_3Anima);
            mAnimatorSet.play(mNull2Animator).before(mNull3Animator);
            mAnimatorSet.play(mNull3Animator).with(mIv_3Anima);
            mAnimatorSet.play(mNull3Animator).before(mIv_4Anima);
        }
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimationRun = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimationRun = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimationRun = false;
            }
        });
        startAnimator();
    }

    private void startAnimator() {
        if (isAnimationRun) {
            mAnimatorSet.cancel();
        }
        mAnimatorSet.setStartDelay(500);
        mAnimatorSet.start();
    }
}
