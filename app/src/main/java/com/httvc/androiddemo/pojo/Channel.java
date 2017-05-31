package com.httvc.androiddemo.pojo;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/31.
 */

public class Channel {

    /**
     * tvid : 1
     * name : 中央台
     * parentid : 0
     * istv : 0
     */

    private String tvid;
    private String name;
    private String parentid;
    private String istv;

    public String getTvid() {
        return tvid;
    }

    public void setTvid(String tvid) {
        this.tvid = tvid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getIstv() {
        return istv;
    }

    public void setIstv(String istv) {
        this.istv = istv;
    }
}
