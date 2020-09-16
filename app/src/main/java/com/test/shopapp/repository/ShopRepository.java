package com.test.shopapp.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.shopapp.data.model.Data;
import com.test.shopapp.data.model.MainResponse;
import com.test.shopapp.data.retrofit.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepository {

    private static ShopRepository instance;
    Context context;
    public RetrofitAPI retrofitAPI;
    private MutableLiveData<Data> allData =new MutableLiveData<>();
    private Data myData =new Data();
    MutableLiveData<Boolean>  progressValue= new MutableLiveData<>();
    private static final String endpointVal = "api/common";


    public ShopRepository(Context context) {
        this.context = context;
    }

    public static ShopRepository getInstance(Context context){
        if(instance==null){
            instance= new ShopRepository(context);
        }
        return instance;
    }

    public LiveData<Boolean> getProgressValue() {
        return progressValue;
    }


    public MutableLiveData<Data> getShopData(){
        progressValue.setValue(true);

        retrofitAPI = RetrofitAPI.Factory.getInstance(context);
        retrofitAPI.responseCall(endpointVal).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if(response.body()!=null&&response.isSuccessful()){
                    progressValue.setValue(false);
                    myData =response.body().getData();
                    if(myData!=null){
                        allData.setValue(myData);
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                progressValue.setValue(false);
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR",t.toString());
            }
        });

        return allData;
    }



}
