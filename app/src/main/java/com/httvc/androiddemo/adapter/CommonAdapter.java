package com.httvc.androiddemo.adapter;

/**
 * Created by jiafujie on 2017/5/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by long on 2015/8/12.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private SetListener setListener;
    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }
    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        if (setListener != null) {
            if (position == mDatas.size() - 1) {
                setListener.onLastPosition(viewHolder);
            } else {
                setListener.onOtherPosition(viewHolder);
            }
        }
        return viewHolder.getConvertView();
    }
    public void setListener(SetListener setListener) {
        this.setListener = setListener;
    }
    public abstract void convert(ViewHolder helper, T item);
    public void addAll(Collection<? extends T> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }
    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }
    public interface SetListener {
        void onLastPosition(ViewHolder viewHolder);
        void onOtherPosition(ViewHolder viewHolder);
    }
}
