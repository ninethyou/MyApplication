package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyInfoFragment extends Fragment {
    private TextView id, name, sNum;
    private User user;
    private Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_myinfo, container, false);

        user = DB.getUser();
        id = (TextView) rootview.findViewById(R.id.info_id);
        id.setText(user.getID());
        name = (TextView) rootview.findViewById(R.id.info_name);
        name.setText(user.getName());
        sNum = (TextView) rootview.findViewById(R.id.info_num);
        sNum.setText(user.getStudentNum());
        logout = (Button) rootview.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setUserIDPW(getContext(), "", "");
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });



        return rootview;
    }

}
