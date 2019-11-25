package com.example.aad9exam.view;


import android.os.Bundle;


public interface OnActionPerformedListener {

    //TODO define action keys for fragments communication
    String BUNDLE_KEY = "bundle_key";
    String OBJECT_PARCELABLE = "object_parcelable";
    String OBJECT_ID = "id";
    String CONFIRM_KEY = "confirm_key";

    int OPEN_PARENT_DETAILS = 101;
    int OPEN_CHILD_DETAILS = 102;
    int OPEN_ADD = 103;
    int OPEN_EDIT = 104;
    int OPEN_SAVED = 105;

    int ACTION_SAVE = 201;
    int ACTION_UPDATE = 202;
    int ACTION_DELETE = 203;
    int ACTION_CONFIRM = 204;
    int ACTION_SEARCH = 205;

    void onActionPerformed(Bundle bundle);
}
