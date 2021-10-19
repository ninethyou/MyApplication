package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends BaseAdapter {

 private    ArrayList<Basket> items;
 private  Context context;
 private  List<Basket> basketList;

    public BasketAdapter(Context context, ArrayList<Basket> items) {
        this.items = items;
        this.context = context;
    }


    public void addItem(Basket item)
    {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public Basket getItem(int position)
    {
        return items.get(position);
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
            convertView = inflater.inflate(R.layout.basket, parent, false);
        }

        TextView textView_foodNo = (TextView) convertView.findViewById(R.id.textView_BasketfoodNo);
        TextView textView_foodName = (TextView) convertView.findViewById(R.id.textView_BasketfoodName);
        TextView textView_foodPrice = (TextView) convertView.findViewById(R.id.textView_BasketfoodPrice);

        TextView textView_foodCnt = (TextView) convertView.findViewById(R.id.textView_BasketfoodCnt);
        TextView textView_foodTotalPrice = (TextView) convertView.findViewById(R.id.textView_BasketTotalPrice);

        Basket basket = items.get(position);

        textView_foodNo.setText(basket.getFoodNo());
        textView_foodNo.setText(basket.getFoodName());
        textView_foodNo.setText(String.valueOf(basket.getFoodPrice()));
        textView_foodNo.setText(String.valueOf(basket.getFoodCnt()));
        Integer price = basket.getFoodPrice();
        Integer cnt = basket.getFoodCnt();
        Integer sum = price * cnt;
        textView_foodNo.setText(sum.toString());
        return convertView;
    }


    }


