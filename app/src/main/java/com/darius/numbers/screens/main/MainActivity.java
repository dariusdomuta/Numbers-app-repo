package com.darius.numbers.screens.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.darius.numbers.R;
import com.darius.numbers.app.NumbersApp;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.app.pojos.DateTrivia;
import com.darius.numbers.app.pojos.NumberTrivia;
import com.darius.numbers.app.pojos.YearTrivia;
import com.darius.numbers.screens.date.DateActivity;
import com.darius.numbers.screens.math.MathActivity;
import com.darius.numbers.screens.number.NumberActivity;
import com.darius.numbers.screens.year.YearActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_FOR_INTENT = "Extra data for intent";

    @BindView(R.id.main_bt_number_trivia)
    Button btNumberTrivia;

    @BindView(R.id.main_bt_date_trivia)
    Button btDateTrivia;

    @BindView(R.id.main_bt_year_trivia)
    Button btYearTrivia;

    @BindView(R.id.main_bt_math_facts)
    Button btMathFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final NumbersApi numbersApi = NumbersApp.getInstance().getNumbersApi();


        Callback<YearTrivia> yearCallback = new Callback<YearTrivia>() {
            @Override
            public void onResponse(Call<YearTrivia> call, Response<YearTrivia> response) {
                if (response.isSuccessful()) {
                    YearTrivia yearTrivia = response.body();

                    Timber.d("Date is %s, and year trivia is: %s", yearTrivia.getDate(),yearTrivia.getText());
                } else {
                   // TODO: handle errors
                }
            }

            @Override
            public void onFailure(Call<YearTrivia> call, Throwable t) {
                //TODO: handle network errors
            }
        };

        Call<YearTrivia> yearTriviaCall = numbersApi.getYearTrivia(1989);
        yearTriviaCall.enqueue(yearCallback);

        Callback<NumberTrivia> mathCallback = new Callback<NumberTrivia>() {
            @Override
            public void onResponse(Call<NumberTrivia> call, Response<NumberTrivia> response) {
                if(response.isSuccessful()) {
                    NumberTrivia mathTrivia = response.body();
                    Timber.d("Math trivia is : %s", mathTrivia.getText());
                } else {
                    //TODO: handle errors
                }
            }

            @Override
            public void onFailure(Call<NumberTrivia> call, Throwable t) {
                    //TODO: handle network errors
            }
        };

        Call<NumberTrivia> mathTriviaCall = numbersApi.getMathTrivia(56);
        mathTriviaCall.enqueue(mathCallback);


    }

    @OnClick(R.id.main_bt_date_trivia)
    public void onDateTriviaClicked() {
        Intent intent = new Intent(this, DateActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_bt_number_trivia)
    public void onNumberTriviaClicked() {
        Intent intent = new Intent(this, NumberActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_bt_year_trivia)
    public void onYearTriviaClicked() {
        Intent intent = new Intent(this, YearActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_bt_math_facts)
    public void onMathFactsClicked() {
        Intent intent = new Intent(this, MathActivity.class);
        startActivity(intent);
    }


}

