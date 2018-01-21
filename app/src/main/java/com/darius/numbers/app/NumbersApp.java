package com.darius.numbers.app;

import android.app.Application;

import com.darius.numbers.BuildConfig;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.app.network.interceptors.LoggingInterceptor;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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

        //Timber
        Timber.plant(new Timber.DebugTree());


        //OkHttpClient
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new LoggingInterceptor());

        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        numbersApi = retrofit.create(NumbersApi.class);

        //Realm
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("facts.realm")
                .schemaVersion(2)
                .build();
        Realm.setDefaultConfiguration(config);




    }

    public static NumbersApp getInstance() {
        return instance;
    }

    public NumbersApi getNumbersApi() {
        return numbersApi;
    }
}
