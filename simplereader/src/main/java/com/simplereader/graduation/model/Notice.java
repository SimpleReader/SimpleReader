package com.simplereader.graduation.model;

/**
 * Created by gxj on 2017/3/28.
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
