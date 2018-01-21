package com.darius.numbers.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.darius.numbers.R;
import com.darius.numbers.app.NumbersApp;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.screens.date.DateActivity;
import com.darius.numbers.screens.math.MathActivity;
import com.darius.numbers.screens.number.NumberActivity;
import com.darius.numbers.screens.year.YearActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // public static final String EXTRA_DATA_FOR_INTENT = "Extra data for intent";

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

