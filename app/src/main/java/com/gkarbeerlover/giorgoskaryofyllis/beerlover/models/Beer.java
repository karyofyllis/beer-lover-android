package com.gkarbeerlover.giorgoskaryofyllis.beerlover.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Beer implements Parcelable{

    private String name;
    private String description;
    private String author;
    private List<Ingredient> ingredients;
    private String tips;
    private List<String> foodServed;
    private int id;
    private String imgUrl;
    private String tagline;
    private String firstBrewed;

    public Beer() {}


    protected Beer(Parcel in) {
        name = in.readString();
        description = in.readString();
        author = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        tips = in.readString();
        foodServed = in.createStringArrayList();
        id = in.readInt();
        imgUrl = in.readString();
        tagline = in.readString();
        firstBrewed = in.readString();
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public List<String> getFoodServed() {
        return foodServed;
    }

    public void setFoodServed(List<String> foodServed) {
        this.foodServed = foodServed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(author);
        dest.writeTypedList(ingredients);
        dest.writeString(tips);
        dest.writeStringList(foodServed);
        dest.writeInt(id);
        dest.writeString(imgUrl);
        dest.writeString(tagline);
        dest.writeString(firstBrewed);
    }
}
