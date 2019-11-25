package com.example.aad9exam.util;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.aad9exam.R;


public class AboutDialogUtil {


    /* for deciding if we should show dialog on rotation change */
    public static boolean isAboutShowing;

    public static AlertDialog showDialog(Context context) {

        isAboutShowing = true;
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context)
                        .setMessage("Cocktail DB Api:\nThis application was made for practice purpose.\nIvana Kilibarda")
                        .setTitle("About")
                        .setIcon(R.drawable.ic_info)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                isAboutShowing = false;
                            }
                        });


        return builder.create();
    }
}
