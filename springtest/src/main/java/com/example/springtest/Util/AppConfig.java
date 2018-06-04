package com.example.springtest.Util;

public class AppConfig {

    public static String userId;

    public static void setUserId(String userId) {
        AppConfig.userId = userId;
    }

    public static String getUserId() {
        return userId;
    }
}
