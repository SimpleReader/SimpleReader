package com.umeng.soexample.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.api.ApiService;
import com.umeng.soexample.api.AppClient;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.BaseCallBack;
import com.umeng.soexample.model.ResponseInfo;
import com.umeng.soexample.ui.view.ClearEditText;
import com.umeng.soexample.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.etxt_username)
    public ClearEditText mUsername;
    @BindView(R.id.etxt_pwd)
    public ClearEditText mPassword;
    @BindView(R.id.etxt_repeat_pwd)
    public ClearEditText mRepeatPwd;
    @BindView(R.id.etxt_phone)
    public ClearEditText mPhoneNumber;
    @BindView(R.id.etxt_nickname)
    public ClearEditText mNickName;
    @BindView(R.id.btnBack)
    public ImageView mBtnBack;
    @BindView(R.id.btn_register)
    public Button mBtnRgister;

    private RequestBody body;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.layout_register);
    }

    @Override
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        //返回上层页面
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //注册
        mBtnRgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUsername.getText().toString().trim();
                String passWord = mPassword.getText().toString().trim();
                String repeatPwd = mRepeatPwd.getText().toString().trim();
                String phoneNumber = mPhoneNumber.getText().toString().trim();
                String nickName = mNickName.getText().toString().trim();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord) || TextUtils.isEmpty(repeatPwd) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(nickName)) {
                    ToastUtils.showToast("资料不允许为空");
                    return;
                }

                if(!isMobileNo(phoneNumber)){
                    ToastUtils.showToast("请输入正确的手机号");
                    return;
                }

                if(userName.length()<6||passWord.length()<6){
                    ToastUtils.showToast("用户名和密码必须六位以上");
                }

                if (!passWord.equals(repeatPwd)) {
                    ToastUtils.showToast("两次输入的密码不一致");
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("username", userName);
                map.put("password", passWord);
                map.put("phone", phoneNumber);
                map.put("nickname", nickName);
                String json = new Gson().toJson(map);
                body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
                addSubscription(AppClient.getApiService(ApiService.HOST_MINE).register(body), new BaseCallBack<ResponseInfo<String>>() {

                    @Override
                    public void onNext(ResponseInfo<String> responseInfo) {
                        if (responseInfo.status.equals("200")) {
                            ToastUtils.showToast("注册成功");
                            finish();
                        }
                    }

                    @Override
                    protected void onError() {
                        super.onError();
                        ToastUtils.showToast("注册失败，请重试");
                    }
                });
            }
        });

    }

    /**
     * 正则匹配验证手机号码
     * @param mobileNo
     * @return
     */
    private boolean isMobileNo(String mobileNo) {
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNo))
            return false;
        else
            return mobileNo.matches(telRegex);
    }
}
