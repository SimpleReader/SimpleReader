package com.umeng.soexample.model;

import java.util.List;

/**
 * Created by gxj on 2017/3/24.
 */

public class Comment {

    private String text;
    private int digg_count;
    private ReplyDataBean reply_data;
    private int reply_count;
    private int create_time;
    private UserBean user;
    private long dongtai_id;
    private long id;

    public static class ReplyDataBean {
        public List<?> reply_list;
    }

    public static class UserBean {
        private String aavatar_url;
        private long user_id;
        private String name;
    }
}
