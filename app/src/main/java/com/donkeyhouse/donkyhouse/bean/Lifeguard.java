package com.donkeyhouse.donkyhouse.bean;

/**
 * Created by li619 on 2019/9/17.
 */
public class Lifeguard {
    private long lifeguardId;
    private String name;
    private String lifeguardAccount;
    private String password;
    private String avatar;
    private String detail;
    private int swimmingPoolId;
    private String phone;
    public Lifeguard(String name,String account,String avatar,String password,String sex,int poolid){
        this.avatar="ssss";
        this.name=name;
        this.lifeguardAccount=account;
        this.password=password;
        this.detail=sex;
        this.swimmingPoolId=poolid;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getLifeguardId() {
        return lifeguardId;
    }

    public void setLifeguardId(long lifeguardId) {
        this.lifeguardId = lifeguardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSwimmingPoolId() {
        return swimmingPoolId;
    }

    public void setSwimmingPoolId(int swimmingPoolId) {
        this.swimmingPoolId = swimmingPoolId;
    }

    public String getLifeguardAccount() {
        return lifeguardAccount;
    }

    public void setLifeguardAccount(String lifeguardAccount) {
        this.lifeguardAccount = lifeguardAccount;
    }
}