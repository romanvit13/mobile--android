package com.vit.roman.roman_vit_app.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class BreedEntity {

    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}