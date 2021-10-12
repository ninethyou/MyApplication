package com.cookandroid.myapplication;

import static com.cookandroid.myapplication.R.drawable.gimbap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        data = Data.menulist;

        foodList = new ArrayList<food>();
        for(int i=0;i<data.size()/4;i++){
            foodList.add(new food(R.drawable.gimbap, data.get(i*4).toString(), data.get(i*4+1).toString(), data.get(i*4+2).toString()));
        }

        listView = (ListView) findViewById(R.id.listviewFood);
        adapter = new foodAdapter(this, foodList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(RestActivity.this, foodDetail.class);
                startActivity(intent);

            }
        });


    }

    public boolean onOptionItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();}
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null){
            }else{
                Toast.makeText(getApplicationContext(), "찾을 수 없습니다", Toast.LENGTH_LONG).show();
            }
            Log.d("phptest", "POST resaponse - "+s);
        }
        @Override
        protected String doInBackground(String... params) {
            String shop = (String)params[1];

            String serverURL = (String)params[0]+"?"+"shop="+shop;

            return _GetData.get(serverURL);


        }
    }
}


