package com.cookandroid.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    private ListView listView;
    private FoodAdapter adapter;
    private ArrayList<Food> wishlist;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = DB.getUser();
        wishlist = new ArrayList<Food>();

        GetData task = new GetData(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("wish", DB.getData(0).toString());
                for(int i=0;i<DB.getDataList().size()/5;i++){
                    wishlist.add(new Food(R.drawable.gimbap,DB.getData(i*5+1).toString(),DB.getData(i*5+2).toString(),DB.getData(i*5+3).toString(),
                            DB.getData(i*5+4).toString(),DB.getData(i*5+5).toString()));
                }
                listView.setAdapter(adapter);
            }
        };
        task.execute("http://"+DB.getIP()+"/getwishlist.php?ID="+user.getID()+"&code=0");




        listView =(ListView) findViewById(R.id.wishList);
        adapter = new FoodAdapter(this, wishlist);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food f = (Food) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), FoodDetailActivity.class);
                intent.putExtra("food", (Serializable) f);
                startActivity(intent);
            }
            public void onItemLonClick(AdapterView<?> parent, View view, int position, long id){
                wishlist.remove(position);
                adapter.notifyDataSetChanged();

            }

        });
    }

}