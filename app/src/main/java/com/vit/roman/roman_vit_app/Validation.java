package com.vit.roman.roman_vit_app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String FIRST_NAME_PATTERN = "[А-Яа-я]+|[a-zA-Z]+";
    private static final String PHONE_PATTERN = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})";
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    private boolean patternMatches(String regex, String text) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public boolean isNameValid(String nameText) {
        return nameText.length() >= 1 && patternMatches(FIRST_NAME_PATTERN, nameText);
    }

    public boolean isPhoneValid(String phoneText) {
        return patternMatches(PHONE_PATTERN, phoneText);
    }

    public boolean isEmailValid(String emailText) {
        return emailText.length() >= 3 && patternMatches(EMAIL_PATTERN, emailText);
    }

    public boolean isPasswordValid(String passwordText) {
        return passwordText.length() >= 8 && patternMatches(PASSWORD_PATTERN, passwordText);
    }
}
