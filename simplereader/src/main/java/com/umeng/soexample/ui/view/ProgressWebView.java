package com.umeng.soexample.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.umeng.soexample.R;
import com.umeng.soexample.util.NetworkUtil;

/**
 * Description:自定义webview
 * Created by chenggong on 2017/4/24.
 */

public class ProgressWebView extends WebView {
    private ProgressBar mProgressBar;
    private OnHtmlEventListener onHtmlEventListener;

    public void setOnHtmlEventListener(OnHtmlEventListener onHtmlEventListener) {
        this.onHtmlEventListener = onHtmlEventListener;
    }

    public interface OnHtmlEventListener {
        void onFinished(String html);

        void onUriLoading(Uri uri);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
        //为webview设置一个处理器
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                boolean isShould;
                //检查url是否合法
                if (NetworkUtil.isNetworkUrl(getUrl())) {
                    view.loadUrl(getUrl());
                    isShould = true;
                } else {
                    if (onHtmlEventListener != null) {
                        onHtmlEventListener.onUriLoading(Uri.parse(getUrl()));
                    }
                    isShould = false;
                }
                return isShould;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                super.onPageFinished(view, url);
            }
        });
        setWebViewClient(new WebViewClient());
        WebSettings settings = getSettings();
        //设置webview支持js
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        //在网页中增加了一个local_obj的类
        addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
    }

    class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            if (onHtmlEventListener != null) {
                onHtmlEventListener.onFinished(html);
            }
        }
    }

    public class WebChormeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE) {
                    mProgressBar.setVisibility(VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
