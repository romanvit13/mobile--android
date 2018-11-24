package com.vit.roman.roman_vit_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultCat {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("url")
    @Expose
    private String mUrl;
    @SerializedName("width")
    @Expose
    private Integer mWidth;
    @SerializedName("height")
    @Expose
    private Integer mHeight;
    @SerializedName("mime_type")
    @Expose
    private String mMimeType;
    @SerializedName("breeds")
    @Expose
    private List<Breed> mBreeds = null;
    @SerializedName("categories")
    @Expose
    private List<Object> mCategories = null;
    @SerializedName("breed_ids")
    @Expose
    private String mBreedIds;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public Integer getWidth() {
        return mWidth;
    }

    public void setWidth(Integer width) {
        this.mWidth = width;
    }

    public Integer getHeight() {
        return mHeight;
    }

    public void setHeight(Integer height) {
        this.mHeight = height;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public void setMimeType(String mimeType) {
        this.mMimeType = mimeType;
    }

    public List<Breed> getBreeds() {
        return mBreeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.mBreeds = breeds;
    }

    public List<Object> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Object> categories) {
        this.mCategories = categories;
    }

    public String getBreedIds() {
        return mBreedIds;
    }

    public void setBreedIds(String breedIds) {
        this.mBreedIds = breedIds;
    }

}