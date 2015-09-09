package com.chengzhang.volleysample.manager;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangcheng on 15/9/8.
 */
public class BaseApplication extends Application {
    private static BaseApplication context;
    @Override
    public void onCreate() {
        context = this;
        super.onCreate();
    }
    public static BaseApplication getApplication(){
        return context;
    }

}
