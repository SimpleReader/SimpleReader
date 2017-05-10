package com.umeng.soexample.util;

import android.content.Context;
import android.widget.Toast;

import com.umeng.soexample.MyApplication;

/**
 * Created by gxj on 2017/3/29.
 */

public class ToastUtils {
    private static Toast mToast;

    /**
     * 显示Toast
     */
    public static void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void showToast(Context context, CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }
}