package com.example.senaotest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataManager {
    public static String senaoUrl = "https://m.senao.com.tw/apis2/test/marttest.jsp";
    public static String SelectItem_Key = "select_item";

    private static DataManager mInstance;
    private StoreData storeData;

    public static DataManager getInstance(Context context){
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public StoreData getStoreData() {
        return storeData;
    }

    public void setStoreData(StoreData storeData) {
        this.storeData = storeData;
    }

}
