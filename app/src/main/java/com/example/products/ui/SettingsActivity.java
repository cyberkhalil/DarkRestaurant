package com.example.products.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;
import com.example.products.model.Purchase;
import com.example.products.model.Restaurant;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // get needed views by id
        LinearLayout ll_3 = findViewById(R.id.settings_ll_3);

        // show add item if account is administrator
        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        if (dsp.getBoolean("is_admin", false)) {
            ll_3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAllPurchases(View view) {
        Intent intent = new Intent(this, PurchaseListActivity.class);
        this.startActivity(intent);
    }

    public void showLastPurchase(View view) {
        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = dsp.getString("username", "");
        ArrayList<Purchase> purchases = Restaurant.getPurchases(username);

        if (purchases.size() >= 1) {
            Purchase purchase = purchases.get(purchases.size() - 1);
            Toast.makeText(this, "آخر عملية شراء: " + purchase.getName()
                    , Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "لم تقم بأي عمليات شراء مؤخراً",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void changePassword(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("password change");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("set password", (dialog, which) -> {
            String text = input.getText().toString();
            SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = dsp.edit();
            edit.putString("pass", text);
            edit.apply();
            Toast.makeText(this, "password changed successfully", Toast.LENGTH_LONG)
                    .show();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void clearAllPurchases(View view) {
        Restaurant.clearPurchases();
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainListActivity.class);
        startActivity(intent);
        this.finish();
    }
}