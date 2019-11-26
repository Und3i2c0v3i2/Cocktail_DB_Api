package com.example.cocktaildbapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Category implements Parcelable {


    @SerializedName("strCategory")
    @Expose
    private String strCategory;

    private List<Drink> drinkDetails;

    public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
            ;

    protected Category(Parcel in) {
        this.strCategory = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Category() {
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(strCategory);
    }

    public int describeContents() {
        return 0;
    }

    public List<Drink> getDrinkDetails() {
        return drinkDetails;
    }

    public void setDrinkDetails(List<Drink> drinkDetails) {
        this.drinkDetails = drinkDetails;
    }
}
