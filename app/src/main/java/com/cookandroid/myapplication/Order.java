package com.cookandroid.myapplication;

public class Order {
    String orderNo;
    String orderFood;
    String orderTime;
    String orderPrice;
    String orderCount;
    String orderState;


    public Order(String orderNo, String orderFood, String orderTime, String orderPrice, String orderCount, String orderState) {
        this.orderNo = orderNo;
        this.orderFood = orderFood;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
        this.orderState = orderState;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderFood() {
        return orderFood;
    }

    public void setOrderFood(String orderFood) {
        this.orderFood = orderFood;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderCount() { return orderCount; }

    public void setOrderCount(String orderCount) { this.orderCount = orderCount; }

    public String getOrderState() { return orderState; }

    public void setOrderState(String orderState) { this.orderState = orderState; }
}
