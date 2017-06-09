package com.httvc.androiddemo.presenter.implPresenter;

import com.httvc.androiddemo.presenter.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/6/2.
 */

public class BasePresenterImpl implements BasePresenter {

    //解决方案就是在生命周期的某个时刻取消订阅。一个很常见的模式就是使用
    // CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()
    // 或者onDestroyView()里取消所有的订阅。
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s){
        if (this.mCompositeSubscription==null){
            this.mCompositeSubscription=new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }
    @Override
    public void unsubscrible() {
        if (this.mCompositeSubscription!=null){
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
