package com.httvc.androiddemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jiafujie on 2017/5/18.
 */

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private int progress = 0;
    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
        //ListView 适配autoLayout
        //AutoUtils.autoSize(mConvertView);
    }
    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();
        holder.setmPosition(position);
        return holder;
    }
    public void setmPosition(int position){
        this.mPosition=position;
    }
    public View getConvertView() {
        return mConvertView;
    }
    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        if (view != null)
            view.setText(text);
        return this;
    }
    /**
     * @param viewId
     * @param
     * @return
     */
    public ViewHolder setTextColor(Context context, int viewId, int color) {
        TextView view = getView(viewId);
        if (view != null)
            view.setTextColor(context.getResources().getColor(color));
        return this;
    }
    /**
     * 为TextView设置字符串，颜色
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, CharSequence text, int resId) {
        TextView view = getView(viewId);
        view.setTextColor(resId);
        view.setText(text);
        return this;
    }
    /**
     * 设置relativelayout 的背景
     */
    public ViewHolder setRlBackGround(int viewId, int drawableId) {
        RelativeLayout layout = getView(viewId);
        layout.setBackgroundResource(drawableId);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param
     * @param
     * @return
     */
//    public ViewHolder setImageByUrl(int viewId, String url) {
//        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//                (ImageView) getView(viewId));
//        return this;
//    }
    public int getPosition() {
        return mPosition;
    }
    public void setVisible(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);//0是可见的  4是不可见的  8是不可见的，而且不占用布局空间
    }
    public void setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
    }
    public void setViewSize(int viewId, float size){
        TextView view = getView(viewId);
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
//        view.setTextSize(size);
    }
}