package com.test.shopapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.test.shopapp.R;
import com.test.shopapp.adapter.CategoryAdapter;
import com.test.shopapp.adapter.MySliderAdapter;
import com.test.shopapp.adapter.ProductAdapter;
import com.test.shopapp.data.model.Data;
import com.test.shopapp.data.model.MainResponse;
import com.test.shopapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    MainActivityViewModel mainActivityViewModel;
    MainResponse mainResponse = new MainResponse();
    MySliderAdapter sliderAdapter;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    SliderView sliderView;
    RecyclerView categoriesRv;
    RecyclerView productsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
        progressBar=findViewById(R.id.progressBar);
        sliderView=findViewById(R.id.slider_view);
        categoriesRv= findViewById(R.id.categories_recycler_view);
        productsRv= findViewById(R.id.new_products_recycler_view);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);



        mainActivityViewModel.getAllData(this).observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                mainResponse.setData(data);
                if(mainResponse!=null){
                    setupSlider(mainResponse);
                    setupCategory(mainResponse);
                    setupProducts(mainResponse);
                }
            }
        });


        mainActivityViewModel.getProgressValue().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                      progressBar.setVisibility(View.VISIBLE);
                }
                 else progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void setupProducts(MainResponse mainResponse) {
        if(productAdapter==null){
            productAdapter = new ProductAdapter(this,mainResponse.getData());
            GridLayoutManager linearLayoutManager= new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
            productsRv.setLayoutManager(linearLayoutManager);
            productsRv.setAdapter(productAdapter);
        }
        else productAdapter.notifyDataSetChanged();

    }

    private void setupCategory(MainResponse mainResponse) {
        if(categoryAdapter==null){
            categoryAdapter = new CategoryAdapter(this,mainResponse.getData());
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            categoriesRv.setLayoutManager(linearLayoutManager);
            categoriesRv.setAdapter(categoryAdapter);
        }
        else categoryAdapter.notifyDataSetChanged();

    }

    private void setupSlider(MainResponse mainResponse) {
        sliderAdapter=new MySliderAdapter(this,mainResponse.getData());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

}