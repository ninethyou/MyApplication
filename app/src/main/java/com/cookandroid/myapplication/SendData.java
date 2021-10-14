package com.cookandroid.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class SendData extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    Activity activity;

    SendData(Activity a){
        this.activity = a;
    }

    protected void onPreExecute(){
        super.onPreExecute();

        progressDialog = ProgressDialog.show(activity,"Pleas Wait", null, true, true);
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();
        Log.d("php", "POST resaponse - "+s);
    }
    protected String doInBackground(String... params){
        String serverURL = (String) params[0];
        String postParameters = (String) params[1];

        try{
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("TAG", "POST response code - "+responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == httpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line=bufferedReader.readLine()) != null){
                sb.append(line);
            }

            bufferedReader.close();

            return sb.toString();

        }catch(Exception e){
            Log.d("TAG", "insertData: Error ", e);

            return new String("Error: "+e.getMessage());
        }
    }
}

