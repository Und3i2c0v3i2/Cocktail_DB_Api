package com.example.aad9exam.view;


import java.util.List;

public interface RepositoryCallback {

    /*
     result codes for retrofit
     this will be passed to a listener(our MainActivity) inside onResponse()
     */
    int ALL_CAT_RES_CODE = 1001;
    int DRINKS_BY_CAT_RES_CODE = 1002;
    int DRINK_RES_CODE = 1003;
    int MEALS_BY_NAME_RES_CODE = 1004;

    void resultCallback(List<?> list, int code);
}
