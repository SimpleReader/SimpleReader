package com.umeng.soexample.theme.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description:sp工具类
 * Created by chenggong on 2017/4/7.
 */

public class SharedPreferencesMgr {
    private static Context context;
    private static SharedPreferences sharedPreferences;

    private SharedPreferencesMgr(Context context, String fileName) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static void init(Context context, String fileName) {
        new SharedPreferencesMgr(context, fileName);
    }

    public static String fileName;

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static String getString(String key, String defaultValue) {
        if (sharedPreferences == null) {
            return defaultValue;
        }
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void setString(String key, String value) {
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 删除sp中的json数据
     */
    public static void clearAll() {
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().clear().commit();
    }
}
