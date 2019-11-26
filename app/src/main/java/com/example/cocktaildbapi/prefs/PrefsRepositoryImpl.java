package com.example.cocktaildbapi.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;


public class PrefsRepositoryImpl implements PrefsRepository {

    private SharedPreferences sharedPreferences;


    public PrefsRepositoryImpl(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
   }


    @Override
    public String getListPrefValue(String key) {
        return sharedPreferences.getString(key, "");
    }

}
