package com.cookandroid.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import java.io.Serializable;

public class food implements Serializable {
    private int foodImg;
    private String foodName;
    private String foodPrice;
    private String foodInfo;


    public food(int foodImg, String foodName, String foodPrice, String foodInfo) {
        this.foodImg = foodImg;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodInfo = foodInfo;
    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }
}
