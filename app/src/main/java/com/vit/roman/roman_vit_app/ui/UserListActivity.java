package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private static final String USER_LIST = "USER_LIST";
    private static final String USER_PREF = "USER_PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        TextView userListTextView = findViewById(R.id.user_list_text_view);
        ArrayList<User> userArrayList = getArrayList();
        String text = listToString(userArrayList);
        userListTextView.setText(text);
    }

    private ArrayList<User> getArrayList() {
        ArrayList<User> userArrayList = new ArrayList<>();
        SharedPreferences userPref = UserListActivity.this.getApplicationContext()
                .getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        if (userPref.contains(USER_LIST)) {
            Gson gson = new Gson();
            String json = userPref.getString(USER_LIST, null);
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            userArrayList = gson.fromJson(json, type);
        }
        return userArrayList;
    }

    private String listToString(ArrayList<User> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : arrayList) {
            String userInfo = "User id: " + user.getId() + "\n" + "First name: "
                    + user.getFirstName() + "\n" + "Last name: " + user.getLastName() + "\n"
                    + "Phone number: " + user.getPhone() + "\n\n";
            stringBuilder.append(userInfo);
        }
        return stringBuilder.toString();
    }
}
