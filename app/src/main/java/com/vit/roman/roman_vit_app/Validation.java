package com.vit.roman.roman_vit_app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isNameValid(String nameText){
        if (nameText.length() < 1){
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String FIRST_NAME_PATTERN = "[А-Яа-я]+|[a-zA-Z]+";
        pattern = Pattern.compile(FIRST_NAME_PATTERN);
        matcher = pattern.matcher(nameText);
        return matcher.matches();
    }

    public static boolean isPhoneValid (String phoneText) {
        Pattern pattern;
        Matcher matcher;
        final String PHONE_PATTERN = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phoneText);
        return matcher.matches();
    }

    public static boolean isEmailValid (String emailText) {
        if (emailText.length() < 3) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailText);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String passwordText){
        if (passwordText.length() < 8) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordText);
        return matcher.matches();
    }


}
