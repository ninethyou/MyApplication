package com.cookandroid.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class receiptAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<receipt> arrayList;
    private List<receipt> items;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void addItem(receipt r) { items.add(r);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.receipt_item,parent, false);
        }


        TextView receiptNo = (TextView) convertView.findViewById(R.id.textView_receiptNo);
        TextView foodName = (TextView) convertView.findViewById(R.id.textView_receiptFoodName);
        TextView foodNo = (TextView) convertView.findViewById(R.id.textView_receiptFoodNo);
        TextView Price = (TextView) convertView.findViewById(R.id.textView_receiptPrice);

      receipt receipt = items.get(position);

      receiptNo.setText(receipt.getReceiptNo());
      foodName.setText(receipt.getFoodName());
        foodNo.setText(receipt.getFoodCnt());
        Price.setText(receipt.getPrice());


        Button button = (Button) convertView.findViewById(R.id.button_receiptOk);
        View finalConvertView = convertView;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[]{"5분", "10분", "15분", "20분", "25분", "30분"};
                final boolean[] checkArray = new boolean[]{true, false, false};

                AlertDialog.Builder dlg = new AlertDialog.Builder(finalConvertView.getContext());
                dlg.setTitle("예상시간 선택");
                dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(finalConvertView.getContext(), versionArray[which], Toast.LENGTH_LONG);
                        toast.show();
                    }

                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(finalConvertView.getContext(), "취소되었음", Toast.LENGTH_LONG);
                        toast.show();

                    }
                });


            }
        });


        return convertView;
    }

}