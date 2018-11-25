package com.vit.roman.roman_vit_app.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatEntity implements Parcelable {

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
    private List<BreedEntity> mBreeds = null;
    @SerializedName("categories")
    @Expose
    private List<Object> mCategories = null;
    @SerializedName("breed_ids")
    @Expose
    private String mBreedIds;

    protected CatEntity(Parcel in) {
        mId = in.readString();
        mUrl = in.readString();
        if (in.readByte() == 0) {
            mWidth = null;
        } else {
            mWidth = in.readInt();
        }
        if (in.readByte() == 0) {
            mHeight = null;
        } else {
            mHeight = in.readInt();
        }
        mMimeType = in.readString();
        mBreedIds = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mUrl);
        if (mWidth == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mWidth);
        }
        if (mHeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mHeight);
        }
        dest.writeString(mMimeType);
        dest.writeString(mBreedIds);
    }

    public static final Creator<CatEntity> CREATOR = new Creator<CatEntity>() {
        @Override
        public CatEntity createFromParcel(Parcel in) {
            return new CatEntity(in);
        }

        @Override
        public CatEntity[] newArray(int size) {
            return new CatEntity[size];
        }
    };

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

    public List<BreedEntity> getBreeds() {
        return mBreeds;
    }

    public void setBreeds(List<BreedEntity> breeds) {
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

    @Override
    public int describeContents() {
        return 0;
    }


}