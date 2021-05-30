package com.example.products.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.products.R;
import com.example.products.model.Purchase;
import com.example.products.model.Restaurant;

import java.util.ArrayList;

public class PurchaseAdapter<I extends Purchase> extends BaseAdapter {
    private final Context context;
    private final ArrayList<Purchase> purchases;

    public PurchaseAdapter(Context context, String username) {
        this.context = context;
        this.purchases = Restaurant.getPurchases(username);
    }

    @Override
    public int getCount() {
        return purchases.size();
    }

    @Override
    public Purchase getItem(int position) {
        return purchases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return purchases.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_purchase, parent, false);
        }

        // inflate
        TextView tv_name = convertView.findViewById(R.id.card_purchase_tv_name);
        TextView tv_price = convertView.findViewById(R.id.card_purchase_tv_price);
        TextView tv_date = convertView.findViewById(R.id.card_purchase_tv_date);

        // fill view from object
        Purchase purchase = getItem(position);
        tv_name.setText(purchase.getName());
        tv_price.setText(purchase.getFullPriceString());
        tv_date.setText(purchase.getDateFormatted());

        return convertView;
    }
}
