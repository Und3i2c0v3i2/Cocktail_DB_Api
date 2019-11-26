package com.example.cocktaildbapi.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit getRetrofitInstance(){

        return new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ServiceEndpoints getService(){
        return getRetrofitInstance().create(ServiceEndpoints.class);
    }

}
