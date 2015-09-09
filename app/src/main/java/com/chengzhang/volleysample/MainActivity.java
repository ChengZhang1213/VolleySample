package com.chengzhang.volleysample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chengzhang.volleysample.domain.UserBean;
import com.chengzhang.volleysample.domain.WeatherBean;

import java.util.LinkedHashMap;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "VOLLEY_SAMPLE";
    private Context context;
    private String postUrl = "http://192.168.23.2:8080/login";//this is the test server,you can replace by your server address
    private String jsonUrl = "http://weather.51wnl.com/weatherinfo/GetMoreWeather?cityCode=101040100&weatherType=0";
    private RequestQueue requestQueue;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_StringRequest:
                //doStringRequest();
                NetworkHelper<UserBean> userHelper = new UserRequest(this);
                userHelper.setDataDispatcher(new DataDispatcher<UserBean>() {
                    @Override
                    public void doDataChanged(UserBean data) {
                        Toast.makeText(context,data.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void doErrorHappened(String message, int errorCode) {
                        Toast.makeText(context,message+"\t -- "+errorCode,Toast.LENGTH_SHORT).show();
                    }
                });
                //fixme there should be convert
                Map<String ,String > params = new LinkedHashMap<String, String>();
                params.put("username", "steven");
                params.put("password","1");
                userHelper.sendPostRequest(postUrl,params);
                //userHelper.setDataDispatcher(this);
                //ResultPresent userPresent = new ResultPresent<UserBean>();

                break;
            case R.id.btn_JsonObjectRequest:
                //  doJsonObjectRequest();
               // ResultPresent weatherPresent = new ResultPresent<WeatherBean>();
                NetworkHelper<WeatherBean> weatherHelper =  new WeatherRequest(this);
                // networkHelper.setDataDispatcher(this);
                weatherHelper.setDataDispatcher(new DataDispatcher<WeatherBean>() {
                    @Override
                    public void doDataChanged(WeatherBean data) {
                        Toast.makeText(context,data.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void doErrorHappened(String message, int errorCode) {
                        Toast.makeText(context,message+"\t -- "+errorCode,Toast.LENGTH_SHORT).show();
                    }
                });
                weatherHelper.sendGetRequest(jsonUrl);



                break;
            default:
                break;
        }
    }

//    private void doJsonObjectRequest() {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(jsonUrl, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d(TAG, response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, error.getMessage(), error);
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }
//
//
//    private void doStringRequest() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, response );
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, error.getMessage(), error);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String ,String > map = new HashMap<String, String>();
//                map.put("username","steven");
//                map.put("password","1");
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        requestQueue = Volley.newRequestQueue(context);
        findViewById(R.id.btn_StringRequest).setOnClickListener(this);
        findViewById(R.id.btn_JsonObjectRequest).setOnClickListener(this);
    }
}
