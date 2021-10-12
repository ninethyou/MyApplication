package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class foodAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<food> layoutInflater;
    private List<food> foodList;


    public foodAdapter(Context context, ArrayList<food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
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
            convertView = inflater.inflate(R.layout.food,parent, false);
        }

        ImageView foodImg = (ImageView) convertView.findViewById(R.id.imageViewFoodImg);
        TextView foodName = (TextView) convertView.findViewById(R.id.textViewFoodName);
        TextView foodPrice = (TextView) convertView.findViewById(R.id.textViewFoodPrice);
        TextView foodDescription = (TextView) convertView.findViewById(R.id.textViewFoodDescrip);

        food fooditem = foodList.get(position);

        foodImg.setImageResource(fooditem.getFoodImg());
        foodName.setText(fooditem.getFoodName());
        foodPrice.setText(fooditem.getFoodPrice());
        foodDescription.setText(fooditem.getFoodInfo());

        return convertView;
    }
}
