package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter
{

    private  Context context;
    private ArrayList<Order> items = new ArrayList<Order>();

    public OrderAdapter(Context context, ArrayList<Order> items) {
        this.context = context;
        this.items = items;
    }

    public void addItem(Order item)
    {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();

    }

    public Order getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItem(int position,Order item)
    {
        items.set(position,item);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_item, parent, false);
        }

            TextView orderNo = convertView.findViewById(R.id.textView_orderNo_item);
            TextView orderDate = convertView.findViewById(R.id.textView_orderDate_item);
            TextView orderTime = convertView.findViewById(R.id.textView_orderTime_item);
            TextView orderPrice = convertView.findViewById(R.id.textView_orderPrice_item);

            Order order = items.get(position);
            orderNo.setText(order.getOrderNo());
            orderDate.setText(order.getOrderDate());
            orderTime.setText(order.getOrderTime());
            orderPrice.setText(order.getOrderPrice());

    return convertView;
    }

}
