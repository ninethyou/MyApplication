package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CategoryAdapter extends BaseAdapter  {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Category> category;

    public CategoryAdapter(Context context, List<Category> resList) {
        this.context = context;
        this.category = resList;
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int position) {
        return category.get(position);
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
             convertView = inflater.inflate(R.layout.category, parent, false);
        }
        TextView resName = (TextView) convertView.findViewById((R.id.resName));


        Category catlistItem = category.get(position);
        resName.setText(catlistItem.getName());

        return convertView;
    }


}
