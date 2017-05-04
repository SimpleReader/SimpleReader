package com.simplereader.graduation.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simplereader.graduation.model.Article;
import com.simplereader.simplereader.R;

import java.util.List;

/**Description: 文章阅读适配器
 * Created by chenggong on 2017/5/4.
 */

public class ArticleAdapter extends BaseQuickAdapter<Article> {

    public ArticleAdapter(List<Article> data) {
        super(R.layout.item_article, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Article article) {
        baseViewHolder.setText(R.id.article_title,article.articleTitle);
    }
}
