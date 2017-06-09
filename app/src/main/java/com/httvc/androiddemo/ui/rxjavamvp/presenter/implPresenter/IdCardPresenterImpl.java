package com.httvc.androiddemo.ui.rxjavamvp.presenter.implPresenter;

import com.httvc.androiddemo.api.HttpResult;
import com.httvc.androiddemo.api.RestPool;
import com.httvc.androiddemo.pojo.IdCard;
import com.httvc.androiddemo.ui.rxjavamvp.presenter.IdCardPresenter;
import com.httvc.androiddemo.ui.rxjavamvp.presenter.implView.IdCardView;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/6/9.
 */

public class IdCardPresenterImpl extends BasePresenterImpl implements IdCardPresenter {
    private IdCardView idCardView;

    public IdCardPresenterImpl(IdCardView idCardView) {
        this.idCardView = idCardView;
    }

    @Override
    public void getIdCard(String idCard) {
        idCardView.showProgressDialog();
        Subscription subscription=RestPool.getInstance().getService().idCard("756571ec84bad655",idCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<IdCard>>() {
                    @Override
                    public void onCompleted() {
                        idCardView.hidProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<IdCard> idCardHttpResult) {
                        idCardView.searchId(idCardHttpResult);
                    }
                });
        addSubscription(subscription);
    }
}
