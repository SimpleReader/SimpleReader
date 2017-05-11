package com.umeng.soexample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.R;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.model.LoginMessage;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.ui.view.ClearEditText;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.util.LoginStatusUtils;
import com.umeng.soexample.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.etxt_username)
    public ClearEditText extUsername;
    @BindView(R.id.etxt_pwd)
    public ClearEditText extPassword;
    @BindView(R.id.btn_login)
    public Button btnLogin;
    @BindView(R.id.btnBack)
    public ImageView btnBack;

    @BindView(R.id.txt_toReg)
    public TextView btnRegister;

    private LoginStatusUtils mInstance;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.layout_login);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mInstance = LoginStatusUtils.getInstance();
    }

    @Override
    protected void setListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=extUsername.getText().toString().trim();
                String password=extPassword.getText().toString().trim();
                addSubscription(AppClient.getApiService(ApiService.URL_LOGIN).loginByUsername(username, password), new BaseCallBack<LoginMessage>() {

                    @Override
                    public void onNext(LoginMessage response) {
                        Logger.e("response:" + response.getData().toString());
                        if (response.getStatus().equals("200")) {
                            SharedPreferencesMgr.setString(ConstanceValue.SP_USER, mInstance.user2Json(response.getData()));
                            finish();
                        }
                    }
                    @Override
                    protected void onError() {
                        ToastUtils.showToast("用户登录失败");
                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
