package com.httvc.androiddemo.ui.mvvm;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.httvc.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AirQualityActivity extends AppCompatActivity {
    private static final String LogTag = "pm25";
    @BindView(R.id.layout_ticket_out)
    RelativeLayout mPaperLayout;
    @BindView(R.id.layout_main)
    RelativeLayout mBodyLayout;
    @BindView(R.id.rl)
    RelativeLayout mrl;
    @BindView(R.id.btn_paper_share)
    ImageButton mShare;
    private Animation mAlphaAnim = new AlphaAnimation(0.1F, 1.0F);
    private Animator mPaperUpTitle;
    private Animator mPaperUpAll;
    private Animator mBodyTranslate;
    private Animator mFeedbackSended;
    private Animator mBodyReset;
    private boolean isFeedback;
    private boolean canRip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);
        ButterKnife.bind(this);

        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mrl.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressWarnings("deprecation")
                    public void onGlobalLayout() {
                        int i = AirQualityActivity.this.getWindow()
                                .findViewById(R.id.rl).getHeight();
                        Log.d(LogTag, "device height :"
                                + AirQualityActivity.this.getWindow().getDecorView()
                                .getHeight());
                        Log.d(LogTag, "onGlobalLayout:" + i);
                        mrl.setLayoutParams(new FrameLayout.LayoutParams(-1,
                                i * 3));
                        AirQualityActivity.this.mrl.setPadding(0, -i, 0, i);
                        RelativeLayout.LayoutParams bodyLayoutParams = (RelativeLayout.LayoutParams) AirQualityActivity.this.mBodyLayout
                                .getLayoutParams();
                        bodyLayoutParams.height = (i * 2);
                        bodyLayoutParams.setMargins(0, 0, 0, -i);
                        AirQualityActivity.this.mBodyLayout
                                .setLayoutParams(bodyLayoutParams);
                        RelativeLayout.LayoutParams paperLayoutParams = (RelativeLayout.LayoutParams) AirQualityActivity.this.mPaperLayout
                                .getLayoutParams();
                        paperLayoutParams.height = (i * 2);
                        paperLayoutParams
                                .setMargins(
                                        0,
                                        AirQualityActivity.this
                                                .getResources()
                                                .getDimensionPixelSize(
                                                        R.dimen.main_paper_margin_top),
                                        0,
                                        AirQualityActivity.this
                                                .getResources()
                                                .getDimensionPixelSize(
                                                        R.dimen.main_paper_margin_bottom));
                        mPaperLayout.setLayoutParams(paperLayoutParams);
                        mrl.getViewTreeObserver().removeGlobalOnLayoutListener(
                                this);
                    }
                });

        mBodyTranslate = PM25Anim.down(this.mBodyLayout, getResources()
                .getDimension(R.dimen.paper_anim_down));
        mPaperUpTitle = PM25Anim.up(this.mPaperLayout, getResources()
                .getDimension(R.dimen.paper_anim_up_one));
        mPaperUpAll = PM25Anim.up(this.mPaperLayout, getResources()
                .getDimension(R.dimen.paper_anim_up_one), getResources()
                .getDimension(R.dimen.paper_anim_up_two));

        mFeedbackSended = PM25Anim.upAndVanish(this.mPaperLayout,
                getResources().getDimension(R.dimen.paper_anim_up_two));
        mBodyReset = PM25Anim.up(this.mBodyLayout,
                getResources().getDimension(R.dimen.paper_anim_down), 0.0F);
    }

    @OnClick({R.id.btn_next, R.id.btn_print})
    void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                break;
            case R.id.btn_print:
                startPaperAnimation();
                break;
        }
    }

    private void startPaperAnimation() {
        RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams) mPaperLayout
                .getLayoutParams();
        Object[] objects1 = new Object[1];
        objects1[0] = Integer.valueOf(localLayoutParams.height);
         Log.d(LogTag, String.format("paperLP.height %s", objects1));
        localLayoutParams.height = getResources().getDimensionPixelSize(
                R.dimen.paper_height);
        Object[] objects2 = new Object[1];
        objects2[0] = Integer.valueOf(localLayoutParams.height);
        Log.d(LogTag, String.format("paperLP.height %s", objects2));
        this.mPaperLayout.setLayoutParams(localLayoutParams);
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] arrayOfAnimator = new Animator[3];
        arrayOfAnimator[0] = mPaperUpTitle;
        arrayOfAnimator[1] = mBodyTranslate;
        arrayOfAnimator[2] = mPaperUpAll;
        animatorSet.playSequentially(arrayOfAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator paramAnonymousAnimator) {
            }

            public void onAnimationEnd(Animator paramAnonymousAnimator) {
                if (!isFeedback)
                     mShare.setVisibility(View.VISIBLE);
                    canRip = true;
            }

            public void onAnimationRepeat(Animator paramAnonymousAnimator) {
            }

            public void onAnimationStart(Animator paramAnonymousAnimator) {
            }
        });
        animatorSet.start();
    }
}
