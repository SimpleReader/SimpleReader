package com.simplereader.graduation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.simplereader.graduation.api.ApiService;
import com.simplereader.graduation.api.AppClient;
import com.simplereader.graduation.base.BaseActivity;
import com.simplereader.graduation.base.BaseCallBack;
import com.simplereader.graduation.model.ResponseInfo;
import com.simplereader.graduation.model.User;
import com.simplereader.graduation.theme.util.util.SharedPreferencesMgr;
import com.simplereader.graduation.ui.view.ProgressWebView;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.graduation.util.LoginStatusUtils;
import com.simplereader.graduation.util.ToastUtils;
import com.simplereader.simplereader.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;

public class ArticleDetailAvtivity extends BaseActivity {
    @BindView(R.id.btnBack)
    public ImageView btnBack;
    @BindView(R.id.btnShare)
    public ImageView btnShare;
    @BindView(R.id.title)
    public TextView textTitle;
    @BindView(R.id.btnCollect)
    public ImageView btnCollect;
    @BindView(R.id.web_article)
    public ProgressWebView webView;

    private String articleId;
    private boolean isCollected=false; //默认没有收藏
    private RequestBody body;
    private LoginStatusUtils mInstance;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.layout_article_detail);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mInstance=LoginStatusUtils.getInstance();
        Intent intent =getIntent();
        String articleTitle=intent.getStringExtra("article_title");
        textTitle.setText(articleTitle);
        String articleContent=intent.getStringExtra("article_content");
        articleId=intent.getStringExtra("article_id");
        //如果已经登录
        if(mInstance.isLoginStatus()){
            String userData= SharedPreferencesMgr.getString(ConstanceValue.SP_USER,"");
            User user=new Gson().fromJson(userData,User.class);
            Map<String,String> map=new HashMap<>();
            map.put("username",user.getUsername());
            map.put("article_id",articleId);
            String json=new Gson().toJson(map);
            body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
            addSubscription(AppClient.getApiService(ApiService.HOST_MINE).isCollected(body), new BaseCallBack<ResponseInfo<String>>() {

                @Override
                public void onNext(ResponseInfo<String> responseInfo) {
                    if(responseInfo.status.equals("200")){
                        isCollected=true;
                        btnCollect.setImageResource(R.drawable.icon_collected_black);
                    }
                }
            });
        }

        webView.loadUrl(articleContent);
    }

    @Override
    protected void setListener() {
        //返回上层页面
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //分享
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArticleDetailAvtivity.this, "点击分享", Toast.LENGTH_SHORT).show();
            }
        });
        //收藏按钮
        btnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInstance.isLoginStatus()){
                    if(isCollected){ //如果已经收藏过
                        addSubscription(AppClient.getApiService(ApiService.HOST_MINE).cancelCollect(body), new BaseCallBack<ResponseInfo<String>>() {

                            @Override
                            public void onNext(ResponseInfo<String> response) {
                                if(response.status.equals("200")){
                                    btnCollect.setImageResource(R.drawable.icon_uncollect_black);
                                    isCollected=false;
                                    Toast.makeText(mContext, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        String json=SharedPreferencesMgr.getString(ConstanceValue.SP_USER,"");
                        if(!TextUtils.isEmpty(json)){
                            User user=new Gson().fromJson(json,User.class);
                            addSubscription(AppClient.getApiService(ApiService.HOST_MINE).collectArticle(user.getUsername(), articleId), new BaseCallBack<ResponseInfo<String>>() {

                                @Override
                                public void onNext(ResponseInfo<String> response) {
                                    if(response.status.equals("200")){
                                        btnCollect.setImageResource(R.drawable.icon_collected_black);
                                        isCollected=true;
                                        Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }
                }else{
                    ToastUtils.showToast("请先登录帐号");
                }



            }
        });

    }




}
