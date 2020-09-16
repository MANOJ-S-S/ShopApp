package com.test.shopapp.data.retrofit;

import android.content.Context;

import com.test.shopapp.BuildConfig;
import com.test.shopapp.data.model.MainResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    String BASE_URL = "http://iroidtechnologies.in/";

    @GET("friday/index.php")
    Call <MainResponse> responseCall(@Query("route") String route);


    class Factory {
        private static RetrofitAPI service;

        public static RetrofitAPI getInstance(Context context) {
            if (service == null) {
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

                builder.readTimeout(30, TimeUnit.SECONDS);
                builder.connectTimeout(30, TimeUnit.SECONDS);
                builder.writeTimeout(30, TimeUnit.SECONDS);


                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                    builder.addInterceptor(interceptor);
                }

                int cacheSize = 10 * 1024 * 1024; // 10 MiB
                Cache cache = new Cache(context.getCacheDir(), cacheSize);
                builder.cache(cache);

                Retrofit retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(RetrofitAPI.class);
                return service;
            } else {
                return service;
            }
        }

    }

}