package com.example.products.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;
import com.example.products.adapter.ProductAdapter;
import com.example.products.model.PayType;
import com.example.products.model.Product;

public class MainListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get needed views by id
        LinearLayout ll_cash_filter = findViewById(R.id.main_ll_pay_type);

        ListView lv_products = findViewById(R.id.main_lv_products);

        SearchView sv = findViewById(R.id.main_sv);

        CheckBox cb_enable_cash = findViewById(R.id.main_cb_enable_cash);
        CheckBox cb_enable_takseet = findViewById(R.id.main_cb_enable_takseet);

        // create and set products adapter in the list view
        ProductAdapter<Product> productAdapter = new ProductAdapter<>(this);
        lv_products.setAdapter(productAdapter);

        // set listeners actions
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter().filter(newText);
                return false;
            }
        });
        sv.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ll_cash_filter.setVisibility(View.VISIBLE);
            } else {
                ll_cash_filter.setVisibility(View.GONE);
                cb_enable_cash.setChecked(false);
                cb_enable_takseet.setChecked(false);
            }
        });

        cb_enable_cash.setOnCheckedChangeListener((buttonView, isChecked) -> {
            filter(productAdapter, sv.getQuery(), isChecked, cb_enable_takseet.isChecked());
        });
        cb_enable_takseet.setOnCheckedChangeListener((buttonView, isChecked) -> {
            filter(productAdapter, sv.getQuery(), cb_enable_cash.isChecked(), isChecked);
        });

        lv_products.setOnItemClickListener((parent, view, position, id) -> {
            // details listener
            Product product = (Product) lv_products.getAdapter().getItem(position);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("product", product);
            this.startActivity(intent);
        });

    }

    public void filter(ProductAdapter<Product> adapter, CharSequence constraint,
                       boolean enableCash, boolean enableTakseet) {
        if ((enableCash && enableTakseet) || (!enableCash && !enableTakseet)) {
            adapter.setFilterPayType(PayType.BOTH);
        } else if (enableCash) {
            adapter.setFilterPayType(PayType.CASH);
        } else adapter.setFilterPayType(PayType.TAKSEET);
        adapter.getFilter().filter(constraint);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_item_settings: {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                this.finish();
            }
            return true;
            case R.id.main_menu_item_logout: {
                // remove logged-in from default shared preferences
                SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit = dsp.edit();
                edit.putBoolean("logged", false);
                edit.apply();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                this.finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}