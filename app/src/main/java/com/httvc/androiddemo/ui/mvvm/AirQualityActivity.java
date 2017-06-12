package com.httvc.androiddemo.ui.mvvm;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.httvc.androiddemo.BR;
import com.httvc.androiddemo.R;
import com.httvc.androiddemo.adapter.MVVMCommonAdapter;
import com.httvc.androiddemo.api.HttpResult;
import com.httvc.androiddemo.api.RestPool;
import com.httvc.androiddemo.databinding.ActivityAirQualityBinding;
import com.httvc.androiddemo.pojo.AirQuality;
import com.httvc.androiddemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


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
    @BindView(R.id.paper_list)
    ListView paperList;
    private Animation mAlphaAnim = new AlphaAnimation(0.1F, 1.0F);
    private Animator mPaperUpTitle;
    private Animator mPaperUpAll;
    private Animator mBodyTranslate;
    private Animator mFeedbackSended;
    private Animator mBodyReset;
    private boolean isFeedback;
    private boolean canRip;
    private String city;
    private ActivityAirQualityBinding airQualityBinding;//此处规则是Activity+AirQuality+Binding
    private AirQuality airQuality = new AirQuality();
    private List<AirQuality.PositionEntity> positionEntity=new ArrayList<>();
    private MVVMCommonAdapter<AirQuality.PositionEntity> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        airQualityBinding = DataBindingUtil.setContentView(this, R.layout.activity_air_quality);
        ButterKnife.bind(this);
        init();
        initData();
        initListener();
        initListView();
    }

    private void init() {
        Intent intent = getIntent();
        if (intent != null) {
            city = intent.getStringExtra("CITY");
            if (city.endsWith("市")) {
                city = city.substring(0, city.length() - 1);
            }
        }
    }

    private void initData() {
        RestPool.getInstance().getService().airQuality("756571ec84bad655", city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<AirQuality>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<AirQuality> airQualityHttpResult) {
                        if ("0".equals(airQualityHttpResult.getStatus())) {
                            airQuality = airQualityHttpResult.getResult();
                            airQualityBinding.setAirQuality(airQuality);
                            positionEntity.addAll(airQuality.getPosition());
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.show(AirQualityActivity.this, airQualityHttpResult.getMsg());
                        }
                    }
                });
    }

    private void initListView() {
        adapter = new MVVMCommonAdapter<>(this,positionEntity, R.layout.paper_item, BR.positionentity);
        paperList.setAdapter(adapter);

    }

    private void initListener() {
        //  final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
                if (airQuality.isFlag()) {
                    airQuality.setFlag(false);
                } else {
                    airQuality.setFlag(true);
                }
                airQualityBinding.setAirQuality(airQuality);
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
