package com.example.android_new.entity;

public class UserInfo {
    private int _id;
    private String username;
    private String mark;
    private String password;
    private int register_type;

    public static UserInfo sUserInfo;

    public static UserInfo getUserInfo() {
        return sUserInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        sUserInfo = userInfo;
    }

    public UserInfo(int _id, String username, String password, int register_type) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.register_type = register_type;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegister_type() {
        return register_type;
    }

    public void setRegister_type(int register_type) {
        this.register_type = register_type;
    }

}
