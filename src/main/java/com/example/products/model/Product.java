package com.example.products.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.products.R;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private String price_type;
    private PayType payType;
    private int img;

    public Product(int id, String name, double price, String price_type, PayType payType, int img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.price_type = price_type;
        this.payType = payType;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceString() {
        return price + "";
    }

    public String getFullPriceString() {
        return price + " " + price_type;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Drawable getImgDrawable(Context context) {
        try {
            return context.getResources().getDrawable(this.getImg());
        } catch (Exception ex) {
            return context.getResources().getDrawable(R.drawable.not_found);
        }
    }

    public String getPrice_type() {
        return price_type;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", price_type='" + price_type + '\'' +
                ", pay_type='" + payType + '\'' +
                ", img=" + img +
                '}';
    }
}
