package com.vit.roman.roman_vit_app.catslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.favourites.FavouritesListFragment;
import com.vit.roman.roman_vit_app.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CatsListFragment extends Fragment implements CatsListView {

    private CatsListPresenter mPresenter;

    @BindView(R.id.button_go_to_favourite)
    ImageButton mFavouritesButton;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view_cats)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_items_list, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new CatsListPresenterImpl(this);
        mPresenter.requestDataFromServer();
        initRefreshLayout();
        return view;
    }

    @OnClick({R.id.button_go_to_favourite})
    public void onClick(View v) {
        FavouritesListFragment favouritesListFragment = new FavouritesListFragment();
        ((MainActivity) v.getContext()).setFragment(favouritesListFragment);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestDataFromServer();
            }
        });
    }

    @Override
    public void setDataToRecyclerView(List<CatEntity> catsArrayList) {
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), catsArrayList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "Failed: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}