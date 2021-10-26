package com.cookandroid.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import java.io.Serializable;

public class Food implements Serializable {
    private int foodImg;
    private String restaurant;
    private String category;
    private String foodName;
    private int foodPrice;
    private String foodInfo;
    private int foodCount;

    public Food(int foodImg, String restaurant, String category, String foodName, int foodPrice, String foodInfo) {
        this.foodImg = foodImg;
        this.restaurant = restaurant;
        this.category = category;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodInfo = foodInfo;
    }
    public Food(String restaurant, String category, String foodName, int foodPrice, String foodInfo, int foodCount) {
        this.restaurant = restaurant;
        this.category = category;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodInfo = foodInfo;
        this.foodCount = foodCount;
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

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
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

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }
}
