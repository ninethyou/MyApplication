package com.cookandroid.myapplication;

public class Order {
    String orderNo;
    String orderDate;
    String orderTime;
    String orderPrice;

    public Order(String orderNo, String orderDate, String orderTime, String orderPrice) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
}
