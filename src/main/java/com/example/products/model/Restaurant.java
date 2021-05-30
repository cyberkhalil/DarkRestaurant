package com.example.products.model;

import com.example.products.R;

import java.util.ArrayList;
import java.util.Calendar;

public final class Restaurant {
    // TODO remove when use database
    private static final ArrayList<Product> products = new ArrayList<>();
    // TODO remove when use database
    private static ArrayList<Purchase> purchases = new ArrayList<>();

    static {
        products.add(new Product(0, "فتة غزاوية مع دجاج فرن", 100,
                "شيكل", PayType.CASH, R.drawable.img_1));
        products.add(new Product(1, "بطاطا مقلية", 20,
                "شيكل", PayType.TAKSEET, 1));
        products.add(new Product(2, "باذنجان محمر", 15,
                "شيكل", PayType.BOTH, R.drawable.not_found));
        purchases.add(new Purchase(0, "test", "بطاطا مقلية",
                Calendar.getInstance().getTime(), 5.6));
    }

    // prevent construct
    private Restaurant() {
    }

    public static ArrayList<Product> getProducts() {
        // TODO get products from database
        return new ArrayList<>(products);
    }

    public static ArrayList<Purchase> getPurchases(String username) {
        // TODO get user purchases from database

        ArrayList<Purchase> userPurchases = new ArrayList<>();
        for (Purchase purchase : purchases) {
            if (purchase.getUserName().equals(username)) userPurchases.add(purchase);
        }
        return userPurchases;
    }

    public static void addPurchase(Purchase purchase) {
        // TODO add purchase to database
        purchases.add(purchase);
    }

    public static void clearPurchases() {
        // TODO clear purchases from database
        purchases = new ArrayList<>();
    }

    public static void addProduct(Product product) {
        // TODO add product to database
        products.add(product);
    }

}
