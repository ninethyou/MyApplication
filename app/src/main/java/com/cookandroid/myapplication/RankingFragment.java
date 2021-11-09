package com.cookandroid.myapplication;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class RankingFragment extends Fragment {

    int index;
    TextView[] food;
    ImageView[] rankimg;
    RatingBar[] ratingBar;
    TextView[] score;

    View view[];
    ArrayList<Prefer> preferlist= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_ranking, container, false);

        index = 6;
        food = new TextView[index];
        rankimg = new ImageView[index];
        ratingBar = new RatingBar[index];
        score = new TextView[index];
        view = new View[index];

        view[0] = rootview.findViewById(R.id.best_1);
        view[1] = rootview.findViewById(R.id.best_2);
        view[2] = rootview.findViewById(R.id.best_3);
        view[3] = rootview.findViewById(R.id.worst_3);
        view[4] = rootview.findViewById(R.id.worst_2);
        view[5] = rootview.findViewById(R.id.worst_1);


        for(int i = 0;i<index;i++){
            food[i] = (TextView)view[i].findViewById(R.id.prefer_food);
            rankimg[i] = (ImageView) view[i].findViewById(R.id.prefer_rankimg);
            ratingBar[i] = (RatingBar) view[i].findViewById(R.id.prefer_rating);
            score[i] = (TextView) view[i].findViewById(R.id.prefer_score);
        }

        return rootview;

    }

    @Override
    public void onResume() {
        super.onResume();

        GetData task = new GetData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                int j=3;
                for(int i=0;i< DB.getDataList().size()/j;i++){
                    preferlist.add(new Prefer(DB.getData(i*j).toString(), Float.parseFloat(DB.getData(i*j+1).toString()),
                            DB.getData(i*j+2).toString()));
                }
                Collections.sort(preferlist);   //오름차순 정렬
                for(int i=0;i<DB.getDataList().size()/j;i++){
                    Log.d("prefer",preferlist.get(i).getFoodName().toString());
                }

                for(int i=0;i<3;i++){
                    Prefer p = preferlist.get(i);
                    Prefer q = preferlist.get(preferlist.size()-(i+1));

                    food[i].setText(p.getFoodName());
                    ratingBar[i].setRating(p.getScore());
                    score[i].setText(Double.toString(Math.round(p.getScore()*10)/10.0));

                    food[index-(i+1)].setText(q.getFoodName());
                    ratingBar[index-(i+1)].setRating(q.getScore());
                    score[index-(i+1)].setText(Double.toString(Math.round(q.getScore()*10)/10.0));
                }
                rankimg[0].setImageResource(R.drawable.p1);
                rankimg[1].setImageResource(R.drawable.p2);
                rankimg[2].setImageResource(R.drawable.p3);
                rankimg[3].setImageResource(R.drawable.p3);
                rankimg[4].setImageResource(R.drawable.p2);
                rankimg[5].setImageResource(R.drawable.p1);
            }
        };
        task.execute("http://"+ DB.getIP()+"/getbestfood.php");

    }

}
