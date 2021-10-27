package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ListView listView;
    private GridView gridView;
    private CategoryAdapter adapter;
    private ArrayList<Category> category;
    private FloatingActionButton fb;
    private String rest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        Intent intent = getIntent();
        rest = intent.getExtras().getString("rest");

        fb = (FloatingActionButton) findViewById(R.id.floatingBasket);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });

        category = new ArrayList<Category>();

        Log.e("rest", Integer.toString(DB.getDataList().size()));
        for(int i = 1; i<DB.getDataList().size();i++){
            category.add(new Category(DB.getData(i).toString()));
            Log.d("rest",DB.getData(i).toString());
            Log.w("rest",category.get(i-1).getName());
        }

        gridView = (GridView) findViewById(R.id.gridviewFood);
        adapter = new CategoryAdapter(this, category);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Category n = (Category) parent.getAdapter().getItem(position);
                Log.d("onclick", n.getName());


                GetData task = new GetData(){
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if(s != null){
                            Intent intent = new Intent(CategoryActivity.this, FoodListActivity.class);
                            intent.putExtra("category", n.getName());
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "찾을 수 없습니다", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                task.execute("http://"+ DB.getIP()+"/menulist.php?category="+n.getName()+"&rest="+rest);
            }
        });
    }
}

