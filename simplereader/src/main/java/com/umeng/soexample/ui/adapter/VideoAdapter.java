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
        ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        final SPVideoPlayer spVideoPlayer = baseViewHolder.getView(R.id.videoPlayer);
        ImageLoadUtil.loadCircleImage(mContext, news.media_avatar_url, (ImageView) baseViewHolder.getView(R.id.ivAvatar));
        ImageLoadUtil.loadImage(news.image_url, spVideoPlayer.thumbImageView);
        spVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        baseViewHolder.setText(R.id.tvFrom, news.source)
                .setText(R.id.tvCommentCount, news.comment_counts + "");
        spVideoPlayer.titleTextView.setText(news.title);
        spVideoPlayer.setDurationText(news.video_duration_str);
        spVideoPlayer.backButton.setVisibility(View.GONE);
        if (news.video == null) {
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
            decoder.decodePath(news.source_url);
            Logger.e("source_url : " + news.source_url);
        } else {
            setPlayer(spVideoPlayer, news);
        }
    }

    private void setPlayer(SPVideoPlayer spVideoPlayer, News news) {
        Logger.e("main_url : " + news.video.main_url);
        spVideoPlayer.setUp(news.video.main_url, SPVideoPlayer.SCREEN_LAYOUT_LIST, news.title);
    }
}
