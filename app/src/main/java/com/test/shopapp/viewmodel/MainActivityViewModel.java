package com.test.shopapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.shopapp.data.model.Data;
import com.test.shopapp.repository.ShopRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private ShopRepository shopRepository;
    private MutableLiveData<Data> allData;
    private LiveData<Boolean> progressValue;

    public LiveData<Data> getAllData(Context context){
        allData= new MutableLiveData<>();
        shopRepository= ShopRepository.getInstance(context);
        allData = shopRepository.getShopData();
        return allData;
    }

    public LiveData<Boolean> getProgressValue(){
        if(progressValue==null){
            progressValue= shopRepository.getProgressValue();
        }
        return progressValue;
    }
}
