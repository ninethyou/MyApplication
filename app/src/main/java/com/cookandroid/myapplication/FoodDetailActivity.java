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
import android.widget.TextView;
import android.widget.Toast;

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
    Button btn_addbasket, btn_plus, btn_minus, btn_order;
    ImageView img_wish;
    TextView tv_foodname, tv_count, tv_foodinfo;
    Food selectfood;
    int count;
    String isWishOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        btn_addbasket = (Button) findViewById(R.id.addbasket);
        btn_plus = (Button) findViewById(R.id.plus);
        btn_minus = (Button) findViewById(R.id.minus);
        btn_order =  (Button) findViewById(R.id.button_order2);

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

        btn_order.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        BootUser bootUser = new BootUser().setPhone("010-1234-5678"); // !! 자신의 핸드폰 번호로 바꾸기
                        BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0, 2, 3});

                        Bootpay.init(getFragmentManager())
                                .setApplicationId("616530847b5ba4002152c403") // 해당 프로젝트(안드로이드)의 application id 값(위의 값 복붙)
                                .setPG(PG.KCP) // 결제할 PG 사
                                .setMethod(Method.CARD) // 결제수단
                                .setContext(FoodDetailActivity.this)
                                .setBootUser(bootUser)
                                .setBootExtra(bootExtra)
                                .setUX(UX.PG_DIALOG)
//                .setUserPhone("010-1234-5678") // 구매자 전화번호
                                .setName("부경대 학식") // 결제할 상품명
                                .setOrderId("1234") // 결제 고유번호 (expire_month)
                                .setPrice(selectfood.getFoodPrice()) // 결제할 금액
                                .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                                    @Override
                                    public void onConfirm(@Nullable String message) {

                                        if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                        else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                        Log.d("confirm", message);
                                    }
                                })
                                .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                                    @Override
                                    public void onDone(@Nullable String message) {
                                        Log.d("done", message);
                                    }
                                })
                                .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                                    @Override
                                    public void onReady(@Nullable String message) {
                                        Log.d("ready", message);
                                    }
                                })
                                .onCancel(new CancelListener() { // 결제 취소시 호출
                                    @Override
                                    public void onCancel(@Nullable String message) {

                                        Log.d("cancel", message);
                                    }
                                })
                                .onClose(
                                        new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                            @Override
                                            public void onClose(String message) {
                                                Log.d("close", "close");
                                            }
                                        })
                                .request();






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