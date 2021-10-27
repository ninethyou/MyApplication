package com.cookandroid.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderListFragment extends Fragment {


    private OrderAdapter adapter;
    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;
    private Button refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup) inflater.inflate(R.layout.fragment_orderlist, container, false);

        recyclerView = rootview.findViewById(R.id.orderlist);
        orderList = new ArrayList<Order>();

        adapter = new OrderAdapter(rootview.getContext(),orderList, OrderListFragment.this);

        recyclerView = (RecyclerView) rootview.findViewById(R.id.orderlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootview.getContext()));



        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();

        try{
            GetData task = new GetData(){
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if(DB.getData(0).toString().equals("0")){
                        Toast.makeText(getContext(), "주문 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        orderList.clear();
                        for(int i=0;i< DB.getDataList().size()/7;i++) {
                            orderList.add(new Order(DB.getData(i * 7).toString(), DB.getData(i * 7 + 1).toString(), DB.getData(i * 7 + 2).toString(),
                                    DB.getData(i * 7 + 5).toString(), DB.getData(i*7+3).toString(), DB.getData(i*7+4).toString(),
                                    DB.getData(i*7+6).toString()));
                        }

                        recyclerView.setAdapter(adapter);
                    }
                }
            };
            task.execute("http://"+ DB.getIP()+"/getorder.php?ID="+DB.getUser().getID());
        }catch(Exception e){
            e.getMessage();
        }
    }

}
