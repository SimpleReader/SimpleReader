package com.simplereader.graduation.model;

/**Description: 文章实体类
 * Created by chenggong on 2017/5/4.
 */

public class Article {
    public String articleId;
    public String articleTitle;
    public String articleAuthor;
    public String articleSketch;
    public String articleThumbnail;
    public String articleTime;
    public String articleAvatar;
    public String articleContent;

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleSketch='" + articleSketch + '\'' +
                ", articleThumbnail='" + articleThumbnail + '\'' +
                ", articleTime='" + articleTime + '\'' +
                ", articleAvatar='" + articleAvatar + '\'' +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }
}
