package com.simplereader.graduation.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simplereader.graduation.model.Article;
import com.simplereader.graduation.model.ArticleFavour;
import com.simplereader.graduation.util.ImageLoadUtil;
import com.simplereader.simplereader.R;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by chenggong on 2017/5/5.
 */

public class ArticleFavourAdapter extends BaseQuickAdapter<ArticleFavour.DataBean> {


    public ArticleFavourAdapter(List<ArticleFavour.DataBean> data) {
        super(R.layout.item_article_favour, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ArticleFavour.DataBean dataBean) {
        Article article = dataBean.getArticle();

        baseViewHolder.setText(R.id.article_title, article.getArticle_title())
                .setText(R.id.text_author, article.getArticle_author())
                .setText(R.id.article_sketch, article.getArticle_sketch())
                .setText(R.id.text_favourData, android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", new Date(dataBean.getFavourite_date())));
        ImageLoadUtil.loadCircleImage(mContext, article.getArticle_avatar(), (ImageView) baseViewHolder.getView(R.id.image_head));
        ImageLoadUtil.loadImage(article.getArticle_thumbnail(), (ImageView) baseViewHolder.getView(R.id.image_thum));
    }
}
