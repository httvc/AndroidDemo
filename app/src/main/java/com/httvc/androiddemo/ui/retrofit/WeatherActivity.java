package com.httvc.androiddemo.ui.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.api.HttpCallBack;
import com.httvc.androiddemo.api.RestPool;
import com.httvc.androiddemo.pojo.Weather;
import com.httvc.androiddemo.utils.CommonUtils;
import com.httvc.androiddemo.utils.DrableMipUtils;
import com.httvc.androiddemo.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.httvc.androiddemo.utils.DrableMipUtils.ResouceMipmap;


public class WeatherActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null){
            String location = intent.getStringExtra("LOCATION");
            initData(location);
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
                                weatherIcon02.setImageResource(DrableMipUtils.ResouceMipmap(day1.getImg()));
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
                                weatherIcon03.setImageResource(DrableMipUtils.ResouceMipmap(day2.getImg()));
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


}
