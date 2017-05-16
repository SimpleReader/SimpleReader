package com.umeng.soexample.base;

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
import com.umeng.soexample.R;
import com.umeng.soexample.model.Notice;
import com.umeng.soexample.theme.SkinFactory;
import com.umeng.soexample.theme.util.ColorUiUtil;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.util.RxBus;
import com.umeng.soexample.util.ToastUtils;

import java.lang.reflect.Field;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Description:基础Activity
 * Created by chenggong on 2017/4/10.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    //管理Observables和Subscribers
    private CompositeSubscription mCompositeSubscription;
    protected Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //判断sp中的字段是白天还是夜晚，查询不到默认白天
        if (SharedPreferencesMgr.getInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT) == ConstanceValue.THEME_LIGHT) {
            //设置主题为白天模式
            setTheme(R.style.Theme_Light);
        } else {
            //设置主题为夜晚模式
            setTheme(R.style.Theme_Night);
        }
        mSubscription = toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                //收到修改主题的通知
                if (notice.type == ConstanceValue.MSG_CHANGE_THEME) {
                    //更改主题
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
            //利用反射改成false然后在设置上去 否则系统自动setFactroy为true 报错
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
            //退订所有的订阅
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 订阅
     * @param observable
     * @param subscriber
     */
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

    /**
     * 销毁Activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅 以防内存泄漏
        onUnsubscribe();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
