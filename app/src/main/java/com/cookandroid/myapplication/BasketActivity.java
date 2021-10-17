package com.cookandroid.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toolbar;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    TimePicker timePicker;
    RadioButton btnResv;
    RadioButton btnDirect;
    private ArrayList<basket> basketArrayList;
    private basketAdapter basketAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


      basketArrayList =new ArrayList<basket>();

        basketArrayList.add(new basket("김밥","12345",25000,1));
        ListView listView = (ListView)findViewById(R.id.basketList);
        basketAdapter = new basketAdapter(this,basketArrayList);
        listView.setAdapter(basketAdapter);

        timePicker = (TimePicker)findViewById(R.id.timePicker1);
        btnDirect = (RadioButton) findViewById(R.id.radioButton_direct);
        btnResv = (RadioButton) findViewById(R.id.radioButton_resv);

        btnResv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
            }
        });

        btnDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.INVISIBLE);
            }
        });



    }
    public boolean onOptionItemSelected(MenuItem menuItem)
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