package com.donkeyhouse.donkyhouse.bean;

public class Production {
    private int imgid;
    private String leixing1;
    private String leixing;
    private String zongshu;
    private String weizhi;
    private String zhuangtai;

    public Production(int imgid, String leixing1, String leixing, String zongshu, String weizhi, String zhuangtai) {
        this.imgid = imgid;
        this.leixing1 = leixing1;
        this.leixing = leixing;
        this.zongshu = zongshu;
        this.weizhi = weizhi;
        this.zhuangtai = zhuangtai;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getLeixing1() {
        return leixing1;
    }

    public void setLeixing1(String leixing1) {
        this.leixing1 = leixing1;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getZongshu() {
        return zongshu;
    }

    public void setZongshu(String zongshu) {
        this.zongshu = zongshu;
    }

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }
}
