package com.umeng.soexample.model;

import java.util.List;

/**
 * Description:收藏文章实体类
 * Created by chenggong on 2017/5/5.
 */

public class ArticleFavour {

    /**
     * status : 200
     * message :
     * data : [{"username":"admin","article_id":"2","favourite_id":1,"favourite_date":1493795913000,"article":{"article_id":"2","article_title":"油画｜春未央，心上一处清喜水泽","article_author":"張震绘生活","article_sketch":"春浓，轻灵盈暖，芳菲皆处，一场争奇斗艳的盛开，更显得深长而浩荡。而我，却喜欢将一颗心放在一弯水月里沉沦，感知一份恒美而清凉的静谧。依月，亭立，使劲地眨一下眼，抖落眉睫上的薄薄尘埃；一双明亮的眸子，泛起一抹清透的烟波蓝。","article_thumbnail":"http://upload-images.jianshu.io/upload_images/3870720-44173ca565abca5d.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50","article_time":1493790918000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/3870720/02c2bd94c8b0.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144","article_content":"http://www.jianshu.com/p/5efaab64ed1e"}}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : admin
         * article_id : 2
         * favourite_id : 1
         * favourite_date : 1493795913000
         * article : {"article_id":"2","article_title":"油画｜春未央，心上一处清喜水泽","article_author":"張震绘生活","article_sketch":"春浓，轻灵盈暖，芳菲皆处，一场争奇斗艳的盛开，更显得深长而浩荡。而我，却喜欢将一颗心放在一弯水月里沉沦，感知一份恒美而清凉的静谧。依月，亭立，使劲地眨一下眼，抖落眉睫上的薄薄尘埃；一双明亮的眸子，泛起一抹清透的烟波蓝。","article_thumbnail":"http://upload-images.jianshu.io/upload_images/3870720-44173ca565abca5d.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50","article_time":1493790918000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/3870720/02c2bd94c8b0.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144","article_content":"http://www.jianshu.com/p/5efaab64ed1e"}
         */

        private String username;
        private String article_id;
        private int favourite_id;
        private long favourite_date;
        private Article article;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public int getFavourite_id() {
            return favourite_id;
        }

        public void setFavourite_id(int favourite_id) {
            this.favourite_id = favourite_id;
        }

        public long getFavourite_date() {
            return favourite_date;
        }

        public void setFavourite_date(long favourite_date) {
            this.favourite_date = favourite_date;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }

    }
}
