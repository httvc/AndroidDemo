package com.httvc.androiddemo.ui.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.httvc.androiddemo.R;
import com.httvc.androiddemo.api.HttpCallBack;
import com.httvc.androiddemo.api.RestPool;
import com.httvc.androiddemo.pojo.Weather;
import com.httvc.androiddemo.utils.ToastUtil;

import java.math.BigDecimal;


public class RetrofitDemoActivity extends AppCompatActivity {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
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
            latitude=new BigDecimal(location.getLatitude()).setScale(6,BigDecimal.ROUND_HALF_EVEN).doubleValue();
            longitude = new BigDecimal(location.getLongitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
            // sb.append("\nlontitude : ");
            //  sb.append(location.getLongitude());    //获取经度信息

            // sb.append("\nradius : ");
            // sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果

                latitude=new BigDecimal(location.getLatitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
                longitude = new BigDecimal(location.getLongitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                latitude=new BigDecimal(location.getLatitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
                longitude = new BigDecimal(location.getLongitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
                //  sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果

                latitude=new BigDecimal(location.getLatitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
                longitude = new BigDecimal(location.getLongitude()).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
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
            initData(latitude+","+longitude);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    private void initData(String location){
        RestPool.getInstance().getService().weather("756571ec84bad654",location).enqueue(new HttpCallBack<Weather>(this,true,"加载中...") {

            @Override
            public void onSuccess(Weather weather) {
                if (weather!=null){

                }
            }

            @Override
            public void onFailure(int code, String message) {
                if (code==201){
                    ToastUtil.show(RetrofitDemoActivity.this,"城市和城市ID和城市代号都为空");
                }else if (code==202){
                    ToastUtil.show(RetrofitDemoActivity.this,"城市不存在");
                }else if (code==203){
                    ToastUtil.show(RetrofitDemoActivity.this,"此城市没有天气信息");
                }else if (code==210){
                    ToastUtil.show(RetrofitDemoActivity.this,"没有信息");
                }else {
                    ToastUtil.show(RetrofitDemoActivity.this,message);
                }
            }
        });

    }
}
