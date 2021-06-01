package com.example.products.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.products.R;
import com.example.products.adapter.PurchaseAdapter;

public class PurchaseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_list);

        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = dsp.getString("username", "");

        RecyclerView rv_purchases = findViewById(R.id.purchaseList_rv_purchases);
        rv_purchases.setAdapter(new PurchaseAdapter(this, username));
    }
}