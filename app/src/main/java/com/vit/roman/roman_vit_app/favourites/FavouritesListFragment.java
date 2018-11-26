package com.vit.roman.roman_vit_app.favourites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.listener.OnItemClickListener;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.fullscreen.FullscreenPhotoFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesListFragment extends Fragment implements FavouritesView {

    @BindView(R.id.recycler_view_favourites)
    RecyclerView mRecyclerView;
    FavouritesPresenter mFavouritesPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favourites_list,
                container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mFavouritesPresenter.onCreate();
        return view;
    }

    public static FavouritesListFragment newInstance() {
        return new FavouritesListFragment();
    }

    @Override
    public void setDataToRecyclerView(List<CatEntity> catsArrayList) {
        FavouritesRecyclerViewAdapter recyclerViewAdapter =
                new FavouritesRecyclerViewAdapter(catsArrayList, new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, CatEntity catEntity) {
                        if (getActivity() == null) return;
                        ((MainActivity) getActivity()).setFragment(FullscreenPhotoFragment
                                .newInstance(catEntity));
                    }
                });
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), getString(R.string.response_failed) +
                throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    void createPresenter() {
        FavouritesModel favouritesModel = new FavouritesModelImpl(getContext());
        mFavouritesPresenter = new FavouritesPresenterImpl(this, favouritesModel);
    }
}
