package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {
    private ListView resListView;
    private ResListAdapter adapter;
    private ArrayList<restaurant> resList;
    private ImageButton imgbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        imgbtn = (ImageButton) rootview.findViewById(R.id.imageButtonBasket);
        imgbtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),BasketActivity.class);
                startActivity((intent));

            }
        });


        resList = new ArrayList<>();
        resList.add(new restaurant("구학","10:00 - 12:00","혼잡"));
        resList.add(new restaurant("구학","10:00 - 12:00","혼잡"));

        resListView = (ListView) rootview.findViewById((R.id.restaurantListView));
        adapter = new ResListAdapter(getContext(),resList);
        resListView.setAdapter(adapter);
        resListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),RestActivity.class);
                startActivity((intent));
            }
        });


            return rootview;
    }





}
