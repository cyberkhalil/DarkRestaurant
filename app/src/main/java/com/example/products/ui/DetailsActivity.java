package com.example.products.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;
import com.example.products.model.Product;
import com.example.products.model.Purchase;
import com.example.products.model.Restaurant;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // display back key on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get needed Views by id
        Button btn_save = findViewById(R.id.details_btn_save);
        Button btn_increase_number = findViewById(R.id.details_btn_increase_number);
        Button btn_decrease_number = findViewById(R.id.details_btn_decrease_number);

        TextView tv_products_full_price = findViewById(R.id.details_tv_products_full_price);
        TextView tv_product_full_price = findViewById(R.id.details_tv_product_full_price);

        EditText et_products_number = findViewById(R.id.details_et_products_number);

        ImageView iv_product_img = findViewById(R.id.details_iv_product_img);


        // get product object
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        // fill information
        getSupportActionBar().setTitle(product.getName());
        tv_product_full_price.setText(product.getFullPriceString());
        tv_products_full_price.setText(product.getFullPriceString());
        iv_product_img.setImageDrawable(product.getImgDrawable(this));

        // set listeners actions
        btn_save.setOnClickListener(v -> {

            // get full price
            int number = Integer.parseInt(et_products_number.getText().toString());
            double price = product.getPrice();
            double full_price = price * number;

            // get username from default shared preferences
            SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
            String username = dsp.getString("username", "");

            Restaurant.addPurchase(new Purchase(username, product.getName(), full_price));
            Toast.makeText(this, "تم حفظ المنتج بنجاح", Toast.LENGTH_LONG).show();
            this.finish();
        });

        btn_increase_number.setOnClickListener(v -> {
            int old = Integer.parseInt(et_products_number.getText().toString());
            et_products_number.setText((old + 1) + "");
        });
        btn_decrease_number.setOnClickListener(v -> {
            int old = Integer.parseInt(et_products_number.getText().toString());
            if (old == 0) return;
            et_products_number.setText((old - 1) + "");
        });

        et_products_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                double price = product.getPrice();

                double all_price = price * number;

                tv_products_full_price.setText(all_price + " " + product.getPrice_type());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}