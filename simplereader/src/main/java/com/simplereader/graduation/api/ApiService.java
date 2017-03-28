package com.simplereader.graduation.api;

import com.simplereader.graduation.base.ResultResponse;
import com.simplereader.graduation.model.CommentList;
import com.simplereader.graduation.model.News;
import com.simplereader.graduation.model.VideoModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by gxj on 2017/3/24.
 */

public interface ApiService {
    String HOST = "http://192.168.0.116:88/";    //baseUrl
    String API_SERVICE_URL = HOST + "api/";
    String ARTICLE_FEED = "article/feed/";
    String COMMENT_LIST = "comment/list";
    String HOST_VIDEO = "http://i.snssdk.com";
    String URL_VIDEO = "/video/urls/v/1/toutiao/mp4/%s?r=%s";

    /**
     * 获取新闻数据列表
     */
    @GET(ARTICLE_FEED + "?utm_source=toutiao&widen=1&max_behot_time_tmp=0&as=A1C528E25E76FB8&cp=582EC64FEBD84E1&max_behot_time=0")
    Observable<ResultResponse<List<News>>> getNews(@Query("category") String category);

    /**
     * 获取评论数据
     *
     * @param group_id
     * @param item_id
     * @param offset
     * @param count
     * @return
     */
    @GET(COMMENT_LIST)
    Observable<ResultResponse<CommentList>> getComment(@Query("group_id") String group_id,
                                                       @Query("item_id") String item_id,
                                                       @Query("offset") String offset,
                                                       @Query("count") String count);

    /**
     * 获取视频页的html代码
     */
    @GET
    Observable<String> getVideoHtml(@Url String url);


    /**
     * 获取视频数据json
     *
     * @param url
     * @return
     */
    @GET
    Observable<ResultResponse<VideoModel>> getVideoData(@Url String url);

}
