package com.cookandroid.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_admin_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_admin_list extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup) inflater.inflate(R.layout.fragment_notifications, container, false);

        RecyclerView OrderListView = rootview.findViewById(R.id.Orderlist_admin);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootview.getContext(),LinearLayoutManager.VERTICAL,false);
        OrderListView.setLayoutManager(layoutManager);
        OrderAdapter adapter = new OrderAdapter();

        adapter.addItem(new Order("12345","20-01-01","15:00","25000"));

        return inflater.inflate(R.layout.fragment_admin_list, container, false);
    }
}