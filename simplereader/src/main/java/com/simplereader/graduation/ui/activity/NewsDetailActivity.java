package com.simplereader.graduation.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplereader.graduation.model.CommentList;
import com.simplereader.graduation.presenter.NewsDetailPresenter;
import com.simplereader.graduation.view.INewsDetailView;
import com.simplereader.simplereader.R;

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
