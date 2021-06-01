package com.example.products.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.products.R;
import com.example.products.model.Purchase;
import com.example.products.model.Restaurant;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {
    private final Context context;
    private final ArrayList<Purchase> purchases;

    public PurchaseAdapter(Context context, String username) {
        this.context = context;
        this.purchases = Restaurant.getPurchases(username);
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View view = LayoutInflater.from(context)
                .inflate(R.layout.card_purchase, parent, false);

        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        Purchase purchase = purchases.get(position);

        holder.tv_name.setText(purchase.getName());
        holder.tv_price.setText(purchase.getFullPriceString());
        holder.tv_date.setText(purchase.getDateFormatted());
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    static class PurchaseViewHolder extends RecyclerView.ViewHolder {
        // inflate
        TextView tv_name;
        TextView tv_price;
        TextView tv_date;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.card_purchase_tv_name);
            tv_price = itemView.findViewById(R.id.card_purchase_tv_price);
            tv_date = itemView.findViewById(R.id.card_purchase_tv_date);
        }
    }
}
