package com.cookandroid.myapplication;

public class receipt {

    String  receiptNo;
    String FoodName;
    String FoodCnt;
    String Price;

    public receipt(String receiptNo, String foodName, String foodCnt, String price) {
        this.receiptNo = receiptNo;
        FoodName = foodName;
        FoodCnt = foodCnt;
        Price = price;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodCnt() {
        return FoodCnt;
    }

    public void setFoodCnt(String foodCnt) {
        FoodCnt = foodCnt;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}

