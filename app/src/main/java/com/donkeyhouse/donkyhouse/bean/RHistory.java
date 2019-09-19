package com.donkeyhouse.donkyhouse.bean;

public class RHistory {
    private String picihao;
    private String donkeyId;
    private String rfid;
    private String size;
    private String time;
    private String pid;

    public RHistory(String picihao, String donkeyId, String rfid, String size, String time, String pid) {
        this.picihao = picihao;
        this.donkeyId = donkeyId;
        this.rfid = rfid;
        this.size = size;
        this.time = time;
        this.pid = pid;
    }

    public String getPicihao() {
        return picihao;
    }

    public void setPicihao(String picihao) {
        this.picihao = picihao;
    }

    public String getDonkeyId() {
        return donkeyId;
    }

    public void setDonkeyId(String donkeyId) {
        this.donkeyId = donkeyId;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
