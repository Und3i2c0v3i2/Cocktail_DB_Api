package com.example.cocktaildbapi.view;

import android.content.Context;

import com.example.cocktaildbapi.App;
import com.example.cocktaildbapi.db.DBRepositoryImpl;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.retrofit.RetrofitRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static com.example.cocktaildbapi.view.RepositoryCallback.DRINK_RES_CODE;

public class RepositoryImpl {


    private DBRepositoryImpl dbRepository;
    private RetrofitRepositoryImpl retrofitRepository;
    private RepositoryCallback listener;


    public RepositoryImpl(Context context) {
        dbRepository = App.getDbRepository();
        retrofitRepository = App.getRetrofitRepository();
        if(context instanceof RepositoryCallback) {
            listener = (RepositoryCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RepositoryCallback");
        }
    }



    public void getCategories(Context context) {
        retrofitRepository.getAll(context);
    }


    public void getByCategory(Context context, String query) {
        retrofitRepository.filterByCategory(context, query);
    }


    public void getByDrinkId(Context context, String drinkId) {

        Drink drink = dbRepository.getByCustomId(drinkId);
        if(drink == null) {
            retrofitRepository.filterById(context, drinkId);
        } else {
            List<Drink> list = new ArrayList<>();
            list.add(drink);
            listener.resultCallback(list, DRINK_RES_CODE);
        }

    }


    public int insert(Drink drink) {
        return dbRepository.insert(drink);
    }


    public int delete(Drink drink) {
        return dbRepository.deleteByCustomId(drink);
    }

    public List<Drink> getSaved() {
        return dbRepository.getAll();
    }

    public int modify(Drink drink) {
        return dbRepository.modify(drink);
    }
}
