package com.chengzhang.volleysample;

import android.content.Context;
import com.android.volley.VolleyError;
import com.chengzhang.volleysample.domain.UserBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangcheng on 15/9/9.
 */
public class UserRequest extends NetworkHelper<UserBean> {

    public UserRequest(Context context) {
        super(context);
    }

    @Override
    protected void disposeErrorResponse(VolleyError error) {
        notifyDataError(error.getMessage(),error.hashCode());
    }

    @Override
    protected void disposeResponse(JSONObject response) {
        UserBean userBean = null ;
        if(response!=null){
            try {
                String username = response.getString("username");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //notifyDataChanged();

    }
}
