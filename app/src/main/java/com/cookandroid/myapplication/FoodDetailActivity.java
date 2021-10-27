package com.cookandroid.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

public class FoodDetailActivity extends AppCompatActivity {

    private int stuck =10;
    Button btn_addbasket, btn_plus, btn_minus;
    ImageView img_wish;
    TextView tv_foodname, tv_count, tv_foodinfo;
    Food selectfood;
    int count;
    String isWishOn;
    ArrayList<Review> reviewlist;
    ListView listView;
    ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        btn_addbasket = (Button) findViewById(R.id.addbasket);
        btn_plus = (Button) findViewById(R.id.plus);
        btn_minus = (Button) findViewById(R.id.minus);

        img_wish = (ImageView) findViewById(R.id.wish);

        tv_foodname = (TextView) findViewById(R.id.foodname);
        tv_foodinfo = (TextView) findViewById(R.id.foodinfo);
        tv_count = (TextView)findViewById(R.id.count);

        Intent intent = getIntent();
        selectfood = (Food) intent.getExtras().getSerializable("food");
        Log.w("food", selectfood.getFoodName()+selectfood.getFoodPrice());

        tv_foodname.setText(selectfood.getFoodName());
        tv_foodinfo.setText(selectfood.getFoodInfo());

        isWishOn = DB.getData(0).toString();
        Log.d("wish", isWishOn);

        if(isWishOn.equals("0")){
            img_wish.setImageResource(R.drawable.wishoff);
        }else{
            img_wish.setImageResource(R.drawable.wishon);
        }

        reviewlist = new ArrayList<Review>();



        listView =(ListView) findViewById(R.id.reviewlist);
        adapter = new ReviewAdapter(getApplicationContext(),reviewlist);



        count =1;


        btn_addbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food food = new Food(selectfood.getRestaurant(), selectfood.getCategory(), selectfood.getFoodName(), selectfood.getFoodPrice(), selectfood.getFoodInfo(), count);
                DB.addFoodList_Basket(food);
                Toast.makeText(getApplicationContext(), "장바구니에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count >=99){
                    Toast.makeText(getApplicationContext(), "더 이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    count++;
                    tv_count.setText(Integer.toString(count));
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count <=1){
                    Toast.makeText(getApplicationContext(), "더 이상 줄일 수 없습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    count--;
                    tv_count.setText(Integer.toString(count));
                }
            }
        });

        img_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isWishOn.equals("0")){
                    SendData task = new SendData(FoodDetailActivity.this);
                    String postParameters = "ID="+DB.getUser().getID()+"&food="+selectfood.getFoodName()+"&restaurant="+selectfood.getRestaurant()+"&code=0";
                    Log.d("user", DB.getUser().getID()+selectfood.getFoodName());
                    task.execute("http://"+DB.getIP()+"/setwishlist1.php",postParameters);
                    img_wish.setImageResource(R.drawable.wishon);
                    isWishOn = "1";

                    Toast.makeText(getApplicationContext(), "위시리스트에 추가하였습니다.", Toast.LENGTH_SHORT).show();
                }else if(isWishOn.equals("1")){
                    SendData task = new SendData(FoodDetailActivity.this);
                    String postParameters = "ID="+DB.getUser().getID()+"&food="+selectfood.getFoodName()+"&restaurant="+selectfood.getRestaurant()+"&code=1";
                    Log.d("user", DB.getUser().getID()+selectfood.getFoodName());
                    task.execute("http://"+DB.getIP()+"/setwishlist1.php",postParameters);
                    img_wish.setImageResource(R.drawable.wishoff);
                    isWishOn = "0";

                    Toast.makeText(getApplicationContext(), "위시리스트에서 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetData task = new GetData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                for(int i= 0;i<DB.getDataList().size()/3;i++){
                    reviewlist.add(new Review(DB.getData(i*3).toString(), DB.getData(i*3+2).toString(), Integer.parseInt(DB.getData(i*3+1).toString())));
                    listView.setAdapter(adapter);
                }
            }
        };

        task.execute("http://"+DB.getIP()+"/getreview.php?ID="+DB.getUser().getID()+"&rest="+selectfood.getRestaurant()+"&cat="+selectfood.getCategory()+
                "&food="+selectfood.getFoodName());

    }



}