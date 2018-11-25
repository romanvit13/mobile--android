package com.vit.roman.roman_vit_app.expanded;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.fullscreen.FullscreenPhotoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandedFragment extends Fragment implements ExpandedView {

    @BindView(R.id.image_view_expanded)
    ImageView mImageView;
    @BindView(R.id.text_view_expanded)
    TextView mTextView;
    @BindView(R.id.favourite_action_button)
    Button mButtonFavourites;
    private ExpandedPresenter mExpandedPresenter;
    private CatEntity mCatEntity;

    public static ExpandedFragment newInstance() {
        Bundle args = new Bundle();
        ExpandedFragment fragment = new ExpandedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_item_expanded, container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mExpandedPresenter.onCreate();
        displayItems();
        return view;
    }

    @OnClick({R.id.image_view_expanded, R.id.favourite_action_button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_expanded:
                startFullPhotoFragment(v);
                break;
            case R.id.favourite_action_button:
                mExpandedPresenter.actionFavourite(mCatEntity);
                break;
        }
    }

    private void startFullPhotoFragment(View v) {
        MainActivity.setCatEntity(mCatEntity);
        ((MainActivity) v.getContext()).setFragment(FullscreenPhotoFragment.newInstance());
    }

    private void displayItems() {
        if (mCatEntity != null) {
            RequestOptions glideOptions = new RequestOptions();
            if (getContext() != null) {
                Glide.with(getContext())
                        .load(mCatEntity.getUrl())
                        .apply(glideOptions.centerCrop())
                        .into(mImageView);
                mTextView.setText(mCatEntity.getId());
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.no_cat), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayAddToFavourite() {
        mButtonFavourites.setText(getString(R.string.favourite_button_action_rm));
        Toast.makeText(getContext(), getString(R.string.favourite_button_success),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRemoveFromFavourite() {
        mButtonFavourites.setText(getString(R.string.favourite_button_action_add));
        Toast.makeText(getContext(), getString(R.string.favourite_button_rm),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayCat(CatEntity catEntity) {
        this.mCatEntity = catEntity;
    }

    private void createPresenter() {
        ExpandedModel expandedModel = new ExpandedModelImpl(getContext());
        mExpandedPresenter = new ExpandedPresenterImpl(this, expandedModel);
    }
}
