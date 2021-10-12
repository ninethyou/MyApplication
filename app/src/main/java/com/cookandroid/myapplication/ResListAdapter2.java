package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResListAdapter2 extends BaseAdapter  {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<restaurant> resList;

    public ResListAdapter2(Context context, List<restaurant> resList) {
        this.context = context;
        this.resList = resList;
    }

    @Override
    public int getCount() {
        return resList.size();
    }

    @Override
    public Object getItem(int position) {
        return resList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.restaurant, parent, false);
        }
        TextView resName = (TextView) convertView.findViewById((R.id.textViewResName));


        restaurant reslistItem = resList.get(position);
        resName.setText(reslistItem.getName());

        return convertView;
    }


}
