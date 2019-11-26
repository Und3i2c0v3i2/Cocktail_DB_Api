package com.example.cocktaildbapi.view;


import java.util.List;

public interface RepositoryCallback {

    int ALL_CAT_RES_CODE = 1001;
    int DRINKS_BY_CAT_RES_CODE = 1002;
    int DRINK_RES_CODE = 1003;

    void resultCallback(List<?> list, int code);
}
