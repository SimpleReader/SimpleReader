package com.umeng.soexample.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.model.CommentList;
import com.umeng.soexample.presenter.NewsDetailPresenter;
import com.umeng.soexample.view.INewsDetailView;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseNewsActivity<NewsDetailPresenter> implements INewsDetailView {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btnShare)
    ImageView btnShare;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_news_detail);
        super.loadViewLayout();
    }

    @OnClick(R.id.btnBack)
    public void onBackClick(View view) {
        finish();
    }

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter(this);
    }

    @Override
    public void onGetCommentSuccess(CommentList response) {
        getCommentSuccess(response);
    }
}
