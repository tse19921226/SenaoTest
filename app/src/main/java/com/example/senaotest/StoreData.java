package com.example.senaotest;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StoreData {
    private ArrayList<StoreItem> data = new ArrayList<>();

    public ArrayList<StoreItem> getData() {
        return data;
    }

    public void setData(ArrayList<StoreItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return Serialize.ObjectToJson(this);
    }

    public static StoreData loadString(String json) throws Exception{
        return Serialize.JsonToObject(json, StoreData.class);
    }
}
