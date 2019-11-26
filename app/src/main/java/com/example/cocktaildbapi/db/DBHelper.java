package com.example.cocktaildbapi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocktaildbapi.entity.Drink;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DBHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "ormlite.db";
    private static final int DATABASE_VERSION = 1;


    // TODO define custom id
    public static final String CUSTOM_ID = "idDrink";

    private Dao<Drink, Integer> drinkDao;

    private static DBHelper instance;


    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        try {
            drinkDao = getDrinkDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Drink.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Drink.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBHelper getInstance(Context context) {

        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }


    Dao<Drink, Integer> getDrinkDao() throws SQLException {
        if (drinkDao == null) {
            drinkDao = getDao(Drink.class);
        }

        return drinkDao;
    }


    @Override
    public void close() {
        drinkDao = null;
        super.close();
    }


}