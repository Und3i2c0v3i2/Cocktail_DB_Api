package com.example.aad9exam.retrofit;

import android.content.Context;


import com.example.aad9exam.entity.Category;
import com.example.aad9exam.entity.CategoryResult;
import com.example.aad9exam.entity.Drink;
import com.example.aad9exam.entity.DrinkResult;
import com.example.aad9exam.util.ToastUtil;
import com.example.aad9exam.view.RepositoryCallback;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.aad9exam.App.API_KEY;
import static com.example.aad9exam.retrofit.APIConstants.CATEGORY_KEY;
import static com.example.aad9exam.retrofit.APIConstants.ID_KEY;
import static com.example.aad9exam.retrofit.APIConstants.X_RAPID_API_HOST_VALUE;
import static com.example.aad9exam.retrofit.APIConstants.X_RAPID_API_KEY_VALUE;


public class RetrofitRepositoryImpl {

    private RepositoryCallback listener = null;

    /*
     TODO define retrofit calls
      and pass data to listener with RES_CODE
      listener.resultCallback(list, RepositoryCallback.MEALS_BY_NAME_RES_CODE);
      in MainActivity define RES_CODE behavior
     */

    public void getAll(final Context context) {

        if (context instanceof RepositoryCallback) {
            listener = (RepositoryCallback) context;


            HashMap<String, String> filter = new HashMap<>();
            filter.put(CATEGORY_KEY, "list");

            Call<CategoryResult> call = RetrofitService.getService().getCategories(X_RAPID_API_HOST_VALUE, API_KEY, filter);
            call.enqueue(new Callback<CategoryResult>() {
                @Override
                public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {

                    if (response.code() == 200) {
                        CategoryResult categoryResult = response.body();
                        //TODO get data from categoryResult
                        List<Category> list = categoryResult.getDrinks();

                        listener.resultCallback(list, RepositoryCallback.ALL_CAT_RES_CODE);
                    }
                }

                @Override
                public void onFailure(Call<CategoryResult> call, Throwable t) {
                    ToastUtil.showToast(context, t.getMessage());
                }
            });
        }
    }


    public void filterByCategory(final Context context, String query) {

        if (context instanceof RepositoryCallback) {
            listener = (RepositoryCallback) context;

            HashMap<String, String> search = new HashMap<>();
            search.put(CATEGORY_KEY, query);


            Call<DrinkResult> call = RetrofitService.getService().filterByCategory(X_RAPID_API_HOST_VALUE, API_KEY, search);
            call.enqueue(new Callback<DrinkResult>() {
                @Override
                public void onResponse(Call<DrinkResult> call, Response<DrinkResult> response) {

                    if (response.code() == 200) {
                        DrinkResult drinkResult = response.body();
                        List<Drink> list = drinkResult.getDrinkDetails();

                        listener.resultCallback(list, RepositoryCallback.DRINKS_BY_CAT_RES_CODE);
                    }
                }

                @Override
                public void onFailure(Call<DrinkResult> call, Throwable t) {
                    ToastUtil.showToast(context, t.getMessage());
                }
            });

        }

    }


    public void filterById(final Context context, String query) {

        if (context instanceof RepositoryCallback) {
            listener = (RepositoryCallback) context;

            HashMap<String, String> search = new HashMap<>();
            search.put(ID_KEY, query);


            Call<DrinkResult> call = RetrofitService.getService().getDrinkById(X_RAPID_API_HOST_VALUE, X_RAPID_API_KEY_VALUE, search);
            call.enqueue(new Callback<DrinkResult>() {
                @Override
                public void onResponse(Call<DrinkResult> call, Response<DrinkResult> response) {

                    if (response.code() == 200) {
                        DrinkResult drinkResult = response.body();
                        List<Drink> list = drinkResult.getDrinkDetails();

                        listener.resultCallback(list, RepositoryCallback.DRINK_RES_CODE);
                    }
                }

                @Override
                public void onFailure(Call<DrinkResult> call, Throwable t) {
                    ToastUtil.showToast(context, t.getMessage());
                }
            });

        }
    }


}