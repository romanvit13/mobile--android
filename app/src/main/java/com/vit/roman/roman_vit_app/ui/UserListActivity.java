package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private static final String USER_LIST = "USER_LIST";
    private static final String USER_PREF = "USER_PREF";

    public static Intent getStartIntent(Context context) {
        return new Intent(context, UserListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initListView();
    }

    private void initListView() {
        ListView listView = findViewById(R.id.list_view);
        ArrayList<User> userArrayList = getArrayList();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, userArrayList);
        listView.setAdapter(adapter);
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
}
