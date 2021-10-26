package com.cookandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    TimePicker timePicker;
    RadioButton btnResv;
    RadioButton btnDirect;
    Button order;
    private ArrayList<Basket> basketArrayList;
    private ArrayList<Food> foodlist;
    private BasketAdapter basketAdapter;
    private Food food;
    private int totalPrice;
    private TextView tv_totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        totalPrice = 0;

        basketArrayList =new ArrayList<Basket>();
        foodlist = DB.getFoodlist_Basket();
        Log.d("basket", String.valueOf(DB.getFoodlist_Basket().size()));

        for(int i = 0; i<DB.getFoodlist_Basket().size(); i++){
            food = DB.getFoodlist_Basket().get(i);
            basketArrayList.add(new Basket(food.getFoodName(), food.getFoodPrice(), food.getFoodCount()));
            totalPrice+=food.getFoodCount()*food.getFoodPrice();
        }
        tv_totalPrice = (TextView) findViewById(R.id.textView_totalPrice);
        tv_totalPrice.setText(Integer.toString(totalPrice));

        ListView listView = (ListView)findViewById(R.id.basketList);
        basketAdapter = new BasketAdapter(this,basketArrayList);
        listView.setAdapter(basketAdapter);

        timePicker = (TimePicker)findViewById(R.id.timePicker1);
        btnDirect = (RadioButton) findViewById(R.id.radioButton_direct);
        btnResv = (RadioButton) findViewById(R.id.radioButton_resv);
        order = (Button) findViewById(R.id.button_order);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = DB.getUser();
                for(int i=0;i<foodlist.size();i++){
                    SendData task = new SendData(BasketActivity.this);
                    String postParameters="id="+user.getID()+"&rest="+foodlist.get(i).getRestaurant()+"&cat="+foodlist.get(i).getCategory()+
                                    "&food="+foodlist.get(i).getFoodName()+"&count="+foodlist.get(i).getFoodCount()+"&state=wait"+"&pay="+foodlist.get(i).getFoodPrice()*
                                    foodlist.get(i).getFoodCount();
                    task.execute("http://"+DB.getIP()+"/order.php",postParameters);
                }
                DB.resetFoodList_Basket();
                finish();
            }
        });

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