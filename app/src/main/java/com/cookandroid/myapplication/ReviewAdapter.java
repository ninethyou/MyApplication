package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends BaseAdapter  {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Review> reviewlist;

    public ReviewAdapter(Context context, List<Review> review) {
        this.context = context;
        this.reviewlist = review;
    }

    @Override
    public int getCount() {
        return reviewlist.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.review, parent, false);
        }
        TextView reID = (TextView) convertView.findViewById((R.id.reviewId));
        RatingBar reRate = (RatingBar) convertView.findViewById((R.id.reviewRating));
        TextView review = (TextView) convertView.findViewById((R.id.reviewComment));


        Review reviewItem = reviewlist.get(position);
        reID.setText(reviewItem.getId());
        reRate.setRating(reviewItem.getScore());
        review.setText(reviewItem.getReview());

        return convertView;
    }


}
