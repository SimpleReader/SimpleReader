package com.umeng.soexample.theme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.umeng.soexample.theme.ColorUiInterface;
import com.umeng.soexample.theme.util.ViewAttributeUtil;

/**
 * Description:imageview颜色控件
 * Created by chenggong on 2017/5/10.
 */

public class ColorImageView extends ImageView implements ColorUiInterface {
    private int attr_img = -1;
    private int attr_background = -1;

    public ColorImageView(Context context) {
        this(context, null);
    }

    public ColorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            this.attr_img = ViewAttributeUtil.getSrcAttribute(attrs);
            this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
        }
        changeTheme();
    }

    private void changeTheme() {
        if (attr_img != -1) {
            int resource = ViewAttributeUtil.createResource(getResources(), attr_img);
            if (resource == 0) {
                return;
            }
            setImageResource(resource);
        }
        if (attr_background != -1) {
            int resource = ViewAttributeUtil.createResource(getResources(), attr_background);
            if (resource == 0) {
                return;
            }
            setBackgroundResource(resource);
        }
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        changeTheme();
    }
}
