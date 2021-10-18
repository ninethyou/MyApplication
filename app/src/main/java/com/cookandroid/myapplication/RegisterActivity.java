package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_create, btn_idcheck;
    private EditText rid, rpw, rname, rbirth, rnum;
    private static String userID, userPassword, userBirth, userName, userNum;
    boolean idck, pwck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idck = false;
        pwck = true;

        rid = (EditText) findViewById(R.id.reg_id);
        rpw = (EditText) findViewById(R.id.reg_pw);
        rname = (EditText) findViewById(R.id.reg_name);
        rnum = (EditText) findViewById(R.id.reg_num);

        btn_create = (Button) findViewById(R.id.reg_create);

        btn_create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = rid.getText().toString();
                String pw = rpw.getText().toString();
                String name = rname.getText().toString();
                String num = rnum.getText().toString();

                if(idck && pwck){
                    SendData task = new SendData(RegisterActivity.this);
                    String postParameters = "userID="+id+"&userPassword="+pw+"&userName="+name+"&userNum="+num;
                    task.execute("http://"+ DB.getIP()+"/register.php",postParameters);

                    rid.setText("");
                    rpw.setText("");
                    rname.setText("");
                    rnum.setText("");

                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }else if(!idck && pwck){
                    Toast.makeText(getApplicationContext(), "아이디를 확인하세요.", Toast.LENGTH_SHORT).show();
                }else if(!pwck && idck){
                    Toast.makeText(getApplicationContext(), "비밀번호 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "아이디를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_idcheck = (Button) findViewById(R.id.reg_idck);
        btn_idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData task = new GetData(){
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if(DB.getData(0).toString().equals("0")){
                            Toast.makeText(getApplicationContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                            idck = true;
                        }else if(DB.getData(0).toString().equals("1")){
                            Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            idck = false;
                        }else{
                            Toast.makeText(getApplicationContext(), "아이디를 적어주세요.", Toast.LENGTH_SHORT).show();
                            idck = false;
                        }
                    }
                };
                task.execute("http://"+ DB.getIP()+"/idcheck.php?ID="+rid.getText());
            }
        });
    }


}
