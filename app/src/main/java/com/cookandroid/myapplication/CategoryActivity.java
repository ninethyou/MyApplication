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
    private ArrayList<category> category;
    private FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fb = (FloatingActionButton) findViewById(R.id.floatingBasket);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
                startActivity(intent);
            }
        });

        category = new ArrayList<category>();

        Log.e("rest", Integer.toString(DB.getDataList().size()));
        for(int i = 1; i<DB.getDataList().size();i++){
            category.add(new category(DB.getData(i).toString()));
            Log.d("rest",DB.getData(i).toString());
            Log.w("rest",category.get(i-1).getName());
        }

        gridView = (GridView) findViewById(R.id.gridviewFood);
        adapter = new CategoryAdapter(this, category);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                category n = (category) parent.getAdapter().getItem(position);
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
                        Log.d("phptest", "POST resaponse - "+s);
                    }
                };
                task.execute("http://"+ DB.getIP()+"/menulist.php?category="+n.getName());
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

