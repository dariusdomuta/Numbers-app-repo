package com.darius.numbers.screens.favourites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.darius.numbers.R;
import com.darius.numbers.app.RealmModels.DateModel;
import com.darius.numbers.app.RealmModels.MathModel;
import com.darius.numbers.app.RealmModels.NumberModel;
import com.darius.numbers.app.RealmModels.YearModel;
import com.darius.numbers.app.adapters.DateAdapter;
import com.darius.numbers.app.adapters.MathAdapter;
import com.darius.numbers.app.adapters.NumberAdapter;
import com.darius.numbers.app.adapters.YearAdapter;
import com.darius.numbers.screens.favourites.Preferences.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by dariu on 1/22/2018.
 */

public class FavoritesActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private YearAdapter yearAdapter;

    private String displayedFactType;
    private String displayedSortOrder;

    RealmResults<NumberModel> queryNumbersResults;
    RealmResults<YearModel> queryYearsResults;
    RealmResults<MathModel> queryMathResults;
    RealmResults<DateModel> queryDateResults;

    public String getDisplayedFactType() {
        return displayedFactType;
    }

    public void setDisplayedFactType(String displayedFactType) {
        this.displayedFactType = displayedFactType;
    }

    public String getDisplayedSortOrder() {
        return displayedSortOrder;
    }

    public void setDisplayedSortOrder(String displayedSortOrder) {
        this.displayedSortOrder = displayedSortOrder;
    }

    @BindView(R.id.favorites_list)
    RecyclerView itemsList;

    @BindView(R.id.favorites_empty_view)
    TextView emptyStateTextView;

    @BindView(R.id.favorites_loading_indicator)
    ProgressBar loadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ButterKnife.bind(this);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        queryNumbersResults = realm.where(NumberModel.class).findAllAsync();
        queryYearsResults = realm.where(YearModel.class).findAllAsync();
        queryMathResults = realm.where(MathModel.class).findAllAsync();
        queryDateResults = realm.where(DateModel.class).findAllAsync();
        realm.commitTransaction();

        loadingIndicator.setVisibility(View.GONE);

        itemsList.setHasFixedSize(false);

        yearAdapter = new YearAdapter(getApplicationContext(), queryYearsResults);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        itemsList.setLayoutManager(layoutManager);

        setupSharedPreferences();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorites_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_fact_key))) {
            loadFactTypeFromPreferences(sharedPreferences);
            if (getDisplayedFactType().equals("number")){
                try {
                if (queryNumbersResults.get(0) != null) {
                    NumberAdapter numberAdapter = new NumberAdapter(getApplicationContext(), queryNumbersResults);
                    itemsList.setAdapter(numberAdapter);
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite number facts", Toast.LENGTH_SHORT).show();
                }

            } else if(getDisplayedFactType().equals("year")) {
                try {
                    if (queryYearsResults.get(0) != null) {
                        YearAdapter yearAdapter = new YearAdapter(getApplicationContext(), queryYearsResults);
                        itemsList.setAdapter(yearAdapter);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite year facts", Toast.LENGTH_SHORT).show();
                }

            } else if (getDisplayedFactType().equals("math")) {
                try {
                    if (queryMathResults.get(0) != null) {
                        MathAdapter mathAdapter = new MathAdapter(getApplicationContext(), queryMathResults);
                        itemsList.setAdapter(mathAdapter);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite math facts", Toast.LENGTH_SHORT).show();
                }
            } else if (getDisplayedFactType().equals("date")) {
                try {
                if (queryDateResults.get(0) != null) {
                    DateAdapter dateAdapter = new DateAdapter(getApplicationContext(), queryDateResults);
                    itemsList.setAdapter(dateAdapter);
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite date facts", Toast.LENGTH_SHORT).show();
                }

            }
        } else if (key.equals(getString(R.string.pref_sort_key))) {
            loadSortTypeFromPreferences(sharedPreferences);
            if (getDisplayedFactType().equals("number")){
                try {
                    if (queryNumbersResults.get(0) != null) {
                        if (getDisplayedSortOrder().equals("ascending")) {
                            queryNumbersResults = queryNumbersResults.sort("storedNumber", Sort.ASCENDING);
                        } else {
                            queryNumbersResults = queryNumbersResults.sort("storedNumber", Sort.DESCENDING);
                        }
                        NumberAdapter numberAdapter = new NumberAdapter(getApplicationContext(), queryNumbersResults);
                        itemsList.setAdapter(numberAdapter);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite number facts", Toast.LENGTH_SHORT).show();
                }

            } else if(getDisplayedFactType().equals("year")) {
                try {
                    if (queryYearsResults.get(0) != null) {
                        if (getDisplayedSortOrder().equals("ascending")) {
                            queryYearsResults = queryYearsResults.sort("storedYear", Sort.ASCENDING);
                        } else {
                            queryYearsResults = queryYearsResults.sort("storedYear", Sort.DESCENDING);
                        }
                        YearAdapter yearAdapter = new YearAdapter(getApplicationContext(), queryYearsResults);
                        itemsList.setAdapter(yearAdapter);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite year facts", Toast.LENGTH_SHORT).show();
                }

            } else if (getDisplayedFactType().equals("math")) {
                try {
                    if (queryMathResults.get(0) != null) {
                        if (getDisplayedSortOrder().equals("ascending")) {
                            queryMathResults = queryMathResults.sort("storedMath", Sort.ASCENDING);
                        } else {
                            queryMathResults = queryMathResults.sort("storedMath", Sort.DESCENDING);
                        }
                        MathAdapter mathAdapter = new MathAdapter(getApplicationContext(), queryMathResults);
                        itemsList.setAdapter(mathAdapter);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite math facts", Toast.LENGTH_SHORT).show();
                }
            } else if (getDisplayedFactType().equals("date")) {
                try {
                    if (queryDateResults.get(0) != null) {
                        if (getDisplayedSortOrder().equals("ascending")) {
                            queryDateResults = queryDateResults.sort("storedDate", Sort.ASCENDING);
                        } else {
                            queryDateResults = queryDateResults.sort("storedDate", Sort.DESCENDING);
                        }
                        DateAdapter dateAdapter = new DateAdapter(getApplicationContext(), queryDateResults);
                        itemsList.setAdapter(dateAdapter);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(this, "You don't have any favorite date facts", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadFactTypeFromPreferences(sharedPreferences);
        loadSortTypeFromPreferences(sharedPreferences);

        if (getDisplayedFactType().equals("number")){
            try {
                if (queryNumbersResults.get(0) != null) {
                    NumberAdapter numberAdapter = new NumberAdapter(getApplicationContext(), queryNumbersResults);
                    itemsList.setAdapter(numberAdapter);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Toast.makeText(this, "You don't have any favorite number facts", Toast.LENGTH_SHORT).show();
            }

        } else if(getDisplayedFactType().equals("year")) {
            try {
                if (queryYearsResults.get(0) != null) {
                    YearAdapter yearAdapter = new YearAdapter(getApplicationContext(), queryYearsResults);
                    itemsList.setAdapter(yearAdapter);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Toast.makeText(this, "You don't have any favorite year facts", Toast.LENGTH_SHORT).show();
            }

        } else if (getDisplayedFactType().equals("math")) {
            try {
                if (queryMathResults.get(0) != null) {
                    MathAdapter mathAdapter = new MathAdapter(getApplicationContext(), queryMathResults);
                    itemsList.setAdapter(mathAdapter);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Toast.makeText(this, "You don't have any favorite math facts", Toast.LENGTH_SHORT).show();
            }
        } else if (getDisplayedFactType().equals("date")) {
            try {
                if (queryDateResults.get(0) != null) {
                    DateAdapter dateAdapter = new DateAdapter(getApplicationContext(), queryDateResults);
                    itemsList.setAdapter(dateAdapter);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Toast.makeText(this, "You don't have any favorite date facts", Toast.LENGTH_SHORT).show();
            }

        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }
    private void loadFactTypeFromPreferences(SharedPreferences sharedPreferences) {
        setDisplayedFactType(sharedPreferences.getString(getString(R.string.pref_fact_key),
                getString(R.string.pref_fact_value_numbers)));
    }

    private void loadSortTypeFromPreferences(SharedPreferences sharedPreferences) {
        setDisplayedSortOrder(sharedPreferences.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_value_ascending)));
    }
}
