package com.simplereader.graduation.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.simplereader.graduation.base.BaseMvpFragment;
import com.simplereader.graduation.model.News;
import com.simplereader.graduation.presenter.NewsDetailActivity;
import com.simplereader.graduation.presenter.NewsListPresenter;
import com.simplereader.graduation.ui.activity.VideoDetailActivity;
import com.simplereader.graduation.ui.adapter.NewsAdapter;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.view.INewsListView;
import com.simplereader.simplereader.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends BaseMvpFragment<NewsListPresenter> implements INewsListView {
    @BindView(R.id.recycleView)
    public RecyclerView recyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    private String mTitleCode = "";
    protected List<News> mDatas = new ArrayList<>();
    protected BaseQuickAdapter mAdapter;

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.layout_recycerview, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void bindViews(View view) {

    }

    @Override
    protected void processLogic() {
        initCommonRecyclerView(createAdapter(), null);
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
        srl.measure(0, 0);
        srl.setRefreshing(true);
    }

    protected BaseQuickAdapter createAdapter() {
        return mAdapter = new NewsAdapter(mDatas);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        if (TextUtils.isEmpty(mTitleCode)) {
            mTitleCode = getArguments().getString(ConstanceValue.DATA);
        }
        mvpPresenter.getNewsList(mTitleCode);
    }

    @Override
    protected void setListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mvpPresenter.getNewsList(mTitleCode);
            }
        });
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                News news = mDatas.get(i);
                if (TextUtils.equals(news.article_genre, ConstanceValue.ARTICLE_GENRE_VIDEO)) {
                    //视频
                    Intent intent = new Intent(mContext, VideoDetailActivity.class);
                    intent.putExtra(ConstanceValue.URL, "http://m.toutiao.com".concat(news.source_url));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra(ConstanceValue.URL, "http://m.toutiao.com".concat(news.source_url));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onGetNewsListSuccess(List<News> response) {
        if (response.size() > 0) {
            response.remove(response.size() - 1);
        }
        srl.setRefreshing(false);
        mDatas.addAll(0, response);
        mAdapter.notifyItemRangeChanged(0, response.size());
    }
}