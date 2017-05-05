package com.httvc.androiddemo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/5.
 */

public class RestPool {
    private RestPool() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // log拦截器  打印所有的log
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                //.addInterceptor(new TokenInterceptor())
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(1, TimeUnit.SECONDS).writeTimeout(1, TimeUnit.SECONDS) //设置超时
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
    }

    private static class SingletonHolder {
        //单例对象实例
        private static final RestPool INSTANCE = new RestPool();
    }

    public static RestPool getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
