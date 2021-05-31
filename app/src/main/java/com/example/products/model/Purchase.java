package com.example.products.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Purchase {
    private int id;
    private String userName;
    private String name;
    private Date date;
    private double full_price;

    protected Purchase(int id, String userName, String name, Date date, double full_price) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.date = date;
        this.full_price = full_price;
    }

    public Purchase(String userName, String name, double full_price) {
        this.id = -1;
        this.userName = userName;
        this.name = name;
        this.date = Calendar.getInstance().getTime();
        this.full_price = full_price;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(getDate());
    }

    public double getFullPrice() {
        return full_price;
    }

    public String getFullPriceString() {
        return full_price + "₪";
    }

    public void setFull_price(double full_price) {
        this.full_price = full_price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", full_price=" + full_price +
                '}';
    }
}
