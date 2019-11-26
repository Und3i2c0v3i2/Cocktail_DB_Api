package com.example.cocktaildbapi.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.view.OnActionPerformedListener;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.ACTION_UPDATE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_ID;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_PARCELABLE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_CHILD_DETAILS;

public class ConfirmationDialogUtil {


    /* for deciding if we should show dialog on rotation change */
    public static boolean isConfirmationShowing;

    public static AlertDialog showDialog(final Context context, final int action, final Drink drink) {

        isConfirmationShowing = true;

        AlertDialog.Builder builder =
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to proceed with this operation?")
                        .setTitle("Confirmation dialog")
                        .setIcon(R.drawable.ic_attention)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                isConfirmationShowing = false;
                                Bundle bundle = new Bundle();
                                bundle.putInt(BUNDLE_KEY, action);
                                bundle.putParcelable(OBJECT_PARCELABLE, drink);
                                ((OnActionPerformedListener) context).onActionPerformed(bundle);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                isConfirmationShowing = false;

                                if(action == ACTION_UPDATE) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt(BUNDLE_KEY, OPEN_CHILD_DETAILS);
                                    bundle.putString(OBJECT_ID, drink.getIdDrink());
                                    ((OnActionPerformedListener) context).onActionPerformed(bundle);
                                }
                            }
                        });


        return builder.create();
    }
}
