package com.donkeyhouse.donkyhouse.bean;

/**
 * Created by li619 on 2019/9/19.
 */

public class UserHistory {
    private long userDataId;
    private long userId;
    private String sensorId;
    private String waterPressure;
    private String RFIDInfo;
    private String time;


    public UserHistory(long userDataId, long userId, String sensorId, String waterPressure, String RFIDInfo, String time) {
        this.userDataId = userDataId;
        this.userId = userId;
        this.sensorId = sensorId;
        this.waterPressure = waterPressure;
        this.RFIDInfo = RFIDInfo;
        this.time = time;
    }

    public long getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(long userDataId) {
        this.userDataId = userDataId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getWaterPressure() {
        return waterPressure;
    }

    public void setWaterPressure(String waterPressure) {
        this.waterPressure = waterPressure;
    }

    public String getRFIDInfo() {
        return RFIDInfo;
    }

    public void setRFIDInfo(String RFIDInfo) {
        this.RFIDInfo = RFIDInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
