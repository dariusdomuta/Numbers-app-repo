package com.darius.numbers.screens.result;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darius.numbers.R;
import com.darius.numbers.app.RealmDB.StoredDateFact;
import com.darius.numbers.app.RealmDB.StoredMathFact;
import com.darius.numbers.app.RealmDB.StoredNumberFact;
import com.darius.numbers.app.RealmDB.StoredYearFact;
import com.darius.numbers.app.utils.Constants;
import com.darius.numbers.screens.main.MainActivity;
import com.darius.numbers.screens.number.NumberActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import org.w3c.dom.Text;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import timber.log.Timber;

/**
 * Created by dariu on 12/11/2017.
 */

public class ResultActivity extends AppCompatActivity {


    @BindView(R.id.result_number)
    TextView resultNumber;

    @BindView(R.id.result_info)
    TextView resultInfo;

    @BindView(R.id.result_bt_share)
    ShareButton resultButtonShare;

    private String sResultNumber;
    private String sResultInfo;
    private String sResultType;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private Context context;

    public String getsResultNumber() {
        return sResultNumber;
    }

    public void setsResultNumber(String sResultNumber) {
        this.sResultNumber = sResultNumber;
    }

    public String getsResultInfo() {
        return sResultInfo;
    }

    public void setsResultInfo(String sResultInfo) {
        this.sResultInfo = sResultInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        context = this;

        Intent intent=this.getIntent();
        if (intent != null) {
            sResultNumber = intent.getStringExtra(Constants.K_EXTRA_NUMBER);
            sResultInfo = intent.getStringExtra(Constants.K_EXTRA_DETAILS);
            sResultType = intent.getStringExtra(Constants.K_EXTRA_TYPE);

            resultNumber.setText(sResultNumber);
            resultInfo.setText(sResultInfo);

        }

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("http://numbersapi.com"))
                .setQuote(getsResultInfo())
                .build();
        resultButtonShare.setShareContent(content);






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_to_favourites, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mShare:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, getsResultInfo() );
                startActivity(Intent.createChooser(intent, "Share Via"));
                Toast.makeText(getApplicationContext(), "You click on menu share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_add_to_favourites:

                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();

                switch (sResultType) {
                    case "date":
                        try {
                            sResultNumber = sResultNumber.replace(" ","");
                            StoredDateFact date = realm.createObject(StoredDateFact.class, sResultNumber);
                            date.setStoredDate(Integer.parseInt(sResultNumber));
                            date.setStoredDateFact(sResultInfo);
                            Toast.makeText(getApplicationContext(), "Fact added to favourites", Toast.LENGTH_SHORT).show();
                        } catch (RealmPrimaryKeyConstraintException e) {
                            Toast.makeText(getApplicationContext(), "Already added to favourites", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case "math":
                        try {
                            final StoredMathFact math = realm.createObject(StoredMathFact.class, sResultNumber);
                            math.setStoredMath(Integer.parseInt(sResultNumber));
                            math.setStoredMathFact(sResultInfo);
                            Toast.makeText(getApplicationContext(), "Fact added to favourites", Toast.LENGTH_SHORT).show();
                        } catch (RealmPrimaryKeyConstraintException e) {
                            Toast.makeText(getApplicationContext(), "Already added to favourites", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case "trivia":
                        try {
                            final StoredNumberFact number = realm.createObject(StoredNumberFact.class, sResultNumber);
                            number.setStoredNumber(Integer.parseInt(sResultNumber));
                            number.setStoredNumberFact(sResultInfo);
                            Toast.makeText(getApplicationContext(), "Fact added to favourites", Toast.LENGTH_SHORT).show();
                        } catch (RealmPrimaryKeyConstraintException e) {
                            Toast.makeText(getApplicationContext(), "Already added to favourites", Toast.LENGTH_SHORT).show();
                        }

                        break;

                    case "year":
                        try{
                            StoredYearFact year = realm.createObject(StoredYearFact.class, sResultNumber);
                            year.setStoredYear(Integer.parseInt(sResultNumber));
                            year.setStoredYearFact(sResultInfo);
                            Toast.makeText(getApplicationContext(), "Fact added to favourites", Toast.LENGTH_SHORT).show();
                        } catch (RealmPrimaryKeyConstraintException e) {
                            Toast.makeText(getApplicationContext(), "Already added to favourites", Toast.LENGTH_SHORT).show();
                    }

                }
                realm.commitTransaction();
                RealmQuery<StoredNumberFact> query = realm.where(StoredNumberFact.class);
                RealmResults<StoredNumberFact> result = query.findAll();
                for (StoredNumberFact iterator : result) {
                    Timber.d("number fact: "+ iterator.getStoredNumberFact() + iterator.getStoredNumber());
                }

        }

        return super.onOptionsItemSelected(item);
    }
}
