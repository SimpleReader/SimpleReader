package com.umeng.soexample.ui.activity;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.umeng.soexample.R;
import com.umeng.soexample.model.CommentList;
import com.umeng.soexample.presenter.VideoDetailPresenter;
import com.umeng.soexample.view.IVideoDetailView;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoDetailActivity extends BaseNewsActivity<VideoDetailPresenter> implements IVideoDetailView {

    @BindView(R.id.btnBack)
    public ImageView btnBack;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_video_detail);
        super.loadViewLayout();
    }

    @Override
    protected VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter(this);
    }

    @Override
    protected void onUriLoad(Uri uri) {
        super.onUriLoad(uri);
    }

    @Override
    public void onGetCommentSuccess(CommentList response) {
        getCommentSuccess(response);
    }

    @OnClick(R.id.btnBack)
    public void onBackClick(View view){
        finish();
    }
}
