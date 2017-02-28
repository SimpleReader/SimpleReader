package com.simplereader.graduation.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.simplereader.graduation.base.BaseActivity;
import com.simplereader.graduation.bean.Gank;
import com.simplereader.graduation.mvp.contract.MainContract;
import com.simplereader.graduation.mvp.presenter.MainPresenter;
import com.simplereader.simplereader.R;

import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog mDialog;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTextView = (TextView) findViewById(R.id.tv);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCancelable(false);
        mDialog.setMessage("正在加载...");

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getGank();
            }
        });
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void onSucceed(Gank data) {

        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
        List<Gank.Result> results = data.getResults();
        mTextView.setText(results.get(new Random().nextInt(10)).toString());

        for (Gank.Result result : results) {
            Log.e(TAG, result.toString());
        }
    }

    @Override
    public void onFail(String err) {
        Log.e(TAG, err);
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideDialog() {
        mDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}