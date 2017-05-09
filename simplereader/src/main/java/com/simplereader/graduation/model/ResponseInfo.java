package com.simplereader.graduation.model;

/**
 * Description:
 * Created by chenggong on 2017/5/5.
 */

public class ResponseInfo<T> {
    public String status;
    public String message;
    public T data;

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
