package com.httvc.androiddemo.presenter.implView;

import com.httvc.androiddemo.api.HttpResult;
import com.httvc.androiddemo.pojo.IdCard;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/6/9.
 */

public interface IdCardView extends BaseView {
    void searchId(HttpResult<IdCard> idCardHttpResult);
}

