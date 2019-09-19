package com.donkeyhouse.donkyhouse.bean;

/**
 * Created by li619 on 2019/9/17.
 * 指定的游泳池
 */

public class Natatorium {
    public Natatorium(long natatoriumId,String time, String name, float waterLevel, String swimmingPoolId, String detail) {
        this.natatoriumId = natatoriumId;
        this.time=time;
        this.name = name;
        this.waterLevel = waterLevel;
        this.swimmingPoolId = swimmingPoolId;
        this.detail = detail;
    }

    private  long natatoriumId;
    private  String name;



    private  String time;

    private  float waterLevel;
    private String swimmingPoolId;
    private String detail;


    public long getNatatoriumId() {
        return natatoriumId;
    }

    public void setNatatoriumId(long natatoriumId) {
        this.natatoriumId = natatoriumId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(float waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getSwimmingPoolId() {
        return swimmingPoolId;
    }

    public void setSwimmingPoolId(String swimmingPoolId) {
        this.swimmingPoolId = swimmingPoolId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Natatorium{" +
                "natatoriumId=" + natatoriumId +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", waterLevel=" + waterLevel +
                ", swimmingPoolId='" + swimmingPoolId + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}