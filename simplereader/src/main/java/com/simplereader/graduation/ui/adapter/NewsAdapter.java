package com.simplereader.graduation.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simplereader.graduation.model.News;
import com.simplereader.graduation.theme.util.util.ColorUiUtil;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.util.DateUtils;
import com.simplereader.graduation.util.ImageLoadUtil;
import com.simplereader.simplereader.R;

import java.util.List;

/**
 * Created by gxj on 2017/3/29.
 */

public class NewsAdapter extends BaseQuickAdapter<News>{
    public NewsAdapter(List<News> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, News news) {
        //防止复用View没有改变主题，重新设置
        ColorUiUtil.changeTheme(baseViewHolder.convertView, mContext.getTheme());
        setGone(baseViewHolder);
        if (TextUtils.equals(news.article_genre, ConstanceValue.ARTICLE_GENRE_ARTICLE)) {
            //文章类型
            if (news.image_list == null || news.image_list.size() == 0) {
                if (!TextUtils.isEmpty(news.image_url)) {
                    //单图片文章
                    ImageLoadUtil.loadImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
                    baseViewHolder.setVisible(R.id.rlRightImg, true).setVisible(R.id.viewFill, true);
                }
            } else {
                //3张图片
                baseViewHolder.setVisible(R.id.llCenterImg, true);
                ImageLoadUtil.loadImage(news.image_list.get(0).url, (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));
                ImageLoadUtil.loadImage(news.image_list.get(1).url, (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));
                ImageLoadUtil.loadImage(news.image_list.get(2).url, (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));
            }
        } else if (TextUtils.equals(news.article_genre, ConstanceValue.ARTICLE_GENRE_GALLERY)) {
            //画廊类型
            if (news.image_list == null) {
                ImageLoadUtil.loadImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
                baseViewHolder.setVisible(R.id.rlRightImg, true).setVisible(R.id.viewFill, true);
            } else {
                ImageLoadUtil.loadImage(news.image_list.get(0).url, (ImageView) baseViewHolder.getView(R.id.ivBigImg));
                baseViewHolder.setVisible(R.id.rlRightImg, true).setText(R.id.tvImgCount, news.image_list.size() + "图");
            }
        } else if (TextUtils.equals(news.article_genre, ConstanceValue.ARTICLE_GENRE_VIDEO)) {
            //视频类型
            ImageLoadUtil.loadImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
            baseViewHolder.setVisible(R.id.rlRightImg, true).setVisible(R.id.viewFill, true).setVisible(R.id.llVideo, true)
                    .setText(R.id.tvTime, DateUtils.getShortTime(news.behot_time * 1000));
        }
        baseViewHolder.setText(R.id.tvTitle, news.title)
                .setText(R.id.tvAuthorName, news.source)
                .setText(R.id.tvCommentCount, news.comment_counts + "评论")
                .setText(R.id.tvTime, DateUtils.getShortTime(news.behot_time * 1000));
    }

    private void setGone(BaseViewHolder baseViewHolder) {
        baseViewHolder.setVisible(R.id.viewFill, false)
                .setVisible(R.id.llCenterImg, false)
                .setVisible(R.id.rlBigImg, false)
                .setVisible(R.id.llVideo, false)
                .setVisible(R.id.rlRightImg, false);
    }
}