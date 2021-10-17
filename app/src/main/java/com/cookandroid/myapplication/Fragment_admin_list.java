package com.cookandroid.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment_admin_list extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup) inflater.inflate(R.layout.fragment_notifications, container, false);

        ListView OrderListView = rootview.findViewById(R.id.Orderlist_admin);
        ArrayList<Order> orderArrayList = new ArrayList<Order>();

          orderArrayList.add(new Order("12345","20-01-01","15:00","25000"));
        OrderAdapter adapter = new OrderAdapter(rootview.getContext(), orderArrayList);

        return inflater.inflate(R.layout.fragment_admin_list, container, false);
    }
}