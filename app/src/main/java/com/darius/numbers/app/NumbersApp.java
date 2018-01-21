package com.darius.numbers.app;

import android.app.Application;

import com.darius.numbers.BuildConfig;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.app.network.interceptors.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by dariu on 12/6/2017.
 */

public class NumbersApp extends Application {

    private static NumbersApp instance;
    private NumbersApi numbersApi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Timber.plant(new Timber.DebugTree());

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new LoggingInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        numbersApi = retrofit.create(NumbersApi.class);



    }

    public static NumbersApp getInstance() {
        return instance;
    }

    public NumbersApi getNumbersApi() {
        return numbersApi;
    }
}
