package com.cookandroid.myapplication;

public class category {

    String name;
    String OpTime;
    String congestion;

    public category(String name){
        this.name = name;
    }

    public category(String name, String OpTime, String congestion) {
        this.name = name;
        this.OpTime = OpTime;
        this.congestion = congestion;
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
}
