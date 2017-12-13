package com.darius.numbers.app.network;

import com.darius.numbers.app.pojos.DateTrivia;
import com.darius.numbers.app.pojos.NumberTrivia;
import com.darius.numbers.app.pojos.YearTrivia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by dariu on 12/6/2017.
 */

public interface NumbersApi {

    @Headers("Cache-Control: max-age=640000")
    @GET("/{number}?json")
    Call<NumberTrivia> getNumberTrivia(@Path("number") int number);

    @GET("/{month}/{day}/date?json")
    Call<DateTrivia> getDateTrivia(@Path("month") int month, @Path("day") int day);

    @GET("/{year}/year?json")
    Call<YearTrivia> getYearTrivia(@Path("year") int year);

    @GET("/{number}/math?json")
    Call<NumberTrivia> getMathTrivia(@Path("number") int number);

}
