package com.darius.numbers.screens.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.darius.numbers.R;
import com.darius.numbers.screens.main.MainActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dariu on 12/11/2017.
 */

public class ResultActivity extends AppCompatActivity {


    @BindView(R.id.result_number)
    TextView resultNumber;

    @BindView(R.id.result_info)
    TextView resultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        Intent intent=this.getIntent();
        if (intent != null) {
            String[] passedData = intent.getExtras().getStringArray(MainActivity.EXTRA_DATA_FOR_INTENT);
            resultNumber.setText(passedData[0]);
            resultInfo.setText(passedData[1]);


        }
    }
}
