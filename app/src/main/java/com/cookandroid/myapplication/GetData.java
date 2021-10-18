package com.cookandroid.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData extends AsyncTask<String, Void, String> {
    protected void onPreExecute(){
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
    @Override
    protected String doInBackground(String... params) {

        try{
            URL url = new URL((String)params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("TAG", "GET response code - "+responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                inputStream = httpURLConnection.getErrorStream();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            String s = null;
            DB.clearDataList();

            int i = 0;
            while((line = bufferedReader.readLine()) != null){
                if(i==0) s = line;
                DB.addData(line);
                Log.d("getData", DB.getData(i).toString());
                i++;
            }
            bufferedReader.close();
            return s;

        }catch(Exception e){
            Log.d("TAG", "InsertData: Error ",e);
            return new String("Error: "+e.getMessage());
        }
    }
}
