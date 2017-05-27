package com.httvc.androiddemo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.httvc.androiddemo.R;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/27.
 */

public class StarcastViewHolder extends RecyclerView.ViewHolder {
    public TextView name,data;
    public ImageView iv;
    public StarcastViewHolder(View itemView) {
        super(itemView);
        name= (TextView) itemView.findViewById(R.id.item_startcast_name);
        data= (TextView) itemView.findViewById(R.id.item_startcast_data);
        iv= (ImageView) itemView.findViewById(R.id.item_startcast_iv);
    }
}
