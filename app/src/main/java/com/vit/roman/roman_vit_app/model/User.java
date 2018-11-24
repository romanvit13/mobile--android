package com.vit.roman.roman_vit_app.model;

public class User {

    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mPhone;

    public User(int id, String firstName, String lastName, String phone) {
        this.mId = id;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mPhone = phone;
    }

    @Override
    public String toString() {
        return "User mId: " + mId +
                "\nFirst name: " + mFirstName +
                "\nLast name: " + mLastName +
                "\nPhone: " + mPhone;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }
}
