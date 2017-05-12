package com.umeng.soexample.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:网络工具类
 * Created by chenggong on 2017/4/24.
 */

public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName();

    /**
     * 设备是否连接到WiFi或4G
     *
     * @param context 上下文
     * @return 网络连接true 否则 false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infoWifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (infoWifi != null) {
            NetworkInfo.State wifi = infoWifi.getState();
            if (wifi == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        NetworkInfo infoMobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (infoMobile != null) {
            NetworkInfo.State mobile = infoMobile.getState();
            if (mobile == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        Log.d(TAG, "Network not available");
        return false;
    }

    /**
     * 检查WiFi是否连接
     *
     * @param context 上下文
     * @return 连接true
     */
    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 检查移动网络是否连接
     *
     * @param context 上下文
     * @return 连接true
     */
    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * Get the network info
     *
     * @param context 上下文
     * @return network
     */
    @Nullable
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 检查 URL 是否合法
     *
     * @param url
     * @return true 合法，false 非法
     */
    public static boolean isNetworkUrl(String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

}
