package com.vit.roman.roman_vit_app.catslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.expanded.ExpandedFragment;
import com.vit.roman.roman_vit_app.favourites.FavouritesListFragment;
import com.vit.roman.roman_vit_app.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CatsListFragment extends Fragment implements CatsListView {

    @BindView(R.id.button_go_to_favourite)
    ImageButton mFavouritesButton;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view_cats)
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mRecyclerViewAdapter;
    private CatsListPresenter mPresenter;

    public static CatsListFragment newInstance() {
        return new CatsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_items_list, container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mPresenter.onCreate();
        setRefreshListener();
        return view;
    }

    @OnClick({R.id.button_go_to_favourite})
    public void onClick(View v) {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).setFragment(FavouritesListFragment.newInstance());
    }

    private void setRefreshListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh();
            }
        });
    }

    @Override
    public void setDataToRecyclerView(List<CatEntity> catsArrayList) {
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), catsArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, CatEntity catEntity) {
                if (getActivity() == null) return;
                ((MainActivity) getActivity()).setFragment(ExpandedFragment.newInstance(catEntity));
            }
        });
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void refreshDataInRecyclerView(List<CatEntity> catsArrayList) {
        mRecyclerViewAdapter.clear();
        mRecyclerViewAdapter.addAll(catsArrayList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "Failed: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    private void createPresenter() {
        CatsListModel model = new CatsListModelImpl();
        mPresenter = new CatsListPresenterImpl(this, model);
    }
}
