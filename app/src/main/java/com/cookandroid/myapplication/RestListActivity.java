package com.cookandroid.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RestListActivity extends AppCompatActivity {
    private ListView listView;
    private ResListAdapter_2 adapter;
    private ArrayList<restaurant> restList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        restList = new ArrayList<restaurant>();
        restList.add(new restaurant("양식"));
        restList.add(new restaurant("중식"));
        restList.add(new restaurant("분식"));
        restList.add(new restaurant("정식"));

       listView =(ListView) findViewById(R.id.listviewFood);
        adapter = new ResListAdapter_2(this,restList);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent intent = new Intent(getApplicationContext(),foodDetail.class);
                startActivity((intent));*/

                restaurant n = (restaurant) parent.getAdapter().getItem(position);
                Log.d("onclick", n.getName());


                RestListActivity.InsertData task = new RestListActivity.InsertData();
                task.execute("http://"+Data.getIP()+"/menulist.php",n.getName());

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

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();}
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null){
                Intent intent = new Intent(RestListActivity.this, RestActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "찾을 수 없습니다", Toast.LENGTH_LONG).show();
            }
            Log.d("phptest", "POST resaponse - "+s);
        }
        @Override
        protected String doInBackground(String... params) {
            String shop = (String)params[1];

            String serverURL = (String)params[0]+"?"+"shop="+shop;

            return GetData.get(serverURL);


        }
    }
}

