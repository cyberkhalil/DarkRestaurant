package com.example.products.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.products.R;
import com.example.products.model.PayType;
import com.example.products.model.Product;
import com.example.products.model.Restaurant;
import com.example.products.ui.DetailsActivity;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
        implements Filterable {
    private final ArrayList<Product> products;
    Context context;
    private ArrayList<Product> active_products;
    private PayType filterPayType = PayType.BOTH;

    public ProductAdapter(Context context) {
        this.context = context;
        products = Restaurant.getProducts();
        active_products = Restaurant.getProducts();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the layout
        View view = LayoutInflater.from(context)
                .inflate(R.layout.card_product, parent, false);

        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ProductViewHolder viewHolder, final int position) {

        Product product = active_products.get(position);
        viewHolder.tv_name.setText(product.getName());
        viewHolder.iv_img.setImageDrawable(product.getImgDrawable(context));
        viewHolder.tv_pay_type.setText(product.getPayType().toString());
        viewHolder.tv_price.setText(product.getPriceString());
        viewHolder.tv_price_type.setText(product.getPrice_type());

        viewHolder.itemView.setOnClickListener((view) -> {
            // details listener
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return active_products.size();
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

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_img;
        TextView tv_pay_type;
        TextView tv_price;
        TextView tv_price_type;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            // inflate
            tv_name = itemView.findViewById(R.id.card_product_tv_name);
            iv_img = itemView.findViewById(R.id.card_product_iv_img);
            tv_pay_type = itemView.findViewById(R.id.card_product_tv_pay_type);
            tv_price = itemView.findViewById(R.id.card_product_tv_price);
            tv_price_type = itemView.findViewById(R.id.card_product_tv_price_type);
        }
    }
}
