package com.cookandroid.myapplication;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Prefer implements Comparable<Prefer>{
    String foodName;
    float score;
    String rest;

    public Prefer(String foodName, float score, String rest) {
        this.foodName = foodName;
        this.score = score;
        this.rest = rest;
    }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public float getScore() { return score; }
    public void setScore(float score) { this.score = score; }

    public String getRest() { return rest; }
    public void setRest(String rest) { this.rest = rest; }

    @Override
    public int compareTo(Prefer p) {
        if(this.score<p.score){
            return 1;
        }else if(this.score == p.score){
            return 0;
        }else{
            return -1;
        }
    }
}