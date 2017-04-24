package com.simplereader.graduation.ui.activity;

import android.net.Uri;

import com.simplereader.graduation.model.CommentList;
import com.simplereader.graduation.presenter.VideoDetailPresenter;
import com.simplereader.graduation.view.IVideoDetailView;
import com.simplereader.simplereader.R;

public class VideoDetailActivity extends BaseNewsActivity<VideoDetailPresenter> implements IVideoDetailView {

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
}
