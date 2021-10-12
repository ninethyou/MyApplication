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

public class LoginActivity extends AppCompatActivity {
    EditText uid, upw;
    Button btn_signin, btn_signup;
    char result;

    private static String IP_Address = "124.51.190.90:1234";
    private static String TAG = "phptest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uid = (EditText) findViewById(R.id.uid);
        upw = (EditText) findViewById(R.id.upw);

        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        btn_signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String id = uid.getText().toString();
                String pw = upw.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://"+IP_Address+"/login.php",id, pw);
                Log.d("login", id+pw);
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
            result = s.toString().charAt(0);
            if(result=='1'){
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);*/
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, "POST resaponse - "+s);

        }
        @Override
        protected String doInBackground(String... params) {
            String ID = (String)params[1];
            String PW = (String)params[2];

            String serverURL = (String)params[0]+"?"+"ID="+ID+"&PW="+PW;

            return GetData.get(serverURL);

        }
    }
}