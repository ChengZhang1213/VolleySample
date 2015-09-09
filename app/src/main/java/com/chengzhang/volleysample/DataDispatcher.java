package com.chengzhang.volleysample;

/**
 * Created by zhangcheng on 15/9/8.
 */
public interface DataDispatcher<T> {
    void doDataChanged(T data);
    void doErrorHappened(String message, int errorCode);
}
