package com.cookandroid.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<Order> orderlist = null;
    private Context context;
    private Fragment f;
    private Order o;
    private String rest;

    OrderAdapter(Context context, ArrayList<Order> list, Fragment f){
        this.context = context;
        this.orderlist = list;
        this.f = f;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderNo, orderFood, orderTime, orderPrice, orderCount, orderState, orderWTime, orderCategory;

        ViewHolder(final View itemView){
            super(itemView);

            orderNo = itemView.findViewById(R.id.textView_orderNo_item);
            orderFood = itemView.findViewById(R.id.textView_orderFood_item);
            orderTime = itemView.findViewById(R.id.textView_orderTime_item);
            orderPrice = itemView.findViewById(R.id.textView_orderPrice_item);
            orderCount = itemView.findViewById(R.id.textView_orderCount_item);
            orderState = itemView.findViewById(R.id.textView_orderState_item);
            orderWTime = itemView.findViewById(R.id.textView_orderWTime_item);
            orderCategory = itemView.findViewById(R.id.textView_orderFood_category);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Log.d("pos", Integer.toString(pos));

                    if(orderState.getText().equals("?????? ??????")){
                        o=orderlist.get(pos);
                        rest = o.getOrderRest();

                        SendData task = new SendData((Activity) context);
                        String postParameters = "state=0&index="+o.getOrderNo();
                        task.execute("http://"+DB.getIP()+"/orderControl.php", postParameters);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("?????? ??????").setMessage("????????? ????????????.");
                        builder.setPositiveButton("??????????????????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Intent intent = new Intent(context, ReViewActivity.class);
                                intent.putExtra("food", o.getOrderFood());
                                intent.putExtra("rest", rest);
                                context.startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                OrderListFragment of = (OrderListFragment) f;
                                of.onResume();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        GetData task2 = new GetData();
                        task2.execute("http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=1&rest="+rest);
                        Log.d("execute", "http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=1&rest="+rest);

                        //???????????? n ms?????? ????????? ?????? ?????? ???????????? ????????? ????????? ??????
                        Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                GetData task2 = new GetData();
                                //????????? ???????????? ??????
                                task2.execute("http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=0&rest="+rest);
                                Log.d("execute", "http://"+DB.getIP()+"/congestion.php?ID="+DB.getUser().getID()+"&code=0&rest="+rest);
                            }
                        }, 60000);





                    }
                    else if(orderState.getText().equals("??????")){
                        o=orderlist.get(pos);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("?????? ??????").setMessage("?????? ????????????????????????");
                        builder.setPositiveButton("?????? ??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SendData task = new SendData((Activity) context);
                                String postParameters = "state=wait&index="+o.getOrderNo();
                                task.execute("http://"+DB.getIP()+"/orderControl.php", postParameters);

                                OrderListFragment of = (OrderListFragment) f;
                                of.onResume();
                            }
                        });
                        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SendData task = new SendData((Activity) context);
                                String postParameters = "state=0&index="+o.getOrderNo();
                                task.execute("http://"+DB.getIP()+"/orderControl.php", postParameters);

                                OrderListFragment of = (OrderListFragment) f;
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

        View view = inflater.inflate(R.layout.order_item2, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position){
        String state;
        o = orderlist.get(position);
        holder.orderNo.setText("No."+o.getOrderNo());
        holder.orderFood .setText(o.getOrderFood());
        holder.orderTime.setText(o.getOrderTime());
        holder.orderPrice.setText(o.getOrderPrice()+"???");
        holder.orderCount.setText("x "+o.getOrderCount());
        holder.orderCategory.setText(o.getOrderCat());
        switch(o.getOrderState()){
            case "wait":
                state = "?????? ???";
                holder.orderState.setText(state);
                GetData task = new GetData(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        int wtime = 0;
                        for(int i=0;i< DB.getDataList().size()/2;i++){
                            wtime += Integer.parseInt(DB.getData(i*2).toString())*Integer.parseInt(DB.getData(i*2+1).toString());
                        }
                        holder.orderWTime.setText("???????????? : "+Integer.toString(wtime)+"???");
                    }
                };

                task.execute("http://"+DB.getIP()+"/getwaittime.php?ID="+DB.getUser().getID()+"&index="+o.getOrderNo());
                break;
            case "cancle":
                state = "??????";
                holder.orderState.setText(state);
                break;
            case "confirm":
                state = "?????? ??????";
                holder.orderState.setText(state);
                holder.orderWTime.setText("????????? ???????????????.");
                break;
        }

    }


    public int getItemCount(){
        return orderlist.size();
    }
}
