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

import org.w3c.dom.Text;

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
                Intent intent = new Intent(getActivity(),WishList.class);
                startActivity(intent);




            }
        });

        resList = new ArrayList<>();
        resList.add(new restaurant("구학","10:00 - 12:00","혼잡"));
        resList.add(new restaurant("신학","10:00 - 12:00","혼잡"));

        resListView = (ListView) rootview.findViewById((R.id.restaurantListView));
        adapter = new ResListAdapter(getContext(),resList);
        resListView.setAdapter(adapter);
        resListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                restaurant rest = (restaurant) parent.getAdapter().getItem(position);

                GetData task = new GetData(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        Intent intent = new Intent(getActivity(), CategoryActivity.class);
                        startActivity((intent));
                    }
                };
                task.execute("http://"+ DB.getIP()+"/restlist.php?rest="+rest.getName());
            }
        });


            return rootview;
    }

}
