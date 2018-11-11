package com.vit.roman.roman_vit_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.CatInterface;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.adapter.RecyclerViewAdapter;
import com.vit.roman.roman_vit_app.entity.Cat;
import com.vit.roman.roman_vit_app.entity.ResultCat;
import com.vit.roman.roman_vit_app.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatsFragment extends Fragment {

    CatInterface mCatInterface;
    @BindView(R.id.button_go_to_favourite)
    ImageButton mFavouritesButton;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.recycler_view_cats)
    RecyclerView recyclerView;
    private ArrayList<Cat> mCats = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_items_list, container, false);
        ButterKnife.bind(this, view);
        initRetrofit();
        getCats();
        initRefreshLayout();
        initRecyclerView();
        return view;
    }

    @OnClick({R.id.button_go_to_favourite})
    public void onClick(View v) {
        FavouritesListFragment favouritesListFragment = new FavouritesListFragment();
        ((MainActivity) v.getContext()).setFragment(favouritesListFragment);
    }


    private void initRefreshLayout() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRetrofit();
                getCats();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mCats);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
