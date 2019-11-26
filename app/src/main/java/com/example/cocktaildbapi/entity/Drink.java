package com.example.cocktaildbapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.example.cocktaildbapi.App.COLUMN_PRIMARY_ID;

@DatabaseTable
public class Drink extends BaseObservable implements Parcelable {

    @DatabaseField(columnName = COLUMN_PRIMARY_ID, generatedId = true)
    private int id;

    @DatabaseField
    @SerializedName("idDrink")
    @Expose
    private String idDrink;
    @DatabaseField
    @SerializedName("strDrink")
    @Expose
    private String strDrink;
    @DatabaseField
    @SerializedName("strTags")
    @Expose
    private String strTags;
    @DatabaseField
    @SerializedName("strCategory")
    @Expose
    private String strCategory;
    @DatabaseField
    @SerializedName("strIBA")
    @Expose
    private String strIBA;
    @DatabaseField
    @SerializedName("strAlcoholic")
    @Expose
    private String strAlcoholic;
    @DatabaseField
    @SerializedName("strGlass")
    @Expose
    private String strGlass;
    @DatabaseField
    @SerializedName("strInstructions")
    @Expose
    private String strInstructions;
    @DatabaseField
    @SerializedName("strDrinkThumb")
    @Expose
    private String strDrinkThumb;
    @DatabaseField
    @SerializedName("strIngredient1")
    @Expose
    private String strIngredient1;
    @DatabaseField
    @SerializedName("strIngredient2")
    @Expose
    private String strIngredient2;
    @DatabaseField
    @SerializedName("strIngredient3")
    @Expose
    private String strIngredient3;
    @DatabaseField
    @SerializedName("strMeasure1")
    @Expose
    private String strMeasure1;
    @DatabaseField
    @SerializedName("strMeasure2")
    @Expose
    private String strMeasure2;
    @DatabaseField
    @SerializedName("strMeasure3")
    @Expose
    private String strMeasure3;
    @DatabaseField
    @SerializedName("strCreativeCommonsConfirmed")
    @Expose
    private String strCreativeCommonsConfirmed;
    @DatabaseField
    @SerializedName("dateModified")
    @Expose
    private String dateModified;



    public final static Parcelable.Creator<Drink> CREATOR = new Creator<Drink>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        public Drink[] newArray(int size) {
            return (new Drink[size]);
        }

    }
            ;

    protected Drink(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idDrink = ((String) in.readValue((String.class.getClassLoader())));
        this.strDrink = ((String) in.readValue((String.class.getClassLoader())));
        this.strTags = ((String) in.readValue((String.class.getClassLoader())));
        this.strCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.strIBA = ((String) in.readValue((String.class.getClassLoader())));
        this.strAlcoholic = ((String) in.readValue((String.class.getClassLoader())));
        this.strGlass = ((String) in.readValue((String.class.getClassLoader())));
        this.strInstructions = ((String) in.readValue((String.class.getClassLoader())));
        this.strDrinkThumb = ((String) in.readValue((String.class.getClassLoader())));
        this.strIngredient1 = ((String) in.readValue((String.class.getClassLoader())));
        this.strIngredient2 = ((String) in.readValue((String.class.getClassLoader())));
        this.strIngredient3 = ((String) in.readValue((String.class.getClassLoader())));
        this.strMeasure1 = ((String) in.readValue((String.class.getClassLoader())));
        this.strMeasure2 = ((String) in.readValue((String.class.getClassLoader())));
        this.strMeasure3 = ((String) in.readValue((String.class.getClassLoader())));
        this.strCreativeCommonsConfirmed = ((String) in.readValue((String.class.getClassLoader())));
        this.dateModified = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Drink() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    @Bindable
    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strDrink);
    }

    @Bindable
    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strTags);
    }

    @Bindable
    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strCategory);
    }

    @Bindable
    public String getStrIBA() {
        return strIBA;
    }

    public void setStrIBA(String strIBA) {
        this.strIBA = strIBA;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strIBA);
    }

    @Bindable
    public String getStrAlcoholic() {
        return strAlcoholic;
    }

    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strAlcoholic);
    }

    @Bindable
    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strGlass);
    }

    @Bindable
    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strInstructions);
    }

    @Bindable
    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strDrinkThumb);
    }

    @Bindable
    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strIngredient1);
    }

    @Bindable
    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strIngredient2);
    }

    @Bindable
    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strIngredient3);
    }

    @Bindable
    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strMeasure1);
    }

    @Bindable
    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strMeasure2);
    }

    @Bindable
    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.strMeasure3);
    }


    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    @Bindable
    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
        notifyPropertyChanged(com.example.cocktaildbapi.BR.dateModified);
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(idDrink);
        dest.writeValue(strDrink);
        dest.writeValue(strTags);
        dest.writeValue(strCategory);
        dest.writeValue(strIBA);
        dest.writeValue(strAlcoholic);
        dest.writeValue(strGlass);
        dest.writeValue(strInstructions);
        dest.writeValue(strDrinkThumb);
        dest.writeValue(strIngredient1);
        dest.writeValue(strIngredient2);
        dest.writeValue(strIngredient3);
        dest.writeValue(strMeasure1);
        dest.writeValue(strMeasure2);
        dest.writeValue(strMeasure3);
        dest.writeValue(strCreativeCommonsConfirmed);
        dest.writeValue(dateModified);
    }

    public int describeContents() {
        return 0;
    }

}
