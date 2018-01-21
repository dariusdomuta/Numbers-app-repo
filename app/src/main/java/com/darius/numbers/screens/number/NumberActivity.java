package com.darius.numbers.screens.number;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class NumberActivity extends AppCompatActivity {

    private static final String TAG = "NumberActivity";
    @BindView(R.id.number_edit_text)
    EditText numberEditText;

    @BindView(R.id.number_bt_go)
    Button numberButtonGo;


    private int numberInput;
    private String resultNumber;
    private String resultTrivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.number_bt_go)
    public void onButtonGoClicked() {

        if (numberEditText.getText().toString().equals("")) {
            Toast.makeText(NumberActivity.this, "Cannot show results without an input", Toast.LENGTH_LONG).show();
        } else {

            numberInput = Integer.parseInt(numberEditText.getText().toString());


            final NumbersApi numbersApi = NumbersApp.getInstance().getNumbersApi();

            Call<NumberTrivia> triviaCall = numbersApi.getNumberTrivia(numberInput);

            triviaCall.enqueue(new Callback<NumberTrivia>() {
                @Override
                public void onResponse(Call<NumberTrivia> call, Response<NumberTrivia> response) {
                    if (response.isSuccessful()) {
                        NumberTrivia numberTrivia = response.body();
                        Timber.d("Number trivia! %d: %s", numberTrivia.getNumber(), numberTrivia.getText());

                        resultNumber = numberTrivia.getNumber().toString();
                        resultTrivia = numberTrivia.getText();
                        Intent intent = new Intent(NumberActivity.this, ResultActivity.class);

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

