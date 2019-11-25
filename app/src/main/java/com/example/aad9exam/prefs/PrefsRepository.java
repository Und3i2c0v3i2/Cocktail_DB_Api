package com.example.aad9exam.prefs;



public interface PrefsRepository {

    String TOAST_KEY = "Toast Messages";
    String NOTIF_KEY = "Notifications";
    String LIST_PREF_KEY = "list_pref_key";

    String getListPrefValue(String key);

}
