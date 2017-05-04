package com.simplereader.graduation.ui.fragment;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simplereader.graduation.base.BaseFragment;
import com.simplereader.graduation.model.Notice;
import com.simplereader.graduation.theme.util.util.SharedPreferencesMgr;
import com.simplereader.graduation.ui.view.HeaderZoomLayout;
import com.simplereader.graduation.util.ConstanceValue;
import com.simplereader.simplereader.R;

/**
 * Created by gxj on 2017/3/28.
 */

public class MeFragment extends BaseFragment {
    private LinearLayout llNightMode;
    private TextView txtNightMode;
    private ImageView ivBg;
    private HeaderZoomLayout zoomLayout;
    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    protected void bindViews(View view) {
        llNightMode = get(R.id.llNightMode);
        zoomLayout = get(R.id.zoomLayout);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {
        llNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesMgr.getInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT) == ConstanceValue.THEME_LIGHT) {
                    SharedPreferencesMgr.setInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT);
                    getActivity().setTheme(R.style.Theme_Night);
                } else {
                    SharedPreferencesMgr.setInt(ConstanceValue.SP_THEME, ConstanceValue.THEME_LIGHT);
                    getActivity().setTheme(R.style.Theme_Light);
                }
                final View rootView = getActivity().getWindow().getDecorView();
                if (Build.VERSION.SDK_INT >= 14) {
                    rootView.setDrawingCacheEnabled(true);
                    rootView.buildDrawingCache(true);
                    final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                    rootView.setDrawingCacheEnabled(false);
                    if (localBitmap != null && rootView instanceof ViewGroup) {
                        final View localView2 = new View(getActivity());
                        localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
                        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        ((ViewGroup) rootView).addView(localView2, params);
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
    }
}