package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyInfoActivity extends AppCompatActivity {
    private TextView id, name, sNum;
    private User user;
    private Button logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        user = DB.getUser();
        id = (TextView) findViewById(R.id.info_id);
        id.setText(user.getID());
        name = (TextView) findViewById(R.id.info_name);
        name.setText(user.getName());
        sNum = (TextView) findViewById(R.id.info_num);
        sNum.setText(user.getStudentNum());
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.setUserIDPW(getApplicationContext(), "", "");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}