package com.simplereader.graduation.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.simplereader.graduation.base.BaseMvpFragment;
import com.simplereader.graduation.model.Article;
import com.simplereader.graduation.model.ArticleResponse;
import com.simplereader.graduation.presenter.ArticleListPresenter;
import com.simplereader.graduation.ui.activity.ArticleDetailAvtivity;
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
        mArticleAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(getContext(), ArticleDetailAvtivity.class);
                intent.putExtra("article_title",mArticleLists.get(i).getArticle_title());
                intent.putExtra("article_content",mArticleLists.get(i).getArticle_content());
                intent.putExtra("article_id",mArticleLists.get(i).getArticle_id());
                startActivity(intent);
            }
        });
    }

    protected BaseQuickAdapter createAdapter() {
        mArticleAdapter=new ArticleAdapter(mArticleLists);
        return mArticleAdapter;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
    }

    @Override
    public void onGetArticleListSuccess(ArticleResponse response) {
        mArticleLists.clear();
        mArticleLists.addAll(response.getData());
        mArticleAdapter.notifyItemChanged(0,response.getData().size());
    }

    /**
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e("isVisibleToUser----getArticleList:"+isVisibleToUser);
        if(isVisibleToUser){
            Logger.e("-----setUserVisibleHint---getArticleList");
            mvpPresenter.getArticleList();
        }
    }
}
