package com.example.products.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.products.R;
import com.example.products.model.PayType;
import com.example.products.model.Product;
import com.example.products.model.Restaurant;

import java.util.ArrayList;

public class ProductAdapter<I extends Product> extends BaseAdapter implements Filterable {
    private final Context context;
    private final ArrayList<Product> products;
    private ArrayList<Product> active_products;
    private PayType filterPayType = PayType.BOTH;

    public ProductAdapter(Context context) {
        this.context = context;
        this.products = Restaurant.getProducts();
        this.active_products = Restaurant.getProducts();
    }

    @Override
    public int getCount() {
        return active_products.size();
    }

    @Override
    public Product getItem(int position) {
        return active_products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return active_products.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_product, parent, false);
        }

        // inflate
        TextView tv_name = convertView.findViewById(R.id.card_product_tv_name);
        ImageView iv_img = convertView.findViewById(R.id.card_product_iv_img);
        TextView tv_pay_type = convertView.findViewById(R.id.card_product_tv_pay_type);
        TextView tv_price = convertView.findViewById(R.id.card_product_tv_price);
        TextView tv_price_type = convertView.findViewById(R.id.card_product_tv_price_type);

        // fill view from object
        Product product = getItem(position);
        tv_name.setText(product.getName());
        iv_img.setImageDrawable(product.getImgDrawable(context));
        tv_pay_type.setText(product.getPayType().toString());
        tv_price.setText(product.getPriceString());
        tv_price_type.setText(product.getPrice_type());

        return convertView;
    }

    public PayType getFilterPayType() {
        return filterPayType;
    }

    public void setFilterPayType(PayType filterPayType) {
        this.filterPayType = filterPayType;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Product> filteredList = new ArrayList<>(products);

                if (getFilterPayType() != PayType.BOTH) {
                    for (int i = 0; i < filteredList.size(); i++) {
                        Product p = filteredList.get(i);
                        if (p.getPayType() != getFilterPayType()) {
                            filteredList.remove(p);
                        }
                    }
                }

                if (constraint != null && constraint.length() != 0) {
                    for (int i = 0; i < filteredList.size(); i++) {
                        Product p = filteredList.get(i);
                        if (!p.getName().contains(constraint)) {
                            filteredList.remove(p);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                active_products = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
