package com.example.aad9exam.prefs;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.aad9exam.R;

import static com.example.aad9exam.prefs.PrefsRepository.LIST_PREF_KEY;

public class PrefsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private SharedPreferences.OnSharedPreferenceChangeListener listener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    //update list preference summary
                    if (key.equals(LIST_PREF_KEY)) {
                        String info_type = sharedPreferences.getString(key, "");
                        Preference pref = findPreference(LIST_PREF_KEY);
                        if (pref != null)
                            pref.setSummary(info_type);
                    }
                }
            };


    /* REGISTER & UNREGISTER LISTENER */
    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);

        Preference pref = findPreference(LIST_PREF_KEY);
        if (pref != null)
            pref.setSummary(getPreferenceScreen().getSharedPreferences().getString(LIST_PREF_KEY, ""));

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }



}
