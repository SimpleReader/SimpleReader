package com.umeng.soexample.theme.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.theme.ColorUiInterface;
import com.umeng.soexample.theme.util.ViewAttributeUtil;

/**
 * Description:textview颜色控件
 * Created by chenggong on 2017/5/10.
 */

public class ColorTextView extends TextView implements ColorUiInterface {
    private int attr_drawable = -1;
    private int attr_textAppearance = -1;
    private int attr_textColor = -1;

    public ColorTextView(Context context) {
        this(context, null);
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
            this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
            changeBackground();
        }
    }

    private void changeBackground() {
        if (attr_drawable > 0) {
            int resource = ViewAttributeUtil.createResource(getResources(), attr_drawable);
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
        Log.e("COLOR", "id = " + getId());
        changeBackground();
        if (attr_textColor != -1) {
            ViewAttributeUtil.applyTextColor(this, themeId, attr_textColor);
        }
    }
}
