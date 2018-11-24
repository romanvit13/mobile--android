package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.CatInterface;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.adapter.RecyclerViewAdapter;
import com.vit.roman.roman_vit_app.model.Cat;
import com.vit.roman.roman_vit_app.model.ResultCat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatsActivity extends AppCompatActivity {

    CatInterface mCatInterface;
    private ArrayList<Cat> mCats = new ArrayList<>();
    private SwipeRefreshLayout swipeContainer;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @BindView(R.id.button_go_to_favourite)
    ImageButton mFavouritesButton;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, CatsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        initRetrofit();
        initRefreshLayout();
        initRecyclerView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_go_to_favourite})
    public void onClick(View v) {
        startActivity(FavouritesListActivity.getStartIntent(CatsActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCats();
    }

    private void initRefreshLayout() {
        swipeContainer = findViewById(R.id.swipe_container);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRetrofit();
                getCats();
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_cats);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mCats);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initRetrofit() {
        mCatInterface = App.getApi();
    }

    private void getCats() {
        Call<List<ResultCat>> call = mCatInterface.imagesOfCats();
        call.enqueue(new Callback<List<ResultCat>>() {
            @Override
            public void onResponse(Call<List<ResultCat>> call, Response<List<ResultCat>> response) {
                mRecyclerViewAdapter.clear();
                mCats.clear();
                List<ResultCat> resultCats = response.body();
                if (resultCats != null) {
                    for (ResultCat resultCat : resultCats) {
                        Cat cat = new Cat(resultCat.getId(), resultCat.getUrl());
                        mCats.add(cat);
                    }
                    mRecyclerViewAdapter.addAll(mCats);
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<ResultCat>> call, Throwable t) {
                Log.i("Cats", "Something went wrong.");
            }
        });
    }
}
