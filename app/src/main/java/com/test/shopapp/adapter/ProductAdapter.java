package com.test.shopapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.shopapp.R;
import com.test.shopapp.data.model.Data;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VideoViewHolder> {

    Context context;
    Data product;

    public ProductAdapter(Context context, Data product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);

        return new VideoViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        holder.productName.setText(product.getFreshProducts().get(position).getName());
        holder.productPrice.setText("Rs"+product.getFreshProducts().get(position).getPrice());
        Glide.with(context).load(product.getFreshProducts().get(position).getImage()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return product.getFreshProducts().size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);

        }


    }

}