package com.simplereader.graduation.model;

import java.util.List;

/**
 * Created by gxj on 2017/3/24.
 */

public class News {
    /**
     * chinese_tag : 体育
     * media_avatar_url : http://p3.pstatp.com/large/bc2000a3611645791f7
     * article_genre : article
     * tag_url : news_sports
     * title : 13岁日本籍华裔立誓击败中国夺奥运金牌，来中国被打到怀疑人生
     * middle_mode : false
     * gallary_image_count : 5
     * image_list : [{"url":"http://p2.pstatp.com/list/ef5000fa3ce130815bc"},{"url":"http://p9.pstatp.com/list/ef5000fa3f737996347"},{"url":"http://p3.pstatp.com/list/e59000f7ce3e08668b6"}]
     * behot_time : 1479438367
     * source_url : /group/6351937181648732418/
     * source : 苏老湿聊体育
     * more_mode : true
     * is_feed_ad : false
     * comments_count : 9000
     * has_gallery : false
     * single_mode : true
     * image_url : http://p2.pstatp.com/list/190x124/ef5000fa3ce130815bc
     * group_id : 6351937181648732418
     * is_diversion_page : false
     * media_url : http://toutiao.com/m50282400523/
     */
    private String chinese_tag;
    private String media_avatar_url;
    private String article_genre;
    private String tag_url;
    private String title;
    private boolean middle_mode;
    private int gallary_image_count;
    public List<ImageUrl> iamge_list;
    private long behot_time;
    private String source_url;
    private String source;
    private boolean more_mode;
    private boolean is_feed_ad;
    private int comment_counts;
    private boolean has_gallery;
    private boolean single_mode;
    private String image_url;
    private String group_id;
    private boolean is_diversion_page;
    private String media_url;

    @Override
    public String toString() {
        return "News{" +
                "chinese_tag='" + chinese_tag + '\'' +
                ", media_avatar_url='" + media_avatar_url + '\'' +
                ", article_genre='" + article_genre + '\'' +
                ", tag_url='" + tag_url + '\'' +
                ", title='" + title + '\'' +
                ", middle_mode=" + middle_mode +
                ", gallary_image_count=" + gallary_image_count +
                ", iamge_list=" + iamge_list +
                ", behot_time=" + behot_time +
                ", source_url='" + source_url + '\'' +
                ", source='" + source + '\'' +
                ", more_mode=" + more_mode +
                ", is_feed_ad=" + is_feed_ad +
                ", comment_counts=" + comment_counts +
                ", has_gallery=" + has_gallery +
                ", single_mode=" + single_mode +
                ", image_url='" + image_url + '\'' +
                ", group_id='" + group_id + '\'' +
                ", is_diversion_page=" + is_diversion_page +
                ", media_url='" + media_url + '\'' +
                '}';
    }
}

