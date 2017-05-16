package com.umeng.soexample.ui.fragment;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.model.Notice;
import com.umeng.soexample.model.User;
import com.umeng.soexample.theme.util.SharedPreferencesMgr;
import com.umeng.soexample.ui.activity.ArticleFavourActivity;
import com.umeng.soexample.ui.activity.LoginActivity;
import com.umeng.soexample.ui.view.HeaderZoomLayout;
import com.umeng.soexample.util.ConstanceValue;
import com.umeng.soexample.util.LoginStatusUtils;

/**
 * Description:我的页面
 * Created by chenggong on 2017/3/28.
 */

public class MeFragment extends BaseFragment {
    private LinearLayout llNightMode;
    private TextView txtNightMode;
    private ImageView ivBg;
    private HeaderZoomLayout zoomLayout;
    private ImageView btnRegister;
    private LinearLayout mLayoutUnlogin; //未登录
    private LinearLayout mLayoutlogin; //登录
    private TextView mUsername; //用户昵称
    private LoginStatusUtils mInstance; //登录状态工具类
    private Button btnLogout; //退出登录按钮
    private LinearLayout mLayoutCollect;
    private TextView mine_fragment_mode_switch;

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    protected void bindViews(View view) {
        llNightMode = get(R.id.llNightMode);
        zoomLayout = get(R.id.zoomLayout);
        btnRegister=get(R.id.img_register);
        mLayoutUnlogin=get(R.id.layout_login);
        mLayoutlogin=get(R.id.layout_user_info);
        mUsername=get(R.id.txtUserName);
        btnLogout=get(R.id.btnLogout);
        mLayoutCollect=get(R.id.ll_favorite);
        mine_fragment_mode_switch = get(R.id.mine_fragment_mode_switch);
    }

    @Override
    protected void processLogic() {
        mInstance=LoginStatusUtils.getInstance();
    }

    @Override
    protected void setListener() {
        //设置点击夜间模式的监听事件
        llNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesMgr.getInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT) == ConstanceValue.THEME_LIGHT) {
                    SharedPreferencesMgr.setInt(ConstanceValue.SP_THEME, ConstanceValue.TIME_NIGHT);
                    getActivity().setTheme(R.style.Theme_Night);
                    mine_fragment_mode_switch.setText("夜间");
                } else {
                    SharedPreferencesMgr.setInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT);
                    getActivity().setTheme(R.style.Theme_Light);
                    mine_fragment_mode_switch.setText("白天");
                }
                //获取最顶层的view，也就是根view
                final View rootView = getActivity().getWindow().getDecorView();
                if (Build.VERSION.SDK_INT >= 14) {
                    rootView.setDrawingCacheEnabled(true);
                    rootView.buildDrawingCache(true);
                    //截取view图像
                    final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                    rootView.setDrawingCacheEnabled(false);
                    if (localBitmap != null && rootView instanceof ViewGroup) {
                        final View localView2 = new View(getActivity());
                        localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
                        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        ((ViewGroup) rootView).addView(localView2, params);
                        //设置切换时候动画效果
                        localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                post(new Notice(ConstanceValue.MSG_CHANGE_THEME));
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                ((ViewGroup) rootView).removeView(localView2);
                                localBitmap.recycle();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).start();
                    } else {
                        post(new Notice(ConstanceValue.MSG_CHANGE_THEME));
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInstance.clearData();
                    mLayoutlogin.setVisibility(View.GONE);
                    mLayoutUnlogin.setVisibility(View.VISIBLE);
            }
        });

        mLayoutCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mInstance.isLoginStatus()){
                    Intent intent=new Intent(mContext, ArticleFavourActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
            String json=SharedPreferencesMgr.getString(ConstanceValue.SP_USER,"");
            if(!TextUtils.isEmpty(json)){
                mLayoutUnlogin.setVisibility(View.GONE);
                mLayoutlogin.setVisibility(View.VISIBLE);
                mLayoutlogin.setGravity(Gravity.CENTER);
                User user=mInstance.json2User(json);
                if(user!=null){
                    mUsername.setText(user.getNickname());
                }
        }

    }
}