package com.cookandroid.myapplication;

public class Review {

    String id;
    String food;
    String rest;
    String cat;
    String review;
    int score;

    public Review(String id, String review, int score) {
        this.id = id;
        this.review = review;
        this.score = score;
    }

    public Review(String id, String food, String rest, String cat, String review, int score) {
        this.id = id;
        this.food = food;
        this.rest = rest;
        this.cat = cat;
        this.review = review;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFood() { return food; }
    public void setFood(String food) { this.food = food; }

    public String getRest() { return rest;}
    public void setRest(String rest) { this.rest = rest; }

    public String getCat() { return cat; }
    public void setCat(String cat) { this.cat = cat; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
