package com.umeng.soexample.theme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;

import com.umeng.soexample.theme.ColorUiInterface;
import com.umeng.soexample.theme.util.ViewAttributeUtil;

/**
 * Description:
 * Created by chenggong on 2017/5/14.
 */

public class ColorCardView extends CardView implements ColorUiInterface {
    private int attr_background = -1;

    public ColorCardView(Context context) {
        super(context);
    }

    public ColorCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ColorCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
    }

}
