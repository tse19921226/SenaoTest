package com.example.senaotest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DataSync extends AsyncTask<String, String, String> {
    private String TAG = getClass().getSimpleName();

    private Context mCtx;

    public void setmCtx(Context mCtx) {
        this.mCtx = mCtx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            Log.d(TAG, "doInBackground, strings" + strings[0]);
            URL url = new URL(strings[0]);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();
            Log.d(TAG, "conn.getResponseCode = " + conn.getResponseCode());
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String temp;

                while ((temp = reader.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                result = stringBuilder.toString();
            }else  {
                result = "error";
            }
            Log.d(TAG, "result = " + result);
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute, s = " + s);
        try {
            StoreData storeData = new StoreData().loadString(s);
            Log.d(TAG, "data size = " + storeData.getData().size());
            DataManager.getInstance(mCtx).setStoreData(storeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
