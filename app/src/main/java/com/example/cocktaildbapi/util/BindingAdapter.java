package com.example.cocktaildbapi.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class BindingAdapter {

    @androidx.databinding.BindingAdapter("loadImg")
    public static void bindImg(ImageView view, String imgUrl) {
        Picasso.get()
                .load(imgUrl)
                .fit()
                .into(view);
    }
}
