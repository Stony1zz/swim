package com.donkeyhouse.donkyhouse.bean;

/**
 * Created by li619 on 2019/9/19.
 */

public class NatatoriumHistory {
    private long natatoriumDataId;
    private long natatoriumId;
    private String sensorId;
    private String time;
    private String waterTemperature;
    private String airHumidity;

    public NatatoriumHistory(long natatoriumId, long natatoriumDataId, String sensorId, String time, String waterTemperature, String airHumidity) {
        this.natatoriumId = natatoriumId;
        this.natatoriumDataId = natatoriumDataId;
        this.sensorId = sensorId;
        this.time = time;
        this.waterTemperature = waterTemperature;
        this.airHumidity = airHumidity;
    }
    public long getNatatoriumDataId() {
        return natatoriumDataId;
    }

    public void setNatatoriumDataId(long natatoriumDataId) {
        this.natatoriumDataId = natatoriumDataId;
    }

    public long getNatatoriumId() {
        return natatoriumId;
    }

    public void setNatatoriumId(long natatoriumId) {
        this.natatoriumId = natatoriumId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(String airHumidity) {
        this.airHumidity = airHumidity;
    }

}
