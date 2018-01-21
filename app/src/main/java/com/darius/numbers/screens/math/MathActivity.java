package com.darius.numbers.screens.math;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darius.numbers.R;
import com.darius.numbers.app.NumbersApp;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.app.pojos.NumberTrivia;
import com.darius.numbers.app.utils.Constants;
import com.darius.numbers.screens.main.MainActivity;
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

public class MathActivity extends AppCompatActivity {

    @BindView(R.id.math_bt_go)
    Button mathButtonGo;

    @BindView(R.id.math_edit_text)
    EditText mathEditText;

    private String resultNumber;
    private String resultTrivia;
    private int numberInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.math_bt_go)
    public void onButtonGoClicked() {

        if (mathEditText.getText().toString().equals("")) {
            Toast.makeText(MathActivity.this, "Cannot show results without an input", Toast.LENGTH_LONG).show();
        } else {

            numberInput = Integer.parseInt(mathEditText.getText().toString());


            final NumbersApi numbersApi = NumbersApp.getInstance().getNumbersApi();

            Call<NumberTrivia> triviaCall = numbersApi.getMathTrivia(numberInput);

            triviaCall.enqueue(new Callback<NumberTrivia>() {
                @Override
                public void onResponse(Call<NumberTrivia> call, Response<NumberTrivia> response) {
                    if (response.isSuccessful()) {
                        NumberTrivia numberTrivia = response.body();
                        Timber.d("Number trivia! %d: %s", numberTrivia.getNumber(), numberTrivia.getText());

                        resultNumber = numberTrivia.getNumber().toString();
                        resultTrivia = numberTrivia.getText();
                        Intent intent = new Intent(MathActivity.this, ResultActivity.class);

                        intent.putExtra(Constants.K_EXTRA_NUMBER, resultNumber);
                        intent.putExtra(Constants.K_EXTRA_DETAILS, resultTrivia);

                        startActivity(intent);
                    } else {
                        //TODO: handle error
                    }
                }

                @Override
                public void onFailure(Call<NumberTrivia> call, Throwable t) {
                    //TODO: handle error
                }
            });
        }

    }
}
