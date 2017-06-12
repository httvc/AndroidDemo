package com.httvc.androiddemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/6/12.
 */

public class MVVMCommonAdapter <T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    private int layoutId;//单布局
    private int variableId;
    public MVVMCommonAdapter(Context context, List<T> list, int layoutId, int variableId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setVariable(variableId, list.get(position));
        return binding.getRoot();
    }
}
