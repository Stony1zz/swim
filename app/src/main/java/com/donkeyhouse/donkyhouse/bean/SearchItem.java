package com.donkeyhouse.donkyhouse.bean;

public class SearchItem {
    private String pici;
    private String zhongliang;
    private String weizhi;
    private String shijian;
    private String leixing;
    private String shichang;

    public SearchItem(String pici, String zhongliang, String weizhi, String shijian, String leixing, String shichang) {
        this.pici = pici;
        this.zhongliang = zhongliang;
        this.weizhi = weizhi;
        this.shijian = shijian;
        this.leixing = leixing;
        this.shichang = shichang;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    public String getZhongliang() {
        return zhongliang;
    }

    public void setZhongliang(String zhongliang) {
        this.zhongliang = zhongliang;
    }

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getShichang() {
        return shichang;
    }

    public void setShichang(String shichang) {
        this.shichang = shichang;
    }
}
