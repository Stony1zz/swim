package com.donkeyhouse.donkyhouse.bean;

/**
 * Created by li619 on 2019/9/18.
 */

public class login {
    String lifeguardAccount;
    String userAccount;
    String password;
    public login(String name,String password){
        this.userAccount=name;
        this.password=password;
    }
    public String getName() {
        return userAccount;
    }

    public void setName(String name) {
        this.userAccount = name;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
