package com.example.cocktaildbapi.retrofit;


import com.example.cocktaildbapi.entity.CategoryResult;
import com.example.cocktaildbapi.entity.DrinkResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

import static com.example.cocktaildbapi.retrofit.APIConstants.ENDPOINT_ALL;
import static com.example.cocktaildbapi.retrofit.APIConstants.ENDPOINT_DETAILS;
import static com.example.cocktaildbapi.retrofit.APIConstants.ENDPOINT_FILTER;
import static com.example.cocktaildbapi.retrofit.APIConstants.X_RAPIDAPI_HOST;
import static com.example.cocktaildbapi.retrofit.APIConstants.X_RAPIDAPI_KEY;


public interface ServiceEndpoints {

    // TODO define endpoints and Call type parameters
    @GET(ENDPOINT_ALL)
    Call<CategoryResult> getCategories(@Header(X_RAPIDAPI_HOST) String host,
                                       @Header(X_RAPIDAPI_KEY) String key,
                                       @QueryMap HashMap<String, String> filter);

    @GET(ENDPOINT_DETAILS)
    Call<DrinkResult> getDrinkById(@Header(X_RAPIDAPI_HOST) String host,
                                   @Header(X_RAPIDAPI_KEY) String key,
                                   @QueryMap HashMap<String, String> search);

    @GET(ENDPOINT_FILTER)
    Call<DrinkResult> filterByCategory(@Header(X_RAPIDAPI_HOST) String host,
                                       @Header(X_RAPIDAPI_KEY) String key,
                                       @QueryMap HashMap<String, String> search);

}
