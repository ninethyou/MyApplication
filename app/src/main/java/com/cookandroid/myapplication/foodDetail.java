package com.cookandroid.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class foodDetail extends AppCompatActivity {

    Button btn_order, btn_plus, btn_minus, btn_bucket, btn_wish;
    ImageView img_wish;
    TextView tv_foodname, tv_count;
    food selectfood;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_order = (Button) findViewById(R.id.order);
        btn_plus = (Button) findViewById(R.id.plus);
        btn_minus = (Button) findViewById(R.id.minus);
        img_wish = (ImageView) findViewById(R.id.wish);

        tv_foodname = (TextView) findViewById(R.id.foodName);
        tv_count = (TextView)findViewById(R.id.count);

        Intent intent = getIntent();
        selectfood = (food) intent.getExtras().getSerializable("food");
        Log.w("food", selectfood.getFoodName()+selectfood.getFoodPrice());



        i=1;

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                //startActivity(intent);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>=99){
                    Toast.makeText(getApplicationContext(), "더 이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    i++;
                    tv_count.setText(Integer.toString(i));
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i<=1){
                    Toast.makeText(getApplicationContext(), "더 이상 줄일 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    tv_count.setText(Integer.toString(i));
                }
            }
        });

        img_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendData task = new SendData(foodDetail.this);
                String postParameters = "ID="+DB.getUser().getID()+"&food="+selectfood.getFoodName();
                Log.d("user", DB.getUser().getID()+selectfood.getFoodName());
                task.execute("http://"+DB.getIP()+"/setwish.php",postParameters);

                Toast.makeText(getApplicationContext(), "위시리스트에 추가하였습니다.", Toast.LENGTH_SHORT).show();
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