package com.darius.numbers.screens.date;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.darius.numbers.R;
import com.darius.numbers.app.NumbersApp;
import com.darius.numbers.app.network.NumbersApi;
import com.darius.numbers.app.pojos.DateTrivia;
import com.darius.numbers.app.utils.Constants;
import com.darius.numbers.screens.main.MainActivity;
import com.darius.numbers.screens.number.NumberActivity;
import com.darius.numbers.screens.result.ResultActivity;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

public class DateActivity extends AppCompatActivity {

    @BindView(R.id.date_bt_set)
    Button dateButtonSet;

    @BindView(R.id.date_bt_go)
    Button goButton;

    @BindView(R.id.date_tv_selected)
    TextView dateSelectedTextView;


    private int selectedMonth;
    private int selectedDay;
    private String resultDate;
    private String resultDateTrivia;
    private String resultType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.date_bt_set)
    public void OnSetDateClicked() {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DateFormat df = SimpleDateFormat.getDateInstance();

                selectedDay = myCalendar.get(Calendar.DAY_OF_MONTH);
                selectedMonth = myCalendar.get(Calendar.MONTH) + 1;

                dateSelectedTextView.setText(df.format(myCalendar.getTime()));
            }

        };

        new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @OnClick(R.id.date_bt_go)
    public void OnGoButtonClicked() {
        final NumbersApi numbersApi = NumbersApp.getInstance().getNumbersApi();
        Call<DateTrivia> dateTriviaCall = numbersApi.getDateTrivia(selectedMonth, selectedDay);

        Callback<DateTrivia> dateCallback = new Callback<DateTrivia>() {
            @Override
            public void onResponse(Call<DateTrivia> call, Response<DateTrivia> response) {
                if (response.isSuccessful()) {
                    DateTrivia dateTrivia = response.body();
                    Timber.d("Date trivia: %s", dateTrivia.getText());

                    Intent intent = new Intent(DateActivity.this, ResultActivity.class);

                    resultDate = String.format("%02d %02d", selectedMonth, selectedDay);
                    resultDateTrivia = dateTrivia.getText();
                    resultType = dateTrivia.getType();

                    intent.putExtra(Constants.K_EXTRA_NUMBER, resultDate);
                    intent.putExtra(Constants.K_EXTRA_DETAILS, resultDateTrivia);
                    intent.putExtra(Constants.K_EXTRA_TYPE, resultType);
                    startActivity(intent);

                } else {
                    // TODO: handle errors
                }
            }

            @Override
            public void onFailure(Call<DateTrivia> call, Throwable t) {
                // TODO: handle network errors
            }
        };
        dateTriviaCall.enqueue(dateCallback);
    }
}
