package com.httvc.androiddemo.ui.rxjava;

import android.os.Bundle;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.adapter.StarcastAdapter;
import com.httvc.androiddemo.api.HttpResult;
import com.httvc.androiddemo.api.RestPool;
import com.httvc.androiddemo.pojo.Starcast;
import com.httvc.androiddemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class StarcastActivity extends SupportActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<Starcast> list = new ArrayList<>();
    private StarcastAdapter starcastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starcast);
        ButterKnife.bind(this);
        initRecyView();
        RestPool.getInstance().getService().starcast("756571ec84bad655")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<List<Starcast>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpResult<List<Starcast>> listHttpResult) {
                if (listHttpResult!=null){
                    if ("0".equals(listHttpResult.getStatus())){
                        if (listHttpResult.getResult()!=null&&listHttpResult.getResult().size()!=0){
                            list.addAll(listHttpResult.getResult());
                            starcastAdapter.notifyDataSetChanged();
                        }
                    }else {
                        ToastUtil.show(StarcastActivity.this,listHttpResult.getMsg());
                    }
                }
            }
        });
    }

    private void initRecyView() {
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        starcastAdapter = new StarcastAdapter(this, list);
        recyclerview.setAdapter(starcastAdapter);
    }
}
