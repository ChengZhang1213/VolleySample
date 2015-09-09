package com.chengzhang.volleysample;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by zhangcheng on 15/9/8.
 */
public class NetworkJsonRequest extends JsonObjectRequest {
    // private Priority mPriority;

    public NetworkJsonRequest(int method, String url, Map<String, String> postParams, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, params2String(postParams), listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private static String params2String(Map<String, String> postParams) {
        if (postParams != null && postParams.size() > 0) {
            String paramsEncoding = "UTF-8";
            StringBuilder encodedParams = new StringBuilder();
            try {
                for (Map.Entry<String, String> entry : postParams.entrySet()) {
                    encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                    encodedParams.append('&');
                }
                return encodedParams.toString().substring(0,encodedParams.toString().length()-1);
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
            }
        }
        return null;
    }

    public NetworkJsonRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, null, listener, errorListener);

    }




    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            JSONObject jsonObject = new JSONObject(new String(response.data, "UTF-8"));
            return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

//    @Override
//    public Priority getPriority() {
//        return mPriority;
//    }
//
//    public void setmPriority(Priority mPriority) {
//        this.mPriority = mPriority;
//    }

}
