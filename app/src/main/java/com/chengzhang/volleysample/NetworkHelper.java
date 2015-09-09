package com.chengzhang.volleysample;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zhangcheng on 15/9/9.
 */
public abstract class NetworkHelper<T> implements Response.Listener<JSONObject>, Response.ErrorListener {
    private static final String TAG = "NetworkHelper";
    private DataDispatcher<T> dataDispatcher;
    private Context context;
    public NetworkHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.v(TAG, response.toString());
        disposeResponse(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.v(TAG, error.toString(), error);
        disposeErrorResponse(error);
    }

    protected NetworkJsonRequest doRequestForGet(String url) {
        return new NetworkJsonRequest(url,this, this);
    }

    protected NetworkJsonRequest doRequestForPost(String url, Map<String, String> params) {
        return new NetworkJsonRequest(Request.Method.POST, url, params, this, this);
    }

    public void setDataDispatcher(DataDispatcher<T> dataDispatcher) {
        this.dataDispatcher = dataDispatcher;
    }

    public void notifyDataChanged(T data){
        if(dataDispatcher !=null){
            dataDispatcher.doDataChanged(data);
        }
    }
    public void notifyDataError(String message,int errorCode){
        if(dataDispatcher !=null){
            dataDispatcher.doErrorHappened(message, errorCode);
        }
    }

    protected abstract void disposeErrorResponse(VolleyError error);

    protected abstract void disposeResponse(JSONObject response);


    public void sendGetRequest(String jsonUrl) {
        Volley.newRequestQueue(context).add(doRequestForGet(jsonUrl));
    }

    public void sendPostRequest(String postUrl, Map<String, String> params) {
        Volley.newRequestQueue(context).add(doRequestForPost(postUrl,params));
    }
}
