package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.myapplication.R;
import com.cookandroid.myapplication.basket;

import java.util.ArrayList;
import java.util.List;

public class basketAdapter extends RecyclerView.Adapter<basketAdapter.ViewHolder> {

    ArrayList<basket> items = new ArrayList<basket>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.basket,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        basket item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(basket item)
    {
        items.add(item);
    }
    public void setItems(ArrayList<basket> items)
    {
        this.items = items;
    }
    public basket getItem(int position)
    {
        return items.get(position);
    }
    public void setItems(int position, basket item)
    {
        items.set(position,item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_foodNo;
        TextView textView_foodName;
        TextView textView_foodCnt;
        TextView textView_foodPrice;
        TextView textView_foodTotalPrice;
        public ViewHolder(View itemView)
        {
            super(itemView);
            TextView textView_foodNo = (TextView) itemView.findViewById(R.id.textView_BasketfoodNo);
            TextView textView_foodName = (TextView) itemView.findViewById(R.id.textView_BasketfoodName);
            TextView textView_foodPrice = (TextView) itemView.findViewById(R.id.textView_BasketfoodPrice);

            TextView textView_foodCnt = (TextView) itemView.findViewById(R.id.textView_BasketfoodCnt);
            TextView textView_foodTotalPrice = (TextView) itemView.findViewById(R.id.textView_BasketTotalPrice);

        }
    public void setItem(basket item)
    {
        textView_foodNo.setText(item.getFoodNo());
        textView_foodName.setText(item.getFoodName());
        textView_foodPrice.setText(item.getFoodPrice());

        textView_foodCnt.setText(item.getFoodCnt());
        Integer total = item.getFoodCnt()  * item.getFoodPrice();
        textView_foodTotalPrice.setText(total.toString());

    }


    }

}
