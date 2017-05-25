package com.httvc.androiddemo.ui.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.httvc.androiddemo.R;
import com.httvc.androiddemo.api.HttpCallBack;
import com.httvc.androiddemo.api.RestPool;
import com.httvc.androiddemo.pojo.Weather;
import com.httvc.androiddemo.utils.CommonUtils;
import com.httvc.androiddemo.utils.ToastUtil;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherActivity extends AppCompatActivity {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    @BindView(R.id.date_y)
    TextView dateY;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.cityField)
    TextView cityField;
    @BindView(R.id.currentTemp)
    TextView currentTemp;
    @BindView(R.id.weather_icon01)
    ImageView weatherIcon01;
    @BindView(R.id.currentWeather)
    TextView currentWeather;
    @BindView(R.id.currentWind)
    TextView currentWind;
    @BindView(R.id.index_d)
    TextView indexD;
    @BindView(R.id.weather_icon02)
    ImageView weatherIcon02;
    @BindView(R.id.weather02)
    TextView weather02;
    @BindView(R.id.temp02)
    TextView temp02;
    @BindView(R.id.wind02)
    TextView wind02;
    @BindView(R.id.weather_icon03)
    ImageView weatherIcon03;
    @BindView(R.id.weather03)
    TextView weather03;
    @BindView(R.id.temp03)
    TextView temp03;
    @BindView(R.id.wind03)
    TextView wind03;
    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        ButterKnife.bind(this);
        initDingWei();
        //RestPool.getInstance().getService().;
    }

    private void initDingWei() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        //  option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);
            //   sb.append(location.getLatitude());    //获取纬度信息
            latitude = new BigDecimal(location.getLatitude()).setScale(6, BigDecimal.ROUND_HALF_EVEN).doubleValue();
            longitude = new BigDecimal(location.getLongitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            // sb.append("\nlontitude : ");
            //  sb.append(location.getLongitude());    //获取经度信息

            // sb.append("\nradius : ");
            // sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果

                latitude = new BigDecimal(location.getLatitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                longitude = new BigDecimal(location.getLongitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                latitude = new BigDecimal(location.getLatitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                longitude = new BigDecimal(location.getLongitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                //  sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果

                latitude = new BigDecimal(location.getLatitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                longitude = new BigDecimal(location.getLongitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                //  sb.append("\ndescribe : ");
                // sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }
            initData(latitude + "," + longitude);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private void initData(String location) {
        RestPool.getInstance().getService().weather("756571ec84bad655", location).enqueue(new HttpCallBack<Weather>(this, true, "加载中...") {

            @Override
            public void onSuccess(Weather weather) {
                if (weather != null) {
                    if (!CommonUtils.isEmpty(weather.getDate())){
                        if(!CommonUtils.isEmpty(weather.getWeek())){
                            dateY.setText(weather.getDate()+"("+weather.getWeek()+")");
                        }else {
                            dateY.setText(weather.getDate());
                        }
                    }
                    if (!CommonUtils.isEmpty(weather.getUpdatetime())){
                       date.setText("更新时间："+weather.getUpdatetime());
                    }
                    if (!CommonUtils.isEmpty(weather.getCity())){
                        cityField.setText(weather.getCity());
                    }
                    if (!CommonUtils.isEmpty(weather.getTemphigh())&&!CommonUtils.isEmpty(weather.getTemplow())){
                       currentTemp.setText(weather.getTemplow()+"℃"+"~"+weather.getTemplow()+"℃");
                    }
                    if (!CommonUtils.isEmpty(weather.getImg())){
                        weatherIcon01.setImageResource(ResouceMipmap(weather.getImg()));
                    }
                    if (!CommonUtils.isEmpty(weather.getWeather())){
                        currentWeather.setText(weather.getWeather());
                    }
                    if (!CommonUtils.isEmpty(weather.getWinddirect())){
                        currentWind.setText(weather.getWinddirect());
                    }
                    if (weather.getIndex()!=null&&weather.getIndex().size()!=0){
                        if (!CommonUtils.isEmpty(weather.getIndex().get(6).getDetail())){
                            indexD.setText(weather.getIndex().get(6).getDetail());
                        }
                    }
                    List<Weather.DailyEntity> daily = weather.getDaily();
                    if (daily!=null&&daily.size()!=0){
                        Weather.DailyEntity.DayEntity day1 = daily.get(1).getDay();
                        Weather.DailyEntity.NightEntity night1 = daily.get(1).getNight();
                        Weather.DailyEntity.DayEntity day2 = daily.get(2).getDay();
                        Weather.DailyEntity.NightEntity night2 = daily.get(2).getNight();
                        if (day1!=null){
                            if (!CommonUtils.isEmpty(day1.getImg())){
                                weatherIcon02.setImageResource(ResouceMipmap(day1.getImg()));
                            }
                            if (!CommonUtils.isEmpty(day1.getWeather())){
                                weather02.setText(day1.getWeather());
                            }
                            if (!CommonUtils.isEmpty(day1.getWinddirect())){
                                if (!CommonUtils.isEmpty(day1.getWindpower())){
                                    wind02.setText(day1.getWinddirect()+day1.getWindpower());
                                }else {
                                    wind02.setText(day1.getWinddirect());
                                }
                            }
                            if (!CommonUtils.isEmpty(day1.getTemphigh())){
                                if (night1!=null){
                                    if (!CommonUtils.isEmpty(night1.getTemplow())){
                                        temp02.setText(night1.getTemplow()+"℃"+"~"+day1.getTemphigh()+"℃");
                                    }else {
                                        temp02.setText(night1.getTemplow()+"℃");
                                    }
                                }
                            }
                       }

                        if (day2!=null){
                            if (!CommonUtils.isEmpty(day2.getImg())){
                                weatherIcon03.setImageResource(ResouceMipmap(day2.getImg()));
                            }
                            if (!CommonUtils.isEmpty(day2.getWeather())){
                                weather03.setText(day2.getWeather());
                            }
                            if (!CommonUtils.isEmpty(day2.getWinddirect())){
                                if (!CommonUtils.isEmpty(day2.getWindpower())){
                                    wind03.setText(day2.getWinddirect()+day2.getWindpower());
                                }else {
                                    wind03.setText(day2.getWinddirect());
                                }
                            }
                            if (!CommonUtils.isEmpty(day2.getTemphigh())){
                                if (night2!=null){
                                    if (!CommonUtils.isEmpty(night2.getTemplow())){
                                        temp03.setText(night2.getTemplow()+"℃"+"~"+day2.getTemphigh()+"℃");
                                    }else {
                                        temp03.setText(night2.getTemplow()+"℃");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(int code, String message) {
                if (code == 201) {
                    ToastUtil.show(WeatherActivity.this, "城市和城市ID和城市代号都为空");
                } else if (code == 202) {
                    ToastUtil.show(WeatherActivity.this, "城市不存在");
                } else if (code == 203) {
                    ToastUtil.show(WeatherActivity.this, "此城市没有天气信息");
                } else if (code == 210) {
                    ToastUtil.show(WeatherActivity.this, "没有信息");
                } else {
                    ToastUtil.show(WeatherActivity.this, message);
                }
            }
        });

    }

    private int ResouceMipmap(String mip){
        int resoucemip=0;
        switch (mip){
            case "0":
                resoucemip= R.mipmap.w0;
                break;
            case "1":
                resoucemip= R.mipmap.w1;
                break;
            case "2":
                resoucemip= R.mipmap.w2;
                break;
            case "3":
                resoucemip= R.mipmap.w3;
                break;
            case "4":
                resoucemip= R.mipmap.w4;
                break;
            case "5":
                resoucemip= R.mipmap.w5;
                break;
            case "6":
                resoucemip= R.mipmap.w6;
                break;
            case "7":
                resoucemip= R.mipmap.w7;
                break;
            case "8":
                resoucemip= R.mipmap.w8;
                break;
            case "9":
                resoucemip= R.mipmap.w9;
                break;
            case "10":
                resoucemip= R.mipmap.w10;
                break;
            case "11":
                resoucemip= R.mipmap.w11;
                break;
            case "12":
                resoucemip= R.mipmap.w12;
                break;
            case "13":
                resoucemip= R.mipmap.w13;
                break;
            case "14":
                resoucemip= R.mipmap.w14;
                break;
            case "15":
                resoucemip= R.mipmap.w15;
                break;
            case "16":
                resoucemip= R.mipmap.w16;
                break;
            case "17":
                resoucemip= R.mipmap.w17;
                break;
            case "18":
                resoucemip= R.mipmap.w18;
                break;
            case "19":
                resoucemip= R.mipmap.w19;
                break;
            case "20":
                resoucemip= R.mipmap.w20;
                break;
            case "21":
                resoucemip= R.mipmap.w21;
                break;
            case "22":
                resoucemip= R.mipmap.w22;
                break;
            case "23":
                resoucemip= R.mipmap.w23;
                break;
            case "24":
                resoucemip= R.mipmap.w24;
                break;
            case "25":
                resoucemip= R.mipmap.w25;
                break;
            case "26":
                resoucemip= R.mipmap.w26;
                break;
            case "27":
                resoucemip= R.mipmap.w27;
                break;
            case "28":
                resoucemip= R.mipmap.w28;
                break;
            case "29":
                resoucemip= R.mipmap.w29;
                break;
            case "30":
                resoucemip= R.mipmap.w30;
                break;
            case "31":
                resoucemip= R.mipmap.w31;
                break;
            case "32":
                resoucemip= R.mipmap.w32;
                break;
            case "49":
                resoucemip= R.mipmap.w49;
                break;
            case "53":
                resoucemip= R.mipmap.w53;
                break;
            case "54":
                resoucemip= R.mipmap.w54;
                break;
            case "55":
                resoucemip= R.mipmap.w55;
                break;
            case "56":
                resoucemip= R.mipmap.w56;
                break;
            case "57":
                resoucemip= R.mipmap.w57;
                break;
            case "58":
                resoucemip= R.mipmap.w58;
                break;
            case "99":
                resoucemip= R.mipmap.w99;
                break;
            case "301":
                resoucemip= R.mipmap.w301;
                break;
            case "302":
                resoucemip= R.mipmap.w302;
                break;

        }

        return resoucemip;
    }
}
