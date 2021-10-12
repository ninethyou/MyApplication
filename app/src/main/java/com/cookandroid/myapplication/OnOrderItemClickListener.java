package com.cookandroid.myapplication;

import android.view.View;

import com.cookandroid.myapplication.OrderAdapter;

public interface OnOrderItemClickListener {
    public void onItemClick(OrderAdapter.ViewHolder holder, View view ,int position);
}
