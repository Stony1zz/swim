package com.donkeyhouse.donkyhouse.bean;

/**
 * 游泳馆
 */
public class SwimmingPool {
    private long swimmingPoolId;
    private String name;
    private String detail;
    private String address;
    private String phone;
    public SwimmingPool(String name, String address, String detail) {
        this.swimmingPoolId =swimmingPoolId;
        this.name = name;
        this.detail = detail;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public long getSwimmingPoolId() {
        return swimmingPoolId;
    }

    public void setSwimmingPoolId(long swimmingPoolId) {
        this.swimmingPoolId = swimmingPoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
