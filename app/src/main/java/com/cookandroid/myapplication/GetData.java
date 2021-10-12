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

            StringBuilder sb = new StringBuilder();
            String line = null;

            int i = 0;
            Data.menulist.clear();

            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
                if(i>=2){
                    Data.menulist.add(line);
                    Log.d("menu", Data.menulist.get(i-2).toString());
                }
                i++;
            }
            bufferedReader.close();
            return sb.toString();

        }catch(Exception e){
            Log.d("TAG", "InsertData: Error ",e);
            return new String("Error: "+e.getMessage());
        }
    }
}
