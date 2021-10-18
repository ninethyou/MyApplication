package com.cookandroid.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import java.io.Serializable;

public class Food implements Serializable {
    private int foodImg;
    private String restaurant;
    private String category;
    private String foodName;
    private String foodPrice;
    private String foodInfo;

    public Food(int foodImg, String restaurant, String category, String foodName, String foodPrice, String foodInfo) {
        this.foodImg = foodImg;
        this.restaurant = restaurant;
        this.category = category;
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

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
