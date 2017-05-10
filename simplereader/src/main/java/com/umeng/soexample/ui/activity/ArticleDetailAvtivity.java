package com.umeng.soexample.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.soexample.R;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.model.ResponseInfo;
import com.umeng.soexample.model.User;
import com.umeng.soexample.theme.util.util.SharedPreferencesMgr;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.util.LoginStatusUtils;
import com.umeng.soexample.util.ToastUtils;

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
    public WebView webView;

    private String articleId;
    private boolean isCollected=false; //默认没有收藏
    private RequestBody body;
    private LoginStatusUtils mInstance;
    private String articleContent;
    private String articleThumb;
    private String articleTitle;
    private String textContent; //文章的文本内容

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
        articleTitle=intent.getStringExtra("article_title");
        textTitle.setText(articleTitle);
        articleContent=intent.getStringExtra("article_content");
        articleId=intent.getStringExtra("article_id");
        articleThumb=intent.getStringExtra("article_thumb");

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


        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        webView.setWebViewClient(new CustomWebViewClient());
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
                //设置分享链接
                UMImage thumb =  new UMImage(ArticleDetailAvtivity.this, R.mipmap.app_icon);
                UMWeb web=new UMWeb(articleContent);
                web.setTitle(articleTitle); //设置分享标题
                web.setThumb(thumb); //设置缩略图 新浪微博必须设置
                web.setDescription("美文");
                new ShareAction(ArticleDetailAvtivity.this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
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

    //社会化分享的监听回调
    UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showToast("分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showToast("分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showToast("分享取消");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    //继承webview
    final class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:window.java_obj.getSource('<head>'+" +
                    "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            super.onPageFinished(view, url);
        }
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            //Logger.d("html=", html);
            int start=html.indexOf("<div class=\"content\" data-note-content=\"\">");
            int end=html.indexOf("<!-- share 数据 -->");
            String string = html.substring(start,end);
            //去掉img标签
            textContent=string.replaceAll("<img[:a-z0-9\"\\s=\\/%?\\._A-Z-]+>","")
                    //去掉div标签
                    .replaceAll("<div class=\"[;&a-z-\\s_]+\">","")
                    //去掉所有的结束标签
                    .replaceAll("<[\\/]?[a-z]+>","")
                    //去掉空格和p标签
                    .replaceAll("(.*)[小刃刃]+<\\/b><\\/p>","")
                    //去掉class
                    .replaceAll("<div\\sclass=\"content\"\\sdata-note-content=\"\">","");
        }
    }

}
