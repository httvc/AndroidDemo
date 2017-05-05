package com.httvc.androiddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.beanjson.MainJson;
import com.httvc.androiddemo.viewholder.MainViewHolder;

import java.util.ArrayList;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/4.
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private Context mContext;
    private ArrayList<MainJson> list;

    public MainAdapter(Context context,ArrayList<MainJson> list){
        this.mContext=context;
        this.list=list;
    }
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_main_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        if (mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(view,pos);
                }
            });
        }
        MainJson mainJson=list.get(position);
        holder.tv.setText(mainJson.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);

        void onItemLongClick(View view,int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener=mOnItemClickLitener;
    }
}
