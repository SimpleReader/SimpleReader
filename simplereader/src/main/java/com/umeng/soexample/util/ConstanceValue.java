package com.umeng.soexample.util;

/**
 * Created by gxj on 2017/3/29.
 */

public interface ConstanceValue {
    String DATA = "data";
    String ARTICLE_GENRE_GALLERY = "gallery";
    String ARTICLE_GENRE_VIDEO = "video";
    String ARTICLE_GENRE_ARTICLE = "article";
    String URL = "url";
    String SP_THEME = "theme";
    int THEME_LIGHT = 1;
    int TIME_NIGHT = 2;
    String SP_USER="sp_users"; //用户集合
    String SP_CITY="sp_city"; //城市名称
    final String CITY_DEFAULT="济南";
    final String KEY_WEATHER="f3974e96a7f6489e84099838abdb8e3e"; //和风天气appkey
    /**
     * 修改主题
     */
    int MSG_CHANGE_THEME = 100;
}
