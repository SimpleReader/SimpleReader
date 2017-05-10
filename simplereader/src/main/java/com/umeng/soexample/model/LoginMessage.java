package com.umeng.soexample.model;

/**
 * Description:
 * Created by chenggong on 2017/5/5.
 */

public class LoginMessage {
    private String status;
    private String message;
    private User data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String
    toString() {
        return "LoginMessage{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
