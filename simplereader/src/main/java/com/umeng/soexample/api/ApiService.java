package com.umeng.soexample.api;

import com.umeng.soexample.base.ResultResponse;
import com.umeng.soexample.model.ArticleFavour;
import com.umeng.soexample.model.ArticleResponse;
import com.umeng.soexample.model.CommentList;
import com.umeng.soexample.model.HeWeather;
import com.umeng.soexample.model.LoginMessage;
import com.umeng.soexample.model.News;
import com.umeng.soexample.model.ResponseInfo;
import com.umeng.soexample.model.VideoModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Description:网络请求接口
 * Created by chenggong on 2017/3/24.
 */

public interface ApiService {
    String HOST = "http://www.toutiao.com/";    //baseUrl
    String API_SERVICE_URL = HOST + "api/";
    String ARTICLE_FEED = "pc/feed/";
    String COMMENT_LIST = "comment/list";
    String HOST_VIDEO = "http://i.snssdk.com";
    String URL_VIDEO = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
    //阿里云的服务器地址
    String HOST_MINE = "http://120.24.185.87:8080/graduate/";
    String URL_ARTICLE = HOST_MINE + "article/list.do";
    String URL_LOGIN = HOST_MINE + "user/login.do";
    String URL_REGISTER=HOST_MINE+"user/register.do";
    String URL_ARTICLE_FAVOUR=HOST_MINE+"article/favour/listByUser.do";
    String URL_FAVOUR_ARTICLE_ADD=HOST_MINE+"article/favour/addFavour.do";
    String URL_ISCOLLECTED=HOST_MINE+"article/favour/isCollected.do";
    String URL_CANCEL_FAVOUR=HOST_MINE+"article/favour/cancelFavour.do";
    String Host_WEATHER="https://free-api.heweather.com/v5/";
    String URL_HEWEATHER=Host_WEATHER+"weather";

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

    /**
     * 获取文章列表
     *
     * @param url
     * @return
     */
    @GET
    Observable<ArticleResponse> getArticleData(@Url String url);

    /**
     * 用户名登录
     *
     * @param username
     * @param password
     * @return
     */
    @POST(URL_LOGIN)
    Observable<LoginMessage> loginByUsername(@Query("username") String username,
                                             @Query("password") String password);

    /**
     * 通过用户名查询收藏列表
     * @param username
     * @return
     */
    @GET(URL_ARTICLE_FAVOUR)
    Observable<ArticleFavour> getArticleFavourList(@Query("username") String username);

    @POST(URL_FAVOUR_ARTICLE_ADD)
    Observable<ResponseInfo<String>> collectArticle(@Query("username") String username,
                                                    @Query("article_id") String article_id);

    /**
     * 查询是否收藏
     * @param body
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(URL_ISCOLLECTED)
    Observable<ResponseInfo<String>> isCollected(@Body RequestBody body);

    /**
     * 取消收藏
     * @param body
     * @return
     */
    @POST(URL_CANCEL_FAVOUR)
    Observable<ResponseInfo<String>> cancelCollect(@Body RequestBody body);

    /**
     * 用户注册
     * @param body
     * @return
     */
    @POST(URL_REGISTER)
    Observable<ResponseInfo<String>> register(@Body RequestBody body);

    /**
     * 获取天气情况
     * @param city
     * @param key
     * @return
     */
    @GET(URL_HEWEATHER)
    Observable<HeWeather> loadHeWeather(@Query("city") String city,
                                        @Query("key") String key);
}
