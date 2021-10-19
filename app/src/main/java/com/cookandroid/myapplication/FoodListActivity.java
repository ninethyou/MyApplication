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
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {
    private ListView listView;
    private FoodAdapter adapter;
    private ArrayList<Food> foodList;
    private ArrayList data;
    private User user;
    private TextView category;
    private FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        Intent intent = getIntent();

        fb = (FloatingActionButton) findViewById(R.id.floatingBasket);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });

        category = (TextView) findViewById(R.id.textView_category);
        category.setText(intent.getExtras().getString("category"));
        data = DB.getDataList();
        user = DB.getUser();

        foodList = new ArrayList<Food>();
        for(int i= 0;i<data.size()/5;i++){
            foodList.add(new Food(R.drawable.gimbap, data.get(i*5).toString(), data.get(i*5+1).toString(), data.get(i*5+2).toString(), data.get(i*5+3).toString(),
                        data.get(i*5+4).toString()));
        }

        listView =(ListView) findViewById(R.id.listviewFood);
        adapter = new FoodAdapter(this,foodList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food f = (Food) parent.getAdapter().getItem(position);
                Log.d("food", f.getFoodName()+f.getFoodPrice()+f.getFoodInfo());
                Log.w("wish", DB.getData(0).toString());

                Intent intent = new Intent(getApplicationContext(), FoodDetailActivity.class);
                intent.putExtra("food", (Serializable) f);

                GetData gt = new GetData(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        Log.e("wish", DB.getData(0).toString());
                        startActivity(intent);
                    }
                };
                gt.execute("http://"+DB.getIP()+"/getwishlist.php?ID="+user.getID()+"&food="+f.getFoodName()+"&rest="+f.getRestaurant()+"&code=1");



            }
        });


    }

}

