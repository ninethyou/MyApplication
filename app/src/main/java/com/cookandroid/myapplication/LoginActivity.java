package com.cookandroid.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText uid, upw;
    Button btn_signin, btn_signup;
    char result;
    User user;

    private static String IP_Address = "124.51.190.90:1234";
    private static String TAG = "phptest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = new User();

        uid = (EditText) findViewById(R.id.uid);
        upw = (EditText) findViewById(R.id.upw);

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        btn_signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String id = uid.getText().toString();
                String pw = upw.getText().toString();

                if(!id.isEmpty() && !pw.isEmpty()){
                    InsertData task = new InsertData();
                    task.execute("http://"+IP_Address+"/login.php?ID="+id+"&PW="+pw);
                }else{
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this,"Pleas Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if(s.equals("1")){
                user.setID(DB.getData(1).toString());
                user.setPW(DB.getData(2).toString());
                user.setName(DB.getData(3).toString());
                user.setStudentNum(DB.getData(4).toString());
                DB.setUser(user);

                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();

            }
            Log.d(TAG, "POST resaponse - "+s);

        }
        @Override
        protected String doInBackground(String... params) {
            String serverURL = (String)params[0];
            return GetData.get(serverURL);
        }
    }
}