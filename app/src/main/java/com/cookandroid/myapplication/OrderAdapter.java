package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends BaseAdapter
{

    private Context context;
   private  List<Order> orderList;
    private LayoutInflater layoutInflater;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.order_item,null);

        TextView orderNo = (TextView) v.findViewById(R.id.textView_orderNo_item);
        TextView orderDate = (TextView) v.findViewById(R.id.textView_orderDate_item);
        TextView orderTime = (TextView) v.findViewById(R.id.textView_orderTime_item);
        TextView orderPrice = (TextView) v.findViewById(R.id.textView_orderPrice_item);

        orderDate.setText(orderList.get(position).getOrderDate());
        orderNo.setText(orderList.get(position).getOrderNo());
        orderTime.setText(orderList.get(position).getOrderTime());
        orderPrice.setText(orderList.get(position).getOrderPrice());

         return v;
    }

}
