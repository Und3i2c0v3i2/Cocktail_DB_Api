package com.example.cocktaildbapi.view.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;

import com.example.cocktaildbapi.App;
import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.prefs.PrefsRepository;
import com.example.cocktaildbapi.util.AboutDialogUtil;
import com.example.cocktaildbapi.util.ConfirmationDialogUtil;
import com.example.cocktaildbapi.util.NotificationsUtil;
import com.example.cocktaildbapi.util.ToastUtil;
import com.example.cocktaildbapi.view.RepositoryImpl;

import static com.example.cocktaildbapi.prefs.PrefsRepository.LIST_PREF_KEY;
import static com.example.cocktaildbapi.prefs.PrefsRepository.NOTIF_KEY;
import static com.example.cocktaildbapi.prefs.PrefsRepository.TOAST_KEY;
import static com.example.cocktaildbapi.util.AboutDialogUtil.isAboutShowing;


public class BaseActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {


    /* for fragment transaction backstack so we can navigate */
    public static final String HOME_FRAGMENT = "home_fragment";
    public static final String DETAILS_FRAGMENT = "details_fragment";
    public static final String CHILD_DETAILS_FRAGMENT = "dring_details";


    protected PrefsRepository prefsRepository;
    public RepositoryImpl repository;

    protected AlertDialog aboutDialog;
    protected AlertDialog confirmationDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsRepository = App.getPrefsRepository();
    }


    /* ************* FRAGMENT TRANSACTION *********** */
    protected void fragmentTransaction(Fragment fragment, String addToBackStack) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(addToBackStack)
                .commit();
    }


    /* ********** CHECK LIST PREF ************* */
    protected void checkPrefs(Context context, String title, String text) {

        String key = App.getPrefsRepository().getListPrefValue(LIST_PREF_KEY);
        switch (key) {

            case TOAST_KEY:
                ToastUtil.showToast(context, title + ": " + text);
                break;

            case NOTIF_KEY:
                NotificationsUtil.getNotification(context, MainActivity.class, title, text);
                break;

        }

    }


    /* ******************* ALERT DIALOG *************** */

    protected void showAboutDialog(Context context) {
        aboutDialog = AboutDialogUtil.showDialog(context);
        // prevent closing on back pressed
        aboutDialog.setCancelable(false);
        // prevent closing when clicked outside
        aboutDialog.setCanceledOnTouchOutside(false);
        aboutDialog.show();
    }

    protected void showConfirmationDialog(Context context, int code, Drink master) {
        confirmationDialog = ConfirmationDialogUtil.showDialog(context, code, master);
        // prevent closing on back pressed
        confirmationDialog.setCancelable(false);
        // prevent closing when clicked outside
        confirmationDialog.setCanceledOnTouchOutside(false);
        confirmationDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (isAboutShowing) {
            showAboutDialog(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // dismiss alert dialog if it is open so activity doesn't leak
        if (aboutDialog != null && aboutDialog.isShowing())
            aboutDialog.dismiss();

        if (confirmationDialog != null && confirmationDialog.isShowing())
            confirmationDialog.dismiss();
    }

}
