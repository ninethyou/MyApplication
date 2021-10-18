package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResListAdapter extends BaseAdapter  {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<restaurant> resList;

    public ResListAdapter(Context context, List<restaurant> resList) {
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
        TextView resName = (TextView) convertView.findViewById((R.id.resName));
        TextView resTime = (TextView) convertView.findViewById((R.id.textViewResOpTime));
        TextView resCongestion = (TextView) convertView .findViewById((R.id.textViewResCongestion));


        restaurant reslistItem = resList.get(position);
        resName.setText(reslistItem.getName());
        resTime.setText(reslistItem.getOpTime());
        resCongestion.setText((reslistItem.getCongestion()));

        return convertView;
    }


}
