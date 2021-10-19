package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AutoLoginActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autologin);

        if(SaveSharedPreference.getUserID(AutoLoginActivity.this).length() == 0){
            intent = new Intent(AutoLoginActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }else{
            GetData task = new GetData(){
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);

                    if(s.equals("1")){
                        DB.setUser(new User(DB.getData(1).toString(), DB.getData(2).toString(), DB.getData(3).toString(),DB.getData(4).toString()));
                        intent = new Intent(AutoLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();

                    }
                }
            };
            task.execute("http://"+DB.getIP()+"/login.php?ID="+SaveSharedPreference.getUserID(AutoLoginActivity.this)
                    +"&PW="+SaveSharedPreference.getUserPW(AutoLoginActivity.this));

            this.finish();
        }
    }
}
