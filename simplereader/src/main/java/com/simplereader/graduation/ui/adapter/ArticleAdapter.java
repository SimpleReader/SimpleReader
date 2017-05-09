package com.simplereader.graduation.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simplereader.graduation.model.Article;
import com.simplereader.graduation.util.ImageLoadUtil;
import com.simplereader.simplereader.R;

import java.util.List;

/**
 * Description: 文章阅读适配器
 * Created by chenggong on 2017/5/4.
 */
public class ArticleAdapter extends BaseQuickAdapter<Article> {
    public ArticleAdapter(List<Article> data) {
        super(R.layout.item_article, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Article dataBean) {
        baseViewHolder.setText(R.id.article_title, dataBean.getArticle_title())
                .setText(R.id.text_author, dataBean.getArticle_author())
                .setText(R.id.article_sketch, dataBean.getArticle_sketch());
        ImageLoadUtil.loadCircleImage(mContext,dataBean.getArticle_avatar(), (ImageView) baseViewHolder.getView(R.id.image_head));
        ImageLoadUtil.loadImage(dataBean.getArticle_thumbnail(), (ImageView) baseViewHolder.getView(R.id.image_thum));
    }

}
