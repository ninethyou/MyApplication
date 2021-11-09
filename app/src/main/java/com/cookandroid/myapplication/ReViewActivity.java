package com.cookandroid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReViewActivity extends AppCompatActivity {
    private TextView reFood;
    private EditText reComment;
    private RatingBar reScore;
    private int score;
    private Button reWrite, reCancle;
    String rest, food, cat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        reFood = (TextView) findViewById(R.id.reviewFoodName);
        reComment = (EditText) findViewById(R.id.reviewComment2);
        reScore = (RatingBar) findViewById(R.id.reviewRaitingBar);
        reWrite = (Button) findViewById(R.id.btn_review);
        reCancle = (Button) findViewById(R.id.btn_reviewcancle);

        Intent intent = getIntent();
        food = intent.getExtras().getString("food");
        rest = intent.getExtras().getString("rest");

        reFood.setText(food);

        reWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SendData task = new SendData(ReViewActivity.this);
                String param = "ID="+DB.getUser().getID()+"&food="+food+"&rest="+rest
                        +"&review="+reComment.getText().toString()+"&score="+Integer.toString((int)reScore.getRating());
                task.execute("http://"+DB.getIP()+"/setreview.php", param);

                reComment.setText("");
                Toast.makeText(getApplicationContext(), "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show();

                onBackPressed();

            }
        });
        reCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
