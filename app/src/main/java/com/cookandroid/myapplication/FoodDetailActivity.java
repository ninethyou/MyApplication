package com.cookandroid.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodDetailActivity extends AppCompatActivity {

    Button btn_addbasket, btn_plus, btn_minus;
    ImageView img_wish;
    TextView tv_foodname, tv_count, tv_foodinfo;
    Food selectfood;
    int count;
    String isWishOn;
    private FloatingActionButton fb;

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

        fb = (FloatingActionButton) findViewById(R.id.floatingBasket);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });

        count =1;

        btn_addbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.addListBasket(selectfood);
                Toast.makeText(getApplicationContext(), "장바구니에 추가되었습니다.", Toast.LENGTH_SHORT).show();
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