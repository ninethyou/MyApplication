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


    private OrderAdapter adapter;
    private ArrayList<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup) inflater.inflate(R.layout.fragment_notifications, container, false);

        ListView OrderListView = rootview.findViewById(R.id.orderList);
       orderList = new ArrayList<Order>();

        orderList.add(new Order("12345","20-01-01","15:00","25000"));

        adapter = new OrderAdapter(rootview.getContext(),orderList);


        OrderListView.setAdapter(adapter);
        OrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
          return rootview;
    }

}
