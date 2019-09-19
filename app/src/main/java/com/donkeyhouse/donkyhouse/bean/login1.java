package com.donkeyhouse.donkyhouse.bean;

/**
 * Created by li619 on 2019/9/18.
 */
public class login1 {
    String lifeguardAccount;
    String password;
    public login1(String name,String password){
        this.lifeguardAccount=name;
        this.password=password;
    }
    public String getName() {
        return lifeguardAccount;
    }

    public void setName(String name) {
        this.lifeguardAccount = name;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
