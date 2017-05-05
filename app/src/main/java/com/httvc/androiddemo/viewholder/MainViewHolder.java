package com.httvc.androiddemo.viewholder;

import android.view.View;
import android.widget.TextView;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.base.BaseViewHolder;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/4.
 */

public class MainViewHolder  extends BaseViewHolder{
    public TextView tv;
    public MainViewHolder(View itemView) {
        super(itemView);
        tv= (TextView) itemView.findViewById(R.id.main_tv);
    }
}
