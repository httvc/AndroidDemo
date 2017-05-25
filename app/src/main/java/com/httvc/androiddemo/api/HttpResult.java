package com.httvc.androiddemo.api;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/24.
 */

public class HttpResult<T> {
    private String status; //错误码
    private String msg;    //提示
    private T result;        //数据

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
