package com.simplereader.graduation.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.simplereader.graduation.model.Notice;
import com.simplereader.graduation.theme.util.SkinFactory;
import com.simplereader.graduation.theme.util.util.ColorUiUtil;
import com.simplereader.graduation.theme.util.util.SharedPreferencesMgr;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.util.RxBus;
import com.simplereader.graduation.util.ToastUtils;
import com.simplereader.simplereader.R;

import java.lang.reflect.Field;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gxj on 2017/4/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private CompositeSubscription mCompositeSubscription;
    protected Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (SharedPreferencesMgr.getInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT) == ConstanceValue.THEME_LIGHT) {
            setTheme(R.style.Theme_Light);
        } else {
            setTheme(R.style.Theme_Night);
        }
        mSubscription = toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_CHANGE_THEME) {
                    ColorUiUtil.changeTheme(getWindow().getDecorView(), getTheme());
                }
            }
        });
        setLayoutInflaterFactory();
        initView(savedInstanceState);
    }

    private void setLayoutInflaterFactory() {
        LayoutInflater layoutInflater = getLayoutInflater();
        try {
            Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
            mFactorySet.setAccessible(true);
            mFactorySet.set(layoutInflater, false);
            LayoutInflaterCompat.setFactory(layoutInflater, new SkinFactory(this));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化界面
     */
    protected void initView(Bundle savedInstanceState) {
        loadViewLayout();
        bindViews();
        processLogic(savedInstanceState);
        setListener();
    }

    protected void showLog(String log) {
        Logger.e(log);
    }

    /**
     * 获取控件
     *
     * @param id  控件的id
     * @param <E>
     * @return
     */
    protected <E extends View> E get(int id) {
        return (E) findViewById(id);
    }

    /**
     * 加载布局
     */
    protected abstract void loadViewLayout();

    /**
     * find控件
     */
    protected abstract void bindViews();

    /**
     * 处理数据
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 界面跳转
     *
     * @param tarActivity
     */
    protected void initent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mContext, tarActivity);
        startActivity(intent);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }

    /**
     * RXjava取消注册，以避免内存泄露
     */
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    /**
     * 注册事件通知
     */
    public Observable<Notice> toObservable() {
        return RxBus.getDefault().toObservable(Notice.class);
    }

    /**
     * 发送消息
     */
    public void post(Notice msg) {
        RxBus.getDefault().post(msg);
    }

    public RecyclerView initCommonRecyclerView(BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        return initCommonRecyclerView(recyclerView, adapter, decoration);
    }

    public RecyclerView initCommonRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initHorizontalRecyclerView(BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initGridRecyclerView(int resId, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, int spanCount) {
        RecyclerView recyclerView = (RecyclerView) findViewById(resId == 0 ? R.id.recycleView : resId);
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initGridRecyclerView(BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, int spanCount) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
