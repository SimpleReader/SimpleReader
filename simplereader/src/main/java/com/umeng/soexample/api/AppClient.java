package com.umeng.soexample.api;

import com.google.gson.GsonBuilder;
import com.umeng.soexample.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Description:Retrofit网络请求封装
 * Created by chenggong on 2017/3/27.
 */

public class AppClient {
    public static Retrofit mRetrofit;
    /**
     * @return
     */
    public static Retrofit retrofit(String baseUrl) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                //设置日志打印级别 请求/相应行+头+体
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }

            /**
             * 拦截到请求 然后对请求进行修改
             */
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    //模拟请求 添加header请求头
                    builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.0.0.13547");
                    builder.addHeader("Cache-Control", "max-age=0");
                    builder.addHeader("Upgrade-Insecure-Request", "1");
                    builder.addHeader("X-Request-With", "XMLHttpRequest");
                    builder.addHeader("Cookie", "uuid=\"w:f2e0e469165542f8a3960f67cb354026\"; __tasessionId=4p6q77g6q1479458262778; csrftoken=7de2dd812d513441f85cf8272f015ce5; tt_webid=36385357187");

                    //拦截器关键部分 返回响应
                    return chain.proceed(builder.build());
                }
            });
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    //转换成String类型的工厂
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //json转为实体类的工厂
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    //支持rxjava 将响应转为Observable类型 默认Call
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    public static ApiService getApiService(String baseUrl) {
        return retrofit(baseUrl).create(ApiService.class);
    }
}
