package com.vit.roman.roman_vit_app.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.adapter.FavouritesRecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesListFragment extends Fragment {

    private static final String FAVOURITES_PREF = "FAVOURITES_PREF";
    private static final String ID_LIST = "ID_LIST";
    private ArrayList<String> mCatIds = new ArrayList<>();
    @BindView(R.id.recycler_view_favourites)
    RecyclerView recyclerView;

    private ArrayList<String> getArrayList() {
        ArrayList<String> catIds = new ArrayList<>();
        SharedPreferences sharedPreferences = FavouritesListFragment.this.getContext()
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
        FavouritesRecyclerViewAdapter recyclerViewAdapter = new FavouritesRecyclerViewAdapter(mCatIds);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favourites_list, container, false);
        ButterKnife.bind(this, view);
        initArrayList();
        initRecyclerView();
        return view;
    }
}
