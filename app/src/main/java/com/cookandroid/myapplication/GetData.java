package com.cookandroid.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {
    public static String get(String serverURL){
        try{
            URL url = new URL(serverURL);
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
