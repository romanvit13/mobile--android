package com.vit.roman.roman_vit_app.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vit.roman.roman_vit_app.CatInterface;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.adapter.RecyclerViewAdapter;
import com.vit.roman.roman_vit_app.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatsActivity extends AppCompatActivity {

    private static final String TAG = "CatsActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Call<List<Result>> call;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerViewAdapter recyclerViewAdapter;
    CatInterface catInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        initRetrofit();
        initRefreshLayout();
        initRecyclerView();
        getCats();
    }

    private void initRefreshLayout() {
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRetrofit();
               getCats();
            }
        });
    }


    private void initRecyclerView() {
        Log.i(TAG, "INIT RECYCLER");
        RecyclerView recyclerView = findViewById(R.id.parent_layout);
        recyclerViewAdapter = new RecyclerViewAdapter(this, mNames, mImages);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        catInterface = retrofit.create(CatInterface.class);
    }

    private void getCats() {
        call = catInterface.imagesOfCats();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                Log.i("Cats", "GOOD!");
                recyclerViewAdapter.clear();
                mImages.clear();
                mNames.clear();
                List<Result> results = response.body();
                Log.i("Cats", response.toString());
                Log.i("kotik", response.body().toString());
                if (results != null) {
                    for (Result result : results) {
                        Log.i(TAG , "kot: " + result.getId());
                        mImages.add(result.getUrl());
                        Log.i(TAG, "Images size: " + mImages.size());
                        mNames.add(result.getId());
                    }
                    recyclerViewAdapter.addAll(mImages, mNames);
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Log.i("Cats", "Something went wrong.");
                Log.i("Cats", t.toString());
            }
        });
    }
}
