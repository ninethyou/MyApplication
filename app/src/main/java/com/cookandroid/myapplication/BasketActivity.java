package com.cookandroid.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
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
    private int stuck = 10;
    private RadioButton orderOption;
    private RadioGroup rg;
    private int hour, minute;
    private String min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        BootpayAnalytics.init(this, "616530847b5ba4002152c403");

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
        timePicker.setIs24HourView(true);
        hour = timePicker.getHour();
        minute = timePicker.getMinute();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m) {
                hour = h;
                minute = m;
            }
        });
        if(minute<10) min="0"+Integer.toString(minute);
        else min=Integer.toString(minute);

        btnDirect = (RadioButton) findViewById(R.id.radioButton_direct);
        btnResv = (RadioButton) findViewById(R.id.radioButton_resv);
        order = (Button) findViewById(R.id.button_order);

        rg = (RadioGroup) findViewById(R.id.radioGroup) ;

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout2);

        if(totalPrice == 0)
            layout.setVisibility(View.INVISIBLE);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = DB.getUser();
                for(int i=0;i<foodlist.size();i++){
                    SendData task = new SendData(BasketActivity.this);
                    String postParameters;
                    if(rg.getCheckedRadioButtonId()==R.id.radioButton_direct) {
                        postParameters="id="+user.getID()+"&rest="+foodlist.get(i).getRestaurant()+"&cat="+foodlist.get(i).getCategory()+
                                "&food="+foodlist.get(i).getFoodName()+"&count="+foodlist.get(i).getFoodCount()+"&state=wait"+"&pay="+foodlist.get(i).getFoodPrice()*
                                foodlist.get(i).getFoodCount()+"&option=0";
                        task.execute("http://"+DB.getIP()+"/order.php",postParameters);
                        Toast.makeText(getApplicationContext(), "?????? ??????", Toast.LENGTH_SHORT).show();

                    }else if(rg.getCheckedRadioButtonId()==R.id.radioButton_resv){
                        long now = System.currentTimeMillis();
                        Date date = new Date(now);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String getTime = sdf.format(date)+hour+min+"00";
                        Log.d("time", getTime);

                        postParameters="id="+user.getID()+"&rest="+foodlist.get(i).getRestaurant()+"&cat="+foodlist.get(i).getCategory()+
                                "&food="+foodlist.get(i).getFoodName()+"&count="+foodlist.get(i).getFoodCount()+"&state=wait"+"&pay="+foodlist.get(i).getFoodPrice()*
                                foodlist.get(i).getFoodCount()+"&option=1+&time="+getTime;
                        task.execute("http://"+DB.getIP()+"/order.php",postParameters);
                        Toast.makeText(getApplicationContext(), "?????? ??????", Toast.LENGTH_SHORT).show();
                    }



                }
                DB.resetFoodList_Basket();
/*
?????? ??????
  */
                BootUser bootUser = new BootUser().setPhone("010-1234-5678"); // !! ????????? ????????? ????????? ?????????
                BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0, 2, 3});

                Bootpay.init(getFragmentManager())
                        .setApplicationId("616530847b5ba4002152c403") // ?????? ????????????(???????????????)??? application id ???(?????? ??? ??????)
                        .setPG(PG.KCP) // ????????? PG ???
                        .setMethod(Method.CARD) // ????????????
                        .setContext(BasketActivity.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
//                .setUserPhone("010-1234-5678") // ????????? ????????????
                        .setName("????????? ??????") // ????????? ?????????
                        .setOrderId("1234") // ?????? ???????????? (expire_month)
                        .setPrice(totalPrice) // ????????? ??????
                        .onConfirm(new ConfirmListener() { // ????????? ???????????? ?????? ?????? ???????????? ?????????, ?????? ???????????? ?????? ????????? ??????
                            @Override
                            public void onConfirm(@Nullable String message) {

                                if (0 < stuck) Bootpay.confirm(message); // ????????? ?????? ??????.
                                else Bootpay.removePaymentWindow(); // ????????? ?????? ????????? ???????????? ?????? ?????? ??????
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // ??????????????? ??????, ????????? ?????? ??? ????????? ????????? ????????? ???????????????
                            @Override
                            public void onDone(@Nullable String message) {
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // ???????????? ?????? ??????????????? ???????????? ???????????? ???????????????.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // ?????? ????????? ??????
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //???????????? ????????? ???????????? ??????
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();

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



}