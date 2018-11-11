package com.vit.roman.roman_vit_app.fragment;

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
import com.vit.roman.roman_vit_app.adapter.RecyclerViewAdapter;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.presenter.CatsListPresenter;
import com.vit.roman.roman_vit_app.presenter.CatsListPresenterImpl;
import com.vit.roman.roman_vit_app.ui.MainActivity;
import com.vit.roman.roman_vit_app.view.CatsListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CatsListFragment extends Fragment implements CatsListView {

    private boolean isChange = false;
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
        mPresenter.requestDataFromServer(isChange);
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
                isChange = true;
                mPresenter.requestDataFromServer(isChange);
            }
        });
    }

    @Override
    public void setDataToRecyclerView(List<CatEntity> catsArrayList) {
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), catsArrayList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void refreshData(List<CatEntity> catsArrayList) {
        mRecyclerViewAdapter.clear();
        mRecyclerViewAdapter.addAll(catsArrayList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "Failed: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
