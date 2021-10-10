package com.cookandroid.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class WishList extends AppCompatActivity {
    private ListView listView;
    private foodAdapter adapter;
    private ArrayList<food> foodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        foodList = new ArrayList<food>();
        foodList.add(new food(R.drawable.gimbap,"kimbap","2000","This is kimbap"));

        listView =(ListView) findViewById(R.id.wishList);
        adapter = new foodAdapter(this,foodList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),foodDetail.class);
                startActivity((intent));
            }
        });
    }
    public boolean onOptionItemSelected (MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}