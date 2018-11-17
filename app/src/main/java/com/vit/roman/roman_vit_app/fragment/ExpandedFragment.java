package com.vit.roman.roman_vit_app.fragment;

import android.os.Bundle;
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
import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.presenter.ExpandedPresenter;
import com.vit.roman.roman_vit_app.presenter.ExpandedPresenterImpl;
import com.vit.roman.roman_vit_app.ui.MainActivity;
import com.vit.roman.roman_vit_app.view.ExpandedView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_item_expanded, container, false);
        ButterKnife.bind(this, view);
        mExpandedPresenter = new ExpandedPresenterImpl(this);
        getCatEntity();
        setItems();
        return view;
    }

    @OnClick({R.id.image_view_expanded, R.id.favourite_action_button})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.image_view_expanded:
                App.setmCatEntity(mCatEntity);
                FullscreenPhotoFragment fullscreenPhotoFragment = new FullscreenPhotoFragment();
                ((MainActivity) v.getContext()).setFragment(fullscreenPhotoFragment);
                break;
            case R.id.favourite_action_button:
                mExpandedPresenter.addFavourite(mCatEntity);
                break;
        }
    }

    private void setItems() {
        if (mCatEntity != null) {
            RequestOptions glideOptions = new RequestOptions();
            Glide.with(App.getAppContext())
                    .load(mCatEntity.getUrl())
                    .apply(glideOptions.centerCrop())
                    .into(mImageView);
            mTextView.setText(mCatEntity.getId());
        } else {
            Toast.makeText(App.getAppContext(), "CatEntity is NULL", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addToFavourite() {
        mButtonFavourites.setText(getResources().getString(R.string.favourite_button_action_add));
    }

    @Override
    public void removeFromFavourite() {
        mButtonFavourites.setText(getResources().getString(R.string.favourite_button_action_rm));
    }

    @Override
    public void setCat(CatEntity catEntity) {
        this.mCatEntity = catEntity;
    }

    @Override
    public void onPhotoClick() {

    }

    private void getCatEntity() {
        mExpandedPresenter.getCat();
    }
}
