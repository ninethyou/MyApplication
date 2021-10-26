package com.cookandroid.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class OrderListFragment extends Fragment {


    private OrderAdapter adapter;
    private ArrayList<Order> orderList;
    private ListView OrderListView;
    private Button refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup) inflater.inflate(R.layout.fragment_orderlist, container, false);

        OrderListView = rootview.findViewById(R.id.orderList);
        orderList = new ArrayList<Order>();
        refresh = rootview.findViewById(R.id.btn_refresh);

        adapter = new OrderAdapter(rootview.getContext(),orderList);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        OrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



          return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();

        GetData task = new GetData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(DB.getData(0).toString().equals("0")){
                    Toast.makeText(getContext(), "주문 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    for(int i=0;i< DB.getDataList().size()/6;i++) {
                        orderList.add(new Order(DB.getData(i * 6).toString(), DB.getData(i * 6 + 1).toString(), DB.getData(i * 6 + 2).toString(),
                                    DB.getData(i * 6 + 5).toString(), DB.getData(i*6+3).toString(), DB.getData(i*6+4).toString()));
                    }

                    OrderListView.setAdapter(adapter);
                }




            }
        };
        task.execute("http://"+ DB.getIP()+"/getorder.php?ID="+DB.getUser().getID());
    }
}
