package com.simplereader.graduation.api;

import com.simplereader.graduation.bean.Gank;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by gxj on 2017/2/28.
 */

public interface ApiService {
    String BASE_URL="http://gank.io/";

    @GET("api/data/Android/10/{page}")
    Observable<Gank> getGank(@Path("page") String page);
}
