package com.umeng.soexample.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.umeng.soexample.model.User;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;

/**
 * Description: 用户登录状态工具类
 * Created by chenggong on 2017/5/5.
 */

public class LoginStatusUtils {
    private static LoginStatusUtils mInstance;
    private Gson mGson;

    private LoginStatusUtils() {
        mGson = new Gson();
    }

    public synchronized static LoginStatusUtils getInstance() {
        if (mInstance == null) {
            mInstance = new LoginStatusUtils();
        }
        return mInstance;
    }


    /**
     * 判断用户是否已经登录(暂时使用sp方法判断)
     *
     * @return
     */
    public boolean isLoginStatus() {
        String userInfo = SharedPreferencesMgr.getString(ConstanceValue.SP_USER, "");
        if (!TextUtils.isEmpty(userInfo)) {
            return true;
        }
        return false;
    }

    /**
     * 将user对象转为String类型类型
     *
     * @param user
     * @return
     */
    public String user2Json(User user) {
        String json = "";
        if (user != null) {
            json = mGson.toJson(user);
        }
        return json;
    }

    /**
     * 将json转为user对象
     *
     * @param json
     * @return
     */
    public User json2User(String json) {
        if (!TextUtils.isEmpty(json)) {
            User user = mGson.fromJson(json, User.class);
            return user;
        }
        return null;
    }

    /**
     * 删除sp中的json数据
     */
    public void clearData(){
        SharedPreferencesMgr.setString(ConstanceValue.SP_USER,"");
    }



}
