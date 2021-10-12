package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private RecyclerView OrderListView;
    private OrderAdapter adapter;
    private ArrayList<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup) inflater.inflate(R.layout.fragment_notifications, container, false);

        RecyclerView OrderListView = rootview.findViewById(R.id.orderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootview.getContext(),LinearLayoutManager.VERTICAL,false);
        OrderListView.setLayoutManager(layoutManager);
        OrderAdapter adapter = new OrderAdapter();

         adapter.addItem(new Order("12345","20-01-01","15:00","25000"));

        OrderListView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnOrderItemClickListener() {
            @Override
            public void onItemClick(OrderAdapter.ViewHolder holder, View view, int position) {
                Intent intent = new Intent(rootview.getContext(),OrderDetailActivity.class);
                startActivity(intent);
            }
        });



        return rootview;
    }

}
