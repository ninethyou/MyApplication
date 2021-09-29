package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookandroid.myapplication.R;
import com.cookandroid.myapplication.basket;

import java.util.List;

public class basketAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public basketAdapter(Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
    }
    private List<basket> basketList;

    @Override
    public int getCount() {
        return basketList.size();
    }

    @Override
    public Object getItem(int position) {
        return basketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.basket,null);

        TextView foodNo = (TextView) v.findViewById(R.id.textView_BasketfoodNo);
        TextView foodName = (TextView) v.findViewById(R.id.textView_BasketfoodName);
        TextView foodPrice = (TextView) v.findViewById(R.id.textView_BasketfoodPrice);

        TextView foodCnt = (TextView) v.findViewById(R.id.textView_BasketfoodCnt);
        TextView BasketTotalPrice = (TextView) v.findViewById(R.id.textView_BasketTotalPrice);

        foodNo.setText(basketList.get(position).getFoodNo());
        foodName.setText(basketList.get(position).getFoodName());
        foodPrice.setText(basketList.get(position).getFoodPrice());

        foodCnt.setText(basketList.get(position).getFoodCnt());
        Integer total = basketList.get(position).getFoodCnt()  * basketList.get(position).getFoodPrice();
        BasketTotalPrice.setText(total.toString());

        return v;

    }
}
