package com.umeng.soexample.model;

/**
 * Created by chenggong on 2017/3/28.
 */

public class Notice {
    public int type;
    public Object content;

    public Notice() {
    }

    public Notice(int type) {
        this.type = type;
    }

    public Notice(Object content, int type) {
        this.content = content;
        this.type = type;
    }
}
