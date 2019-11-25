package com.example.aad9exam.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinkResult implements Parcelable {

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinkDetails = null;
    public final static Parcelable.Creator<DrinkResult> CREATOR = new Creator<DrinkResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DrinkResult createFromParcel(Parcel in) {
            return new DrinkResult(in);
        }

        public DrinkResult[] newArray(int size) {
            return (new DrinkResult[size]);
        }

    }
            ;

    protected DrinkResult(Parcel in) {
        in.readList(this.drinkDetails, (Drink.class.getClassLoader()));
    }

    public DrinkResult() {
    }

    public List<Drink> getDrinkDetails() {
        return drinkDetails;
    }

    public void setDrinkDetails(List<Drink> drinkDetails) {
        this.drinkDetails = drinkDetails;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(drinkDetails);
    }

    public int describeContents() {
        return 0;
    }

}
