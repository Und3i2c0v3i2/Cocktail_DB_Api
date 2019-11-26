package com.example.cocktaildbapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResult implements Parcelable {

    @SerializedName("drinks")
    @Expose
    private List<Category> drinks = null;
    public final static Parcelable.Creator<CategoryResult> CREATOR = new Creator<CategoryResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryResult createFromParcel(Parcel in) {
            return new CategoryResult(in);
        }

        public CategoryResult[] newArray(int size) {
            return (new CategoryResult[size]);
        }

    }
            ;

    protected CategoryResult(Parcel in) {
        in.readList(this.drinks, (Drink.class.getClassLoader()));
    }

    public CategoryResult() {
    }

    public List<Category> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Category> drinks) {
        this.drinks = drinks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(drinks);
    }

    public int describeContents() {
        return 0;
    }

}
