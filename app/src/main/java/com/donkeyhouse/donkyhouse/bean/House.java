package com.donkeyhouse.donkyhouse.bean;

public class House {
    private String house_num;
    private String house_shuliang;
    private String house_wendu;
    private String house_shidu;
    private String house_nongdu;

    public House(String house_num, String house_shuliang, String house_wendu, String house_shidu, String house_nongdu) {
        this.house_num = house_num;
        this.house_shuliang = house_shuliang;
        this.house_wendu = house_wendu;
        this.house_shidu = house_shidu;
        this.house_nongdu = house_nongdu;
    }
    public String getHouse_num() {
        return house_num;
    }

    public void setHouse_num(String house_num) {
        this.house_num = house_num;
    }

    public String getHouse_shuliang() {
        return house_shuliang;
    }

    public void setHouse_shuliang(String house_shuliang) {
        this.house_shuliang = house_shuliang;
    }

    public String getHouse_wendu() {
        return house_wendu;
    }

    public void setHouse_wendu(String house_wendu) {
        this.house_wendu = house_wendu;
    }

    public String getHouse_shidu() {
        return house_shidu;
    }

    public void setHouse_shidu(String house_shidu) {
        this.house_shidu = house_shidu;
    }

    public String getHouse_nongdu() {
        return house_nongdu;
    }

    public void setHouse_nongdu(String house_nongdu) {
        this.house_nongdu = house_nongdu;
    }
}
