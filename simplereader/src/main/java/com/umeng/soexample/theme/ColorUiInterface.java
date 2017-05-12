package com.umeng.soexample.theme;

import android.content.res.Resources;
import android.view.View;

/**
 * Description:换肤接口
 * Created by chenggong on 2017/4/7.
 */

public interface ColorUiInterface {
    View getView();

    void setTheme(Resources.Theme themeId);
}
