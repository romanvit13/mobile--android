package com.vit.roman.roman_vit_app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    Validation(){

    }

    public boolean isNameValid(String nameText){
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

    public boolean isPasswordValid(String passwordText){
        if (passwordText.length() < 8) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordText);
        return matcher.matches();
    }

    public boolean isEmailValid (String emailText) {
        if (emailText.length() < 3) {
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailText);
        return matcher.matches();
    }


}
