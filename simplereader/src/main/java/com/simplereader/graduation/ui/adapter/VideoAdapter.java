package com.simplereader.graduation.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.simplereader.graduation.model.News;
import com.simplereader.graduation.model.Video;
import com.simplereader.graduation.theme.util.util.ColorUiUtil;
import com.simplereader.graduation.ui.view.SPVideoPlayer;
import com.simplereader.graduation.util.ImageLoadUtil;
import com.simplereader.graduation.util.VideoPathDecoder;
import com.simplereader.simplereader.R;

import java.util.List;

/**
 * Created by gxj on 2017/4/7.
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
