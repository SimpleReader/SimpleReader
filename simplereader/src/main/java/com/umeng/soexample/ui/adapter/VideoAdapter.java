package com.umeng.soexample.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.umeng.soexample.R;
import com.umeng.soexample.model.News;
import com.umeng.soexample.model.Video;
import com.umeng.soexample.theme.util.ColorUiUtil;
import com.umeng.soexample.ui.view.SPVideoPlayer;
import com.umeng.soexample.util.ImageLoadUtil;
import com.umeng.soexample.util.VideoPathDecoder;

import java.util.List;

/**
 * Description:视频适配器
 * Created by chenggong on 2017/4/7.
 */

public class VideoAdapter extends BaseQuickAdapter<News> {

    public VideoAdapter(List<News> data) {
        super(R.layout.item_video_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final News news) {
        //修改主题
        ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        final SPVideoPlayer spVideoPlayer = baseViewHolder.getView(R.id.videoPlayer);
        ImageLoadUtil.loadCircleImage(mContext, news.media_avatar_url, (ImageView) baseViewHolder.getView(R.id.ivAvatar));
        //设置视频未播放时的缩略图
        ImageLoadUtil.loadImage(news.image_url, spVideoPlayer.thumbImageView);
        spVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //设置作者和评论数
        baseViewHolder.setText(R.id.tvFrom, news.source)
                .setText(R.id.tvCommentCount, news.comments_count + "");
        //设置视频的标题
        spVideoPlayer.titleTextView.setText(news.title);
        //设置显示视频时长
        spVideoPlayer.setDurationText(news.video_duration_str);
        //设置返回按钮不显示
        spVideoPlayer.backButton.setVisibility(View.GONE);
        //如果vedio为空
        if (news.video == null) {
            //由于地址加密，所以抽出一个类专门解析播放地址
            VideoPathDecoder decoder = new VideoPathDecoder() {
                @Override
                public void onSuccess(Video s) {
                    news.video = s;
                    setPlayer(spVideoPlayer, news);
                }

                @Override
                public void onDecodeError(Throwable e) {
                }
            };
            //传递视频的地址
            Logger.d("source_url:"+news.source_url);
            decoder.decodePath(news.source_url);
        } else {
            //解析完之后保存在vedio对象中 不为空 直接设置播放
            setPlayer(spVideoPlayer, news);
        }
    }

    //设置播放器视频源
    private void setPlayer(SPVideoPlayer spVideoPlayer, News news) {
        Logger.e("main_url : " + news.video.main_url);
        spVideoPlayer.setUp(news.video.main_url, SPVideoPlayer.SCREEN_LAYOUT_LIST, news.title);
    }
}
