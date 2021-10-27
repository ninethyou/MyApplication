package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ListView resListView;
    private ResListAdapter adapter;
    private ArrayList<restaurant> resList;
    private ImageButton imgbtn;
    private User user;
    private TextView id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        user = DB.getUser();
        id = (TextView) rootview.findViewById(R.id.userid);
        id.setText(user.getID()+" 님");

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

        imgbtn = (ImageButton) rootview.findViewById(R.id.imageButtonWishlist);
        imgbtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), WishListActivity.class);
                startActivity(intent);
            }
        });

        resList = new ArrayList<>();


        resListView = (ListView) rootview.findViewById((R.id.restaurantListView));
        resListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                restaurant rest = (restaurant) parent.getAdapter().getItem(position);

                GetData task = new GetData(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        Intent intent = new Intent(getActivity(), CategoryActivity.class);
                        intent.putExtra("rest", rest.getName());
                        startActivity((intent));
                    }
                };
                task.execute("http://"+ DB.getIP()+"/restlist.php?rest="+rest.getName());
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
                resList.clear();
                int k = 0 , n = 0;
                Log.d("congestion", Integer.toString(DB.getDataList().size()));
                for(int i=0;i< DB.getDataList().size()/2;i++){
                    if(DB.getData(i*2+1).toString().equals("구학")){
                        k+=1;
                    }else n+=1;
                }
                resList.add(new restaurant("구학","9:00 - 18:00",congestion(k), k));
                resList.add(new restaurant("신학","9:00 - 18:00",congestion(n), n));
                resListView.setAdapter(adapter);

            }
        };

        task.execute("http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=2&rest=구학");

        adapter = new ResListAdapter(getContext(),resList);
    }

    String congestion(int a){
        /*if(a<66) return "쾌적";
        else if(66<=a && a<132) return "보통";
        else return "혼잡";*/

        if(a<3) return "쾌적";
        else if(3<=a && a<6) return "보통";
        else return "혼잡";
    }

}
