package com.simplereader.graduation.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.simplereader.graduation.base.BaseMvpFragment;
import com.simplereader.graduation.model.Article;
import com.simplereader.graduation.presenter.ArticleListPresenter;
import com.simplereader.graduation.ui.adapter.ArticleAdapter;
import com.simplereader.graduation.view.IArticleView;
import com.simplereader.simplereader.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleFragment extends BaseMvpFragment<ArticleListPresenter> implements IArticleView {
    @BindView(R.id.recycleView_article)
    public RecyclerView mRecyclerView;

    private ArticleAdapter mArticleAdapter;
    private List<Article> mArticleLists = new ArrayList<>();

    @Override
    protected ArticleListPresenter createPresenter() {
        return new ArticleListPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_article,null);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    protected void bindViews(View view) {
    }

    @Override
    protected void processLogic() {
        initArticleRecyclerView(createAdapter(),null);
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void onGetArticleListSuccess(List<Article> response) {
        mArticleLists=response;
    }

    protected BaseQuickAdapter createAdapter() {
        /*Article article=new Article();
        article.articleId="1";
        article.articleTitle="222有什么可以保持每天好心情的方法？";
        article.articleAuthor="123";
        article.articleAvatar="http://upload.jianshu.io/users/upload_avatars/253140/b9adfdadef8a?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        mArticleLists.add(article);*/
        return mArticleAdapter = new ArticleAdapter(mArticleLists);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mvpPresenter.getArticleList();
    }
}
