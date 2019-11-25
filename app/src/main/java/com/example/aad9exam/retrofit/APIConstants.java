package com.example.aad9exam.retrofit;

import static com.example.aad9exam.App.API_KEY;

public interface APIConstants {

    String BASE_URL = "https://the-cocktail-db.p.rapidapi.com/";


    String CATEGORY_KEY = "c";
    String ID_KEY = "i";

    String ENDPOINT_ALL = "list.php";
    String ENDPOINT_DETAILS = "lookup.php";
    String ENDPOINT_FILTER = "filter.php";

    String X_RAPIDAPI_HOST = "X-RapidAPI-Host";
    String X_RAPIDAPI_KEY = "X-RapidAPI-Key";
    String X_RAPID_API_HOST_VALUE = "the-cocktail-db.p.rapidapi.com";
    String X_RAPID_API_KEY_VALUE = API_KEY;

}
