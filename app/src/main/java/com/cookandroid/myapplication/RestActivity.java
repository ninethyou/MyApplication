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

public class RestActivity extends AppCompatActivity {
    private ListView listView;
    private foodAdapter adapter;
    private ArrayList<food> foodList;
    private ArrayList data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        data = DB.getDataList();

        foodList = new ArrayList<food>();
        for(int i= 0;i<data.size()/4;i++){
            foodList.add(new food(R.drawable.gimbap, data.get(i*4).toString(), data.get(i*4+1).toString(), data.get(i*4+2).toString()));
        }

       listView =(ListView) findViewById(R.id.listviewFood);
        adapter = new foodAdapter(this,foodList);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                food f = (food) parent.getAdapter().getItem(position);
                Log.d("food", f.getFoodName()+f.getFoodPrice()+f.getFoodInfo());
                Intent intent = new Intent(getApplicationContext(),foodDetail.class);

                intent.putExtra("food", (Serializable) f);
                startActivity(intent);
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

