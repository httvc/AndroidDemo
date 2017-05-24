package com.httvc.androiddemo.pojo;

import java.util.List;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/5/23.
 */

public class Weather {
    private String city;      //城市
    private String cityid;   //城市ID
    private String citycode; //城市天气代号
    private String date;     //日期
    private String week;     //星期
    private String weather;  //天气
    private String temp;     //气温
    private String temphigh; //最高气温
    private String templow;  //最低气温
    private String img;      //图片数字
    private String humidity; //湿度
    private String pressure; //气压
    private String windspeed; //风速
    private String winddirect;//风向
    private String windpower;//风级
    private String updatetime;//更新时间
    private AqiEntity aqi;     //
    private List<IndexEntity> index;
    private List<DailyEntity> daily;
    private List<HourlyEntity> hourly;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemphigh() {
        return temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public AqiEntity getAqi() {
        return aqi;
    }

    public void setAqi(AqiEntity aqi) {
        this.aqi = aqi;
    }

    public List<IndexEntity> getIndex() {
        return index;
    }

    public void setIndex(List<IndexEntity> index) {
        this.index = index;
    }

    public List<DailyEntity> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyEntity> daily) {
        this.daily = daily;
    }

    public List<HourlyEntity> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyEntity> hourly) {
        this.hourly = hourly;
    }

    public static class AqiEntity {
        /**
         * so2 : 37
         * so224 : 43
         * no2 : 24
         * no224 : 21
         * co : 0.647
         * co24 : 0.675
         * o3 : 26
         * o38 : 14
         * o324 : 30
         * pm10 : 30
         * pm1024 : 35
         * pm2_5 : 23
         * pm2_524 : 24
         * iso2 : 13
         * ino2 : 13
         * ico : 7
         * io3 : 9
         * io38 : 7
         * ipm10 : 35
         * ipm2_5 : 35
         * aqi : 35
         * primarypollutant : PM10
         * quality : 优
         * timepoint : 2015-12-09 16:00:00
         * aqiinfo : {"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}
         */

        private String so2;
        private String so224;
        private String no2;
        private String no224;
        private String co;
        private String co24;
        private String o3;
        private String o38;
        private String o324;
        private String pm10;
        private String pm1024;
        private String pm2_5;
        private String pm2_524;
        private String iso2;
        private String ino2;
        private String ico;
        private String io3;
        private String io38;
        private String ipm10;
        private String ipm2_5;
        private String aqi;
        private String primarypollutant;
        private String quality;
        private String timepoint;
        private AqiinfoEntity aqiinfo;

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getSo224() {
            return so224;
        }

        public void setSo224(String so224) {
            this.so224 = so224;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNo224() {
            return no224;
        }

        public void setNo224(String no224) {
            this.no224 = no224;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getCo24() {
            return co24;
        }

        public void setCo24(String co24) {
            this.co24 = co24;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO38() {
            return o38;
        }

        public void setO38(String o38) {
            this.o38 = o38;
        }

        public String getO324() {
            return o324;
        }

        public void setO324(String o324) {
            this.o324 = o324;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm1024() {
            return pm1024;
        }

        public void setPm1024(String pm1024) {
            this.pm1024 = pm1024;
        }

        public String getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getPm2_524() {
            return pm2_524;
        }

        public void setPm2_524(String pm2_524) {
            this.pm2_524 = pm2_524;
        }

        public String getIso2() {
            return iso2;
        }

        public void setIso2(String iso2) {
            this.iso2 = iso2;
        }

        public String getIno2() {
            return ino2;
        }

        public void setIno2(String ino2) {
            this.ino2 = ino2;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }

        public String getIo3() {
            return io3;
        }

        public void setIo3(String io3) {
            this.io3 = io3;
        }

        public String getIo38() {
            return io38;
        }

        public void setIo38(String io38) {
            this.io38 = io38;
        }

        public String getIpm10() {
            return ipm10;
        }

        public void setIpm10(String ipm10) {
            this.ipm10 = ipm10;
        }

        public String getIpm2_5() {
            return ipm2_5;
        }

        public void setIpm2_5(String ipm2_5) {
            this.ipm2_5 = ipm2_5;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPrimarypollutant() {
            return primarypollutant;
        }

        public void setPrimarypollutant(String primarypollutant) {
            this.primarypollutant = primarypollutant;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getTimepoint() {
            return timepoint;
        }

        public void setTimepoint(String timepoint) {
            this.timepoint = timepoint;
        }

        public AqiinfoEntity getAqiinfo() {
            return aqiinfo;
        }

        public void setAqiinfo(AqiinfoEntity aqiinfo) {
            this.aqiinfo = aqiinfo;
        }

        public static class AqiinfoEntity {
            /**
             * level : 一级
             * color : #00e400
             * affect : 空气质量令人满意，基本无空气污染
             * measure : 各类人群可正常活动
             */

            private String level;
            private String color;
            private String affect;
            private String measure;

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getAffect() {
                return affect;
            }

            public void setAffect(String affect) {
                this.affect = affect;
            }

            public String getMeasure() {
                return measure;
            }

            public void setMeasure(String measure) {
                this.measure = measure;
            }
        }
    }

    public static class IndexEntity {
        /**
         * iname : 空调指数
         * ivalue : 较少开启
         * detail : 您将感到很舒适，一般不需要开启空调。
         */

        private String iname;
        private String ivalue;
        private String detail;

        public String getIname() {
            return iname;
        }

        public void setIname(String iname) {
            this.iname = iname;
        }

        public String getIvalue() {
            return ivalue;
        }

        public void setIvalue(String ivalue) {
            this.ivalue = ivalue;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public static class DailyEntity {
        /**
         * date : 2015-12-22
         * week : 星期二
         * sunrise : 07:39
         * sunset : 18:09
         * night : {"weather":"多云","templow":"9","img":"1","winddirect":"无持续风向","windpower":"微风"}
         * day : {"weather":"多云","temphigh":"18","img":"1","winddirect":"无持续风向","windpower":"微风"}
         */

        private String date;
        private String week;
        private String sunrise;
        private String sunset;
        private NightEntity night;
        private DayEntity day;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public NightEntity getNight() {
            return night;
        }

        public void setNight(NightEntity night) {
            this.night = night;
        }

        public DayEntity getDay() {
            return day;
        }

        public void setDay(DayEntity day) {
            this.day = day;
        }

        public static class NightEntity {
            /**
             * weather : 多云
             * templow : 9
             * img : 1
             * winddirect : 无持续风向
             * windpower : 微风
             */

            private String weather;
            private String templow;
            private String img;
            private String winddirect;
            private String windpower;

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getTemplow() {
                return templow;
            }

            public void setTemplow(String templow) {
                this.templow = templow;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getWinddirect() {
                return winddirect;
            }

            public void setWinddirect(String winddirect) {
                this.winddirect = winddirect;
            }

            public String getWindpower() {
                return windpower;
            }

            public void setWindpower(String windpower) {
                this.windpower = windpower;
            }
        }

        public static class DayEntity {
            /**
             * weather : 多云
             * temphigh : 18
             * img : 1
             * winddirect : 无持续风向
             * windpower : 微风
             */

            private String weather;
            private String temphigh;
            private String img;
            private String winddirect;
            private String windpower;

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getTemphigh() {
                return temphigh;
            }

            public void setTemphigh(String temphigh) {
                this.temphigh = temphigh;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getWinddirect() {
                return winddirect;
            }

            public void setWinddirect(String winddirect) {
                this.winddirect = winddirect;
            }

            public String getWindpower() {
                return windpower;
            }

            public void setWindpower(String windpower) {
                this.windpower = windpower;
            }
        }
    }

    public static class HourlyEntity {
        /**
         * time : 16:00
         * weather : 多云
         * temp : 14
         * img : 1
         */

        private String time;
        private String weather;
        private String temp;
        private String img;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
