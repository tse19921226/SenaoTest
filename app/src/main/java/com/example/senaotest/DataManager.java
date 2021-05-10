package com.example.senaotest;

import android.content.Context;

public class DataManager {
    public static String senaoUrl = "https://m.senao.com.tw/apis2/test/marttest.jsp";

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
