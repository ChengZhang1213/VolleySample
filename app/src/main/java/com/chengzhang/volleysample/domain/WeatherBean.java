package com.chengzhang.volleysample.domain;

/**
 * Created by zhangcheng on 15/9/9.
 */
public class WeatherBean {
    public String city;
    public String date_y;
    public String weather;
    public String temperature;

    @Override
    public String toString() {
        return "WeatherBean{" +
                "city='" + city + '\'' +
                ", date_y='" + date_y + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
