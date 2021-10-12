package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements OnOrderItemClickListener
{

    OnOrderItemClickListener listener;
    ArrayList<Order> items = new ArrayList<Order>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.order_item,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(Order item)
    {
        items.add(item);
    }
    public void setItems(ArrayList<Order> items)
    {
        this.items =items;
    }
    public Order getItem(int position)
    {
        return items.get(position);
    }
    public void setItem(int position,Order item)
    {
        items.set(position,item);
    }

    public void setOnItemClickListener(OnOrderItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != listener)
            listener.onItemClick(holder,view,position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderNo;
        TextView orderDate;
        TextView orderTime;
        TextView orderPrice;


        public ViewHolder(@NonNull View itemView,final OnOrderItemClickListener listener) {
            super(itemView);
            TextView orderNo = itemView.findViewById(R.id.textView_orderNo_item);
            TextView orderDate = itemView.findViewById(R.id.textView_orderDate_item);
            TextView orderTime = itemView.findViewById(R.id.textView_orderTime_item);
            TextView orderPrice = itemView.findViewById(R.id.textView_orderPrice_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null)
                    {
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });
        }
        public void setItem(Order item)
        {
            orderDate.setText(item.getOrderDate());
            orderNo.setText(item.getOrderNo());
            orderTime.setText(item.getOrderTime());
            orderPrice.setText(item.getOrderPrice());
        }

    }






}
