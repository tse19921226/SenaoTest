package com.example.senaotest;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class StoreItem implements Cloneable, Serializable {
    String price = "";
    String martShortName = "";
    String imageUrl = "";
    String finalPrice = "";
    String martName = "";
    String stockAvailable = "";
    String martId = "";

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMartShortName(String martShortName) {
        this.martShortName = martShortName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setMartName(String martName) {
        this.martName = martName;
    }

    public void setStockAvailable(String stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public void setMartId(String martId) {
        this.martId = martId;
    }

    public String getPrice() {
        return price;
    }

    public String getMartShortName() {
        return martShortName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public String getMartName() {
        return martName;
    }

    public String getStockAvailable() {
        return stockAvailable;
    }

    public String getMartId() {
        return martId;
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

    public static StoreItem loadString(String json) throws Exception{
        return Serialize.JsonToObject(json, StoreItem.class);
    }
}
