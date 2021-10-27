package com.cookandroid.myapplication;

public class restaurant {

    String name;
    String OpTime;
    String congestion;
    int num;

    public restaurant(String name){
        this.name = name;
    }

    public restaurant(String name, String OpTime, String congestion, int num) {
        this.name = name;
        this.OpTime = OpTime;
        this.congestion = congestion;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpTime() {
        return OpTime;
    }

    public void setOpTime(String opTime) {
        this.OpTime = opTime;
    }

    public String getCongestion() {
        return congestion;
    }

    public void setCongestion(String congestion) {
        this.congestion = congestion;
    }

    public int getNum() {return num; }

    public void setNum(int num) {this.num = num; }
}
