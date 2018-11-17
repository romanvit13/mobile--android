package com.vit.roman.roman_vit_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.adapter.FavouritesRecyclerViewAdapter;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.presenter.FavouritesPresenter;
import com.vit.roman.roman_vit_app.presenter.FavouritesPresenterImpl;
import com.vit.roman.roman_vit_app.view.FavouritesView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesListFragment extends Fragment implements FavouritesView {

    @BindView(R.id.recycler_view_favourites)
    RecyclerView recyclerView;
    FavouritesPresenter mFavouritesPresenter;
    FavouritesRecyclerViewAdapter mRecyclerViewAdapter;

    private void initRecyclerView() {

    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favourites_list, container, false);
        ButterKnife.bind(this, view);
        mFavouritesPresenter = new FavouritesPresenterImpl(this);
        mFavouritesPresenter.getCats();
        initRecyclerView();
        return view;
    }

    @Override
    public void setDataToRecyclerView(List<CatEntity> catsArrayList) {
        FavouritesRecyclerViewAdapter recyclerViewAdapter = new FavouritesRecyclerViewAdapter(catsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
