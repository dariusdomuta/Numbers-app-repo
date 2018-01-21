package com.darius.numbers.screens.year;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darius.numbers.R;
import com.darius.numbers.app.NumbersApp;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.app.pojos.NumberTrivia;
import com.darius.numbers.app.pojos.YearTrivia;
import com.darius.numbers.app.utils.Constants;
import com.darius.numbers.screens.main.MainActivity;
import com.darius.numbers.screens.number.NumberActivity;
import com.darius.numbers.screens.result.ResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by dariu on 12/9/2017.
 */



public class YearActivity extends AppCompatActivity {

    @BindView(R.id.year_bt_go)
    Button yearButtonGo;

    @BindView(R.id.year_edit_text)
    EditText yearEditText;

    private int yearInput;
    private String resultYear;
    private String resultTrivia;
    private String resultType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.year_bt_go)
    public void onButtonGoClicked() {
        if (yearEditText.getText().toString().equals("")) {
            Toast.makeText(YearActivity.this, "Cannot show results without an input", Toast.LENGTH_LONG).show();
        } else {

            yearInput = Integer.parseInt(yearEditText.getText().toString());

            final NumbersApi numbersApi = NumbersApp.getInstance().getNumbersApi();

            Call<YearTrivia> yearTriviaCall = numbersApi.getYearTrivia(yearInput);
            Callback<YearTrivia> yearCallback = new Callback<YearTrivia>() {
                @Override
                public void onResponse(Call<YearTrivia> call, Response<YearTrivia> response) {
                    if (response.isSuccessful()) {
                        YearTrivia yearTrivia = response.body();

                        resultYear = yearTrivia.getNumber().toString();
                        resultTrivia = yearTrivia.getText();
                        resultType = yearTrivia.getType();
                        Intent intent = new Intent(YearActivity.this, ResultActivity.class);

                        intent.putExtra(Constants.K_EXTRA_NUMBER, resultYear);
                        intent.putExtra(Constants.K_EXTRA_DETAILS, resultTrivia);
                        intent.putExtra(Constants.K_EXTRA_TYPE, resultType);

                        startActivity(intent);
                        Timber.d("Date is %s, and year trivia is: %s", yearTrivia.getDate(), yearTrivia.getText());
                    } else {
                        // TODO: handle errors
                    }
                }

                @Override
                public void onFailure(Call<YearTrivia> call, Throwable t) {
                    //TODO: handle network errors
                }
            };
            yearTriviaCall.enqueue(yearCallback);
        }
    }
}
