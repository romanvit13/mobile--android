package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.adapter.FavouritesRecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavouritesListActivity extends AppCompatActivity {

    private ArrayList<String> mCatIds = new ArrayList<>();
    private FavouritesRecyclerViewAdapter recyclerViewAdapter;
    private static final String FAVOURITES_PREF = "FAVOURITES_PREF";
    private static final String ID_LIST = "ID_LIST";

    private ArrayList<String> getArrayList() {
        ArrayList<String> catIds = new ArrayList<>();
        SharedPreferences sharedPreferences = FavouritesListActivity.this.getApplicationContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(ID_LIST)) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(ID_LIST, null);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            catIds = gson.fromJson(json, type);
        }
        return catIds;
    }

    private void initArrayList() {
        mCatIds = getArrayList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.favourites_recycler);
        recyclerViewAdapter = new FavouritesRecyclerViewAdapter(this, mCatIds);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);
        initArrayList();
        initRecyclerView();
    }
}
