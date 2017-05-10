package com.umeng.soexample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseMVPActivity;
import com.umeng.soexample.model.ArticleFavour;
import com.umeng.soexample.model.User;
import com.umeng.soexample.presenter.ArticleFavourListPresenter;
import com.umeng.soexample.theme.util.util.SharedPreferencesMgr;
import com.umeng.soexample.ui.adapter.ArticleFavourAdapter;
import com.umeng.soexample.ui.adapter.layoutmanager.LinearLayoutManagerWrapper;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.view.IArticleFavourView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleFavourActivity extends BaseMVPActivity<ArticleFavourListPresenter> implements IArticleFavourView{

    @BindView(R.id.recycleView_article)
    public RecyclerView mRecyclerView;

    private ArticleFavourAdapter mArticleAdapter;
    private List<ArticleFavour.DataBean> mArticleLists=new ArrayList<>();

    @Override
    protected ArticleFavourListPresenter createPresenter() {
        return new ArticleFavourListPresenter(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.fragment_article);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String json= SharedPreferencesMgr.getString(ConstanceValue.SP_USER,"");
        if(!TextUtils.isEmpty(json)){
            Gson mGson=new Gson();
            User user=mGson.fromJson(json,User.class);
            mvpPresenter.getArticleFavourList(user.getUsername());
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(createAdapter());
    }

    @Override
    protected void setListener() {
        mArticleAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ArticleFavourActivity.this,ArticleDetailAvtivity.class);
                intent.putExtra("article_title",mArticleLists.get(i).getArticle().getArticle_title());
                intent.putExtra("article_content",mArticleLists.get(i).getArticle().getArticle_content());
                intent.putExtra("article_id",mArticleLists.get(i).getArticle_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetArticleFavoursSuccess(ArticleFavour response) {
        mArticleLists.clear();
        mArticleLists.addAll(response.getData());
        mArticleAdapter.notifyItemChanged(0,response.getData().size());
    }

    protected BaseQuickAdapter createAdapter() {
        mArticleAdapter=new ArticleFavourAdapter(mArticleLists);
        return mArticleAdapter;
    }
}
