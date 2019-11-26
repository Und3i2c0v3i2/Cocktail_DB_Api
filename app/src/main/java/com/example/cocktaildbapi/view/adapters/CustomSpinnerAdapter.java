package com.example.cocktaildbapi.view.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cocktaildbapi.R;

import java.util.List;

//TODO define type parameter
public class CustomSpinnerAdapter extends ArrayAdapter {


    //TODO spinner tag in xml and layout view holder in layout directory


    public CustomSpinnerAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    //TODO another method to returnView for both methods, if we want to show same view
    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {

            // TODO inflate apropriate layout resource
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_main, parent, false);
        }

        //TODO bind views in layout or findViewById()

        // Entity entityItem = getItem(position); <-- to get current item
        // get fields from this object to display in spinner views

        return convertView;
    }


    //TODO in activity
    /*

    create adapter and pass list of items and context,
    find spinner view in xml resource and set created adapter
    call setOnItemSelectedListener on spinner object
    implement behavior ( get selected item with parent.getItemAtPosition(position) )
     */
}
