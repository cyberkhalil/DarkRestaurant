package com.example.products.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;
import com.example.products.model.PayType;
import com.example.products.model.Product;
import com.example.products.model.Restaurant;

public class AddItemActivity extends AppCompatActivity {

    ImageView iv_item_img;
    EditText et_item_name, et_item_price;
    RadioGroup rg_item_pay_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        iv_item_img = findViewById(R.id.addItem_iv_item_img);

        et_item_name = findViewById(R.id.addItem_et_item_name);
        et_item_price = findViewById(R.id.addItem_et_item_price);

        rg_item_pay_type = findViewById(R.id.addItem_rg_item_pay_type);
    }

    public void addProduct(View view) {
        int productImg = R.drawable.not_found;
        if (iv_item_img.getTag() != null) {
            productImg = (int) iv_item_img.getTag();
        }
        String productName = et_item_name.getText().toString();
        double productPrice = Double.parseDouble(et_item_price.getText().toString());

        if (productName.isEmpty()) {
            Toast.makeText(this, "Please enter item name", Toast.LENGTH_LONG).show();
            return;
        }
        if (productPrice <= 0) {
            Toast.makeText(this, "Item price must be more than 0", Toast.LENGTH_LONG).show();
            return;
        }

        PayType productPayType;
        if (rg_item_pay_type.getCheckedRadioButtonId() == R.id.addItem_rb_item_cash_type_cash) {
            productPayType = PayType.CASH;
        } else if (rg_item_pay_type.getCheckedRadioButtonId() == R.id.addItem_rb_item_cash_type_takseet) {
            productPayType = PayType.TAKSEET;
        } else {
            productPayType = PayType.BOTH;
        }

        Product product = new Product(3, productName, productPrice,
                "شيكل", productPayType, productImg);
        Restaurant.addProduct(product);
        Toast.makeText(this, "Item added successfully", Toast.LENGTH_LONG).show();
        this.finish();
    }
}