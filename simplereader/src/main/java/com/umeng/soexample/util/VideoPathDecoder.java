package com.umeng.soexample.util;

import android.util.Base64;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.ResultResponse;
import com.umeng.soexample.model.Video;
import com.umeng.soexample.model.VideoModel;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Description:视频地址已经加密 解析视频地址
 * Created by chenggong on 2017/4/11.
 */

public abstract class VideoPathDecoder {
    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    /**
     * 对视频获取视频网页代码
     * @param srcUrl
     */
    public void decodePath(final String srcUrl) {
        AppClient.getApiService(ApiService.API_SERVICE_URL).getVideoHtml(srcUrl)
                //将String转为Observable<ResultResponse<VideoModel>>
                .flatMap(new Func1<String, Observable<ResultResponse<VideoModel>>>() {
                    @Override
                    public Observable<ResultResponse<VideoModel>> call(String response) {
                        //字符串格式 videoid:'c0caf14684c5488eb6818f7ed2ee0a2f'
                        Pattern pattern = Pattern.compile("videoid:\'(.+)\'");
                        Matcher matcher = pattern.matcher(response);
                        if (matcher.find()) {
                            //得到第一个括号匹配的数据即vedioid
                            String videoId = matcher.group(1);
                            Logger.e("videoId : " + videoId);
                            //将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}，进行crc32加密校验。
                            String r = getRandom();
                            //CRC32校验
                            CRC32 crc32 = new CRC32();
                            String s = String.format(ApiService.URL_VIDEO, videoId, r);
                            //对地址进行crc32编码
                            crc32.update(s.getBytes());
                            String crcString = crc32.getValue() + "";
                            //使用无符号右移0位
                            String url = ApiService.HOST_VIDEO + s + "&s=" + crcString;
                            Logger.e("url : " + url);
                            return AppClient.getApiService(ApiService.API_SERVICE_URL).getVideoData(url);
                        }
                        return null;
                    }
                })
                .map(new Func1<ResultResponse<VideoModel>, Video>() {
                    @Override
                    public Video call(ResultResponse<VideoModel> videoModelResultResponse) {
                        VideoModel.VideoListBean data = videoModelResultResponse.data.video_list;
                        if (data.video_3 != null) {
                            return updateVideo(data.video_3);
                        }
                        if (data.video_2 != null) {
                            return updateVideo(data.video_2);
                        }
                        if (data.video_1 != null) {
                            return updateVideo(data.video_1);
                        }
                        return null;
                    }

                    //得到真实路径 通过base64解码
                    private String getRealPath(String base64) {
                        return new String(Base64.decode(base64.getBytes(), Base64.DEFAULT));
                    }

                    //修改vedio对象中的url字段
                    private Video updateVideo(Video video) {
                        video.main_url = getRealPath(video.main_url);
                        return video;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Video>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onDecodeError(e);
                    }

                    @Override
                    public void onNext(Video video) {
                        onSuccess(video);
                    }
                });

    }

    public abstract void onSuccess(Video s);

    public abstract void onDecodeError(Throwable e);

    /**
     * 生成16位随机数
     * @return
     */
    private String getRandom() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

}


