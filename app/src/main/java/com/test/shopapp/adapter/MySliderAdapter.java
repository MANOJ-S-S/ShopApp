package com.test.shopapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.test.shopapp.R;
import com.test.shopapp.data.model.Data;

import java.util.ArrayList;
import java.util.List;

public class MySliderAdapter extends SliderViewAdapter<MySliderAdapter.SliderViewHolder> {

    Context context;
    Data sliderData;

    public MySliderAdapter(Context context, Data data) {
        this.context = context;
        this.sliderData = data;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        Glide.with(context).load(sliderData.getBanners().get(position).getImage()).into(viewHolder.banner_image);
    }

    @Override
    public int getCount() {
        return sliderData.getBanners().size();
    }

    static class SliderViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView banner_image;
        View view;

        public SliderViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            banner_image=itemView.findViewById(R.id.advertise_image);
        }
    }
}
