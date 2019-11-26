package com.example.cocktaildbapi;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.cocktaildbapi.db.DBHelper;
import com.example.cocktaildbapi.db.DBRepositoryImpl;
import com.example.cocktaildbapi.prefs.PrefsRepositoryImpl;
import com.example.cocktaildbapi.retrofit.RetrofitRepositoryImpl;

public class App extends Application {


    public static final String COLUMN_PRIMARY_ID = "primary_key";


    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String CHANNEL_ID = "channel";
    public static final String CHANNEL_NAME = "Notification Channel";
    public static final String CHANNEL_DESC = "Notification Channel Description";



    private static DBRepositoryImpl dbRepository;
    private static PrefsRepositoryImpl prefsRepository;
    private static RetrofitRepositoryImpl retrofitRepository;


    @Override
    public void onCreate() {
        super.onCreate();

        DBHelper dbHelper = DBHelper.getInstance(this);
        dbRepository = new DBRepositoryImpl(dbHelper);
        registerNotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_DESC);
        prefsRepository = new PrefsRepositoryImpl(this);
        retrofitRepository = new RetrofitRepositoryImpl();

    }


    public static DBRepositoryImpl getDbRepository() {
        return dbRepository;
    }

    public static PrefsRepositoryImpl getPrefsRepository() {
        return prefsRepository;
    }

    public static RetrofitRepositoryImpl getRetrofitRepository() {
        return retrofitRepository;
    }


    public void registerNotificationChannel(String channelId, String channelName, String channelDescription) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            NotificationManager manager = this.getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }


}
