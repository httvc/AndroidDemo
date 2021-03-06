package com.httvc.androiddemo.api;

import com.httvc.androiddemo.pojo.AirQuality;
import com.httvc.androiddemo.pojo.Channel;
import com.httvc.androiddemo.pojo.IdCard;
import com.httvc.androiddemo.pojo.Starcast;
import com.httvc.androiddemo.pojo.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/5.
 */

public interface ApiService {
    //756571ec84bad655

     //Retrofit2.0请求
    /**
     * 天气预报接口
     * @param appKey
     * @param location
     * @return
     */
    @GET("weather/query")
    Call<HttpResult<Weather>> weather(@Query("appkey") String appKey, @Query("location") String location);

    //Retrofit2.0+RxJava+RxAndroid
    @GET("astro/all")
    Observable<HttpResult<List<Starcast>>> starcast(@Query("appkey") String appKey);


    @GET("/tv/channel")
    Observable<HttpResult<List<Channel>>> chennel(@Query("appkey") String appKey);

    @GET("idcard/query")
    Observable<HttpResult<IdCard>> idCard(@Query("appkey") String appKey,@Query("idcard") String idcard);

    @GET("/aqi/query")
    Observable<HttpResult<AirQuality>> airQuality(@Query("appkey") String appKey,@Query("city") String city);
}
