package com.vit.roman.roman_vit_app.entity;

public class Cat {
    private String mId;
    private String mImage;

    public Cat(String id, String image) {
        mId = id;
        mImage = image;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
