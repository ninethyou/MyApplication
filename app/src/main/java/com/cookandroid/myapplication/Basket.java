package com.cookandroid.myapplication;

public class Basket {
    String foodName;
    String foodNo;
    int foodPrice;
    int foodCnt;

    public Basket(String foodName, String foodNo, int foodPrice, int foodCnt) {
        this.foodName = foodName;
        this.foodNo = foodNo;
        this.foodPrice = foodPrice;
        this.foodCnt = foodCnt;
    }

    public int getFoodCnt() {
        return foodCnt;
    }

    public void setFoodCnt(int foodCnt) {
        this.foodCnt = foodCnt;
    }


    public String getFoodName() {
        return foodName;
    }

    public String getFoodNo() {
        return foodNo;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodNo(String foodNo) {
        this.foodNo = foodNo;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }


}
