package com.umeng.soexample.ui.activity;

import android.net.Uri;

import com.umeng.soexample.R;
import com.umeng.soexample.model.CommentList;
import com.umeng.soexample.presenter.VideoDetailPresenter;
import com.umeng.soexample.view.IVideoDetailView;

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
