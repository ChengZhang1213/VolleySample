package com.chengzhang.volleysample.domain;

/**
 * Created by zhangcheng on 15/9/9.
 */
public class UserBean {
    public String username;
    public String password;

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
