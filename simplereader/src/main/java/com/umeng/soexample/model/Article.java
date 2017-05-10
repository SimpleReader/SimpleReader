package com.umeng.soexample.model;

/**Description: 文章实体类
 * Created by chenggong on 2017/5/4.
 */

public class Article {
    private String article_id;
    private String article_title;
    private String article_author;
    private String article_sketch;
    private String article_thumbnail;
    private long article_time;
    private String article_avatar;
    private String article_content;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_sketch() {
        return article_sketch;
    }

    public void setArticle_sketch(String article_sketch) {
        this.article_sketch = article_sketch;
    }

    public String getArticle_thumbnail() {
        return article_thumbnail;
    }

    public void setArticle_thumbnail(String article_thumbnail) {
        this.article_thumbnail = article_thumbnail;
    }

    public long getArticle_time() {
        return article_time;
    }

    public void setArticle_time(long article_time) {
        this.article_time = article_time;
    }

    public String getArticle_avatar() {
        return article_avatar;
    }

    public void setArticle_avatar(String article_avatar) {
        this.article_avatar = article_avatar;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }
}
