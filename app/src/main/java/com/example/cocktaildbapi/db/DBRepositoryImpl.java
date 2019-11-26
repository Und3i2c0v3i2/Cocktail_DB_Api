package com.example.cocktaildbapi.db;

import android.util.Log;

import com.example.cocktaildbapi.entity.Drink;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.cocktaildbapi.db.DBHelper.CUSTOM_ID;


public class DBRepositoryImpl {

    private Dao<Drink, Integer> drinkDao;

    private DBHelper dbHelper;

    public DBRepositoryImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        try {
            drinkDao = dbHelper.getDrinkDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Drink> getAll() {
        List<Drink> list = new ArrayList<>();
        try {
            list = drinkDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public Drink getById(int id) {
        Drink drink = null;
        try {
            drink = drinkDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return drink;
    }


    public Drink getByCustomId(String id) {
        List<Drink> list = new ArrayList<>();
        try {
            list = drinkDao.queryForEq(CUSTOM_ID, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }


    private Drink checkIfExists(Drink drink) {

        Drink exist = null;

        try {
            QueryBuilder<Drink, Integer> qb = drinkDao.queryBuilder();
            Where<Drink, Integer> where = qb.where();
            where.like(CUSTOM_ID, drink.getIdDrink());
            PreparedQuery<Drink> pq = qb.prepare();
            exist = drinkDao.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }


    public int insert(Drink drink) {
        int i = 0;
        if (drink != null) {
            try {
                if(checkIfExists(drink) == null) {
                    i = drinkDao.create(drink);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return i;
    }


    public int modify(Drink drink) {
        int i = 0;
        if (drink != null) {

            try {
                i = drinkDao.update(drink);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return i;
    }


    public int delete(Drink drink) {
        int i = 0;

        try {
            i = drinkDao.delete(drink);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public int deleteByCustomId(Drink drink) {

        int i = 0;

        try {

            DeleteBuilder builder = drinkDao.deleteBuilder();
            builder.where()
                    .like(CUSTOM_ID, drink.getIdDrink());
            i = builder.delete();
            Log.i("TAG", i+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


}
