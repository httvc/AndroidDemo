package com.httvc.androiddemo.beanjson;

import java.io.Serializable;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/4.
 */

public class MainJson implements Serializable {
    private String name;

    public MainJson(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
