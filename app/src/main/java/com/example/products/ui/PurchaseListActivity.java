package com.example.products.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;
import com.example.products.adapter.PurchaseAdapter;

public class PurchaseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_list);

        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = dsp.getString("username", "");

        ListView lv_list = findViewById(R.id.purchaseList_lv_list);
        lv_list.setAdapter(new PurchaseAdapter<>(this, username));
    }
}