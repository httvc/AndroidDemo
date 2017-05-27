package com.httvc.androiddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.pojo.Starcast;
import com.httvc.androiddemo.viewholder.StarcastViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/27.
 */

public class StarcastAdapter extends RecyclerView.Adapter<StarcastViewHolder> {
    private Context mContext;
    private ArrayList<Starcast> list;
    public StarcastAdapter(Context context,ArrayList<Starcast> list){
        this.mContext=context;
        this.list=list;
    }

    @Override
    public StarcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StarcastViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_starcast,parent,false));
    }

    @Override
    public void onBindViewHolder(StarcastViewHolder holder, int position) {
        if (list!=null&&list.size()!=0){
            holder.name.setText(list.get(position).getAstroname());
            holder.data.setText(list.get(position).getDate());
            Picasso.with(mContext).load(list.get(position).getPic()).into(holder.iv);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
