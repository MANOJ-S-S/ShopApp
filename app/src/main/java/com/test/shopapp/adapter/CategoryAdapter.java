package com.test.shopapp.adapter;

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

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VideoViewHolder> {

    Context context;
    Data categoryData;

    public CategoryAdapter(Context context, Data categoryData) {
        this.context = context;
        this.categoryData = categoryData;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout,parent,false);

        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        holder.categoryTv.setText(categoryData.getCategories().get(position).getName());
        Glide.with(context).load(categoryData.getCategories().get(position).getImage()).into(holder.categoryImg);
    }

    @Override
    public int getItemCount() {
        return categoryData.getCategories().size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImg;
        TextView categoryTv;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg=itemView.findViewById(R.id.category_image);
            categoryTv=itemView.findViewById(R.id.category_name);

        }


    }

}
