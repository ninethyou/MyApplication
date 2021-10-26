package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText uid, upw;
    private Button btn_signin, btn_signup;
    private char result;
    private User user;
    private CheckBox autologin;
    private boolean isAutoLogin;

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

        autologin = (CheckBox) findViewById(R.id.autologin);
        autologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autologin.isChecked()){
                    Toast.makeText(getApplicationContext(), "자동로그인 활성", Toast.LENGTH_SHORT).show();
                    SaveSharedPreference.setUserIDPW(getApplicationContext(), uid.getText().toString(), upw.getText().toString());
                    isAutoLogin = true;
                }else{
                    Toast.makeText(getApplicationContext(), "자동로그인 비활성", Toast.LENGTH_SHORT).show();
                    SaveSharedPreference.setUserIDPW(getApplicationContext(), "","");
                    isAutoLogin = false;
                }
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String id = uid.getText().toString();
                String pw = upw.getText().toString();

                if(!id.isEmpty() && !pw.isEmpty()){
                    GetData task = new GetData(){
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            if(s.equals("1")){
                                Log.d("getdata1", DB.getData(1).toString());
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
                    };
                    task.execute("http://"+DB.getIP()+"/login.php?ID="+id+"&PW="+pw);
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

    @Override
    protected void onResume() {
        super.onResume();

        DB.getFoodlist_Basket().clear();
    }
}