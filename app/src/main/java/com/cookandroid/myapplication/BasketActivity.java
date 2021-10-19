package com.cookandroid.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    TimePicker timePicker;
    RadioButton btnResv;
    RadioButton btnDirect;
    private ArrayList<Basket> basketArrayList;
    private BasketAdapter basketAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


      basketArrayList =new ArrayList<Basket>();

        basketArrayList.add(new Basket("김밥","12345",25000,1));
        ListView listView = (ListView)findViewById(R.id.basketList);
        basketAdapter = new BasketAdapter(this,basketArrayList);
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