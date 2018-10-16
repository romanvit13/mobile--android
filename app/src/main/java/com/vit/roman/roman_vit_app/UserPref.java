package com.vit.roman.roman_vit_app;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPref {
    private static final String USER_PREF = "USER_PREF";
    private static final String USER_ID = "USER_ID";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String USER_PHONE = "USER_PHONE";

    private final SharedPreferences mPrefs;

    public UserPref(Context context) {
        mPrefs = context.getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
    }

    public void putUser(User user){
        mPrefs.edit()
                .putInt(USER_ID, user.getId())
                .putString(FIRST_NAME, user.getFirstName())
                .putString(LAST_NAME, user.getLastName())
                .putString(USER_PHONE, user.getPhone())
                .apply();
    }

}
