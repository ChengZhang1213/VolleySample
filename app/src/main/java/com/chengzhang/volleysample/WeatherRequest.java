package com.chengzhang.volleysample;

import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.chengzhang.volleysample.domain.WeatherBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangcheng on 15/9/9.
 */
public class WeatherRequest extends NetworkHelper<WeatherBean> {
    public WeatherRequest(Context context) {
        super(context);
    }

    @Override
    protected void disposeErrorResponse(VolleyError error) {
        notifyDataError(error == null ? "NULL" : error.getMessage(), 1);

    }

    @Override
    protected void disposeResponse(JSONObject response) {
        WeatherBean weatherBean = null;
        if (response != null) {
            Log.i("WeatherRequest", response.toString());

            try {
                JSONObject weatherJson = response.getJSONObject("weatherinfo");
              //  JSONArray weatherinfo = response.getJSONArray("weatherinfo");
               // for(int i = 0;i<weatherinfo.length();i++){
                    String city = weatherJson.getString("city");
                    String date_y = weatherJson.getString("date_y");
                    String weather6 = weatherJson.getString("weather6");
                    String temp6 = weatherJson.getString("temp6");
                    if (city != null && date_y != null && weather6 != null && temp6 != null) {
                        weatherBean = new WeatherBean();
                        weatherBean.city = city;
                        weatherBean.date_y = date_y;
                        weatherBean.weather = weather6;
                        weatherBean.temperature = temp6;
                        notifyDataChanged(weatherBean);
                    }
               // }
            } catch (JSONException e) {
                e.printStackTrace();
                notifyDataError(e.getMessage(),e.hashCode());
            }
            //System.out.println("-------"+response.toString());
            //notifyDataChanged(weatherBean);
        }

    }
}
