package com.darius.numbers.screens.favourites.Preferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.darius.numbers.R;

/**
 * Created by dariu on 1/22/2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.favorites_pref);
    }
}
