package com.httvc.androiddemo.pojo;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/6/2.
 */

public class IdCard {

    /**
     * province : 河南省
     * city : 周口市
     * town : 鹿邑县
     * lastflag : 0
     * sex : 男
     * birth : 1980年01月02日
     */

    private String province;
    private String city;
    private String town; //县
    private String lastflag;//最后一位效验码 0正确 1错误
    private String sex;
    private String birth;
    private String area;//区域信息  由于城市规划的原因，省市县变化较大。具体以此为准

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLastflag() {
        return lastflag;
    }

    public void setLastflag(String lastflag) {
        this.lastflag = lastflag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
