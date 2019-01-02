package com.gkarbeerlover.giorgoskaryofyllis.beerlover.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {

    private String name;
    private String unit;
    private String value;

    public Ingredient(String name, String unit, String value) {
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public Ingredient() {

    }


    protected Ingredient(Parcel in) {
        name = in.readString();
        unit = in.readString();
        value = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(unit);
        dest.writeString(value);
    }
}
