package com.umeng.soexample.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.listener.ItemDragHelperCallback;
import com.umeng.soexample.listener.OnChannelDragListener;
import com.umeng.soexample.model.Channel;
import com.umeng.soexample.ui.adapter.ChannelAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelActivity extends BaseActivity implements OnChannelDragListener {
    @BindView(R.id.recycleView)
    RecyclerView mRecyclerView;
    @BindView(R.id.icon_collapse)
    ImageView iconCollapse;
    private List<Channel> mDatas = new ArrayList<>();
    private ChannelAdapter mAdapter;
    private String[] titles = new String[]{};
    private ItemTouchHelper mHelper;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_channel);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        titles = getResources().getStringArray(R.array.channel_title);
        generateData();
        mAdapter = new ChannelAdapter(mDatas);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                return itemViewType == Channel.TYPE_MY_CHANNEl || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });
        ItemDragHelperCallback callBack = new ItemDragHelperCallback(this);
        mHelper = new ItemTouchHelper(callBack);
        mAdapter.setOnChannelDragListener(this);
        mHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void setListener() {
        iconCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 生成频道数据
     */
    private void generateData() {
        mDatas.add(new Channel(Channel.TYPE_MY, "我的频道"));
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            mDatas.add(new Channel(Channel.TYPE_MY_CHANNEl, title));
        }
        mDatas.add(new Channel(Channel.TYPE_OTHER, "频道推荐"));
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            mDatas.add(new Channel(Channel.TYPE_OTHER_CHANNEL, title + "推荐"));
        }
    }

    @Override
    public void onStarDrag(BaseViewHolder baseViewHolder) {
        //开始拖动
        Logger.i("开始拖动");
        mHelper.startDrag(baseViewHolder);
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        Channel startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
    }
}