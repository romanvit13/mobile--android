package com.vit.roman.roman_vit_app.entity;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public User(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User id: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nPhone: " + phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
