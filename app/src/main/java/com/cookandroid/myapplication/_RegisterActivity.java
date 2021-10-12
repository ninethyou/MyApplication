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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class _RegisterActivity extends AppCompatActivity {
    private static String IP_Address = "124.51.190.90:1234";

    private Button btn_create;
    private EditText rid, rpw, rname, rbirth, rnum;
    private static String userID, userPassword, userBirth, userName, userNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        rid = (EditText) findViewById(R.id.reg_id);
        rpw = (EditText) findViewById(R.id.reg_pw);
        rbirth = (EditText) findViewById(R.id.reg_birth);
        rname = (EditText) findViewById(R.id.reg_name);
        rnum = (EditText) findViewById(R.id.reg_num);

        btn_create = (Button) findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = rid.getText().toString();
                String pw = rpw.getText().toString();
                String birth= rbirth.getText().toString();
                String name = rname.getText().toString();
                String num = rnum.getText().toString();

                InsertData task = new InsertData();
                String postParameters = "userID="+id+"&userPassword="+pw+"&userName="+name+"&userBirth="+birth+"&userNum="+num;
                task.execute("http://"+IP_Address+"/register.php",postParameters);

                rid.setText("");
                rpw.setText("");
                rname.setText("");
                rbirth.setText("");
                rnum.setText("");

                Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), _LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog = ProgressDialog.show(_RegisterActivity.this,"Pleas Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            Log.d("php", "POST resaponse - "+s);
        }
        protected String doInBackground(String... params){

            return _SendData.send((String)params[0], (String)params[1]);
        }
    }
}
