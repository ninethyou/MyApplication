package com.cookandroid.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<Order> orderlist = null;
    private Context context;
    private OrderListFragment f;
    private Order o;
    private String rest;

    OrderAdapter(Context context, ArrayList<Order> list, OrderListFragment f){
        this.context = context;
        this.orderlist = list;
        this.f = f;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderNo, orderFood, orderTime, orderPrice, orderCount, orderState, orderWTime;

        ViewHolder(final View itemView){
            super(itemView);

            orderNo = itemView.findViewById(R.id.textView_orderNo_item);
            orderFood = itemView.findViewById(R.id.textView_orderFood_item);
            orderTime = itemView.findViewById(R.id.textView_orderTime_item);
            orderPrice = itemView.findViewById(R.id.textView_orderPrice_item);
            orderCount = itemView.findViewById(R.id.textView_orderCount_item);
            orderState = itemView.findViewById(R.id.textView_orderState_item);
            orderWTime = itemView.findViewById(R.id.textView_orderWTime_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Log.d("pos", Integer.toString(pos));

                    if(orderState.getText().equals("조리 완료")){
                        o=orderlist.get(pos);
                        rest = o.getOrderRest();

                        SendData task = new SendData((Activity) context);
                        String postParameters = "state=0&index="+o.getOrderNo();
                        task.execute("http://"+DB.getIP()+"/orderControl.php", postParameters);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("음식 수령").setMessage("맛있게 드십시오.");
                        builder.setPositiveButton("리뷰쓰러가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Intent intent = new Intent(context, ReViewActivity.class);
                                intent.putExtra("food", o.getOrderFood());
                                intent.putExtra("rest", o.getOrderRest());
                                context.startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                OrderListFragment of = f;
                                of.onResume();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        GetData task2 = new GetData();
                        task2.execute("http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=1&rest="+rest);
                        Log.d("execute", "http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=1&rest="+rest);

                        Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                GetData task2 = new GetData();
                                task2.execute("http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=0&rest="+rest);
                                Log.d("execute", "http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=0&rest="+rest);
                            }
                        }, 60000);





                    }
                    else if(orderState.getText().equals("취소")){
                        o=orderlist.get(pos);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("주문 취소").setMessage("다시 주문하시겠습니까");
                        builder.setPositiveButton("다시 주문", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SendData task = new SendData((Activity) context);
                                String postParameters = "state=wait&index="+o.getOrderNo();
                                task.execute("http://"+DB.getIP()+"/orderControl.php", postParameters);

                                OrderListFragment of = f;
                                of.onResume();
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SendData task = new SendData((Activity) context);
                                String postParameters = "state=0&index="+o.getOrderNo();
                                task.execute("http://"+DB.getIP()+"/orderControl.php", postParameters);

                                OrderListFragment of = f;
                                of.onResume();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                }
            });


        }
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.order_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position){
        String state;
        o = orderlist.get(position);
        holder.orderNo.setText("No."+o.getOrderNo());
        holder.orderFood .setText(o.getOrderFood());
        holder.orderTime.setText(o.getOrderTime());
        holder.orderPrice.setText(o.getOrderPrice()+"원");
        holder.orderCount.setText("x "+o.getOrderCount());
        switch(o.getOrderState()){
            case "wait":
                state = "조리 중";
                holder.orderState.setText(state);
                GetData task = new GetData(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        int wtime = 0;
                        for(int i=0;i< DB.getDataList().size()/2;i++){
                            wtime += Integer.parseInt(DB.getData(i*2).toString())*Integer.parseInt(DB.getData(i*2+1).toString());
                        }
                        holder.orderWTime.setText("대기시간 : "+Integer.toString(wtime)+"분");
                    }
                };

                task.execute("http://"+DB.getIP()+"/getwaittime.php?ID="+DB.getUser().getID()+"&index="+o.getOrderNo());
                break;
            case "cancle":
                state = "취소";
                holder.orderState.setText(state);
                break;
            case "confirm":
                state = "조리 완료";
                holder.orderState.setText(state);
                holder.orderWTime.setText("음식을 수령하세요.");
                break;
        }

    }


    public int getItemCount(){
        return orderlist.size();
    }
}
