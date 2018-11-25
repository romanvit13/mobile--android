package com.vit.roman.roman_vit_app.fullscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class FullscreenPhotoFragment extends Fragment implements FullScreenPhotoView {

    public static final String CAT_ENTITY = "CAT_ENTITY";
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;
    FullScreenPhotoPresenter mFullScreenPhotoPresenter;

    public static FullscreenPhotoFragment newInstance(CatEntity catEntity) {
        Bundle args = new Bundle();
        FullscreenPhotoFragment fragment = new FullscreenPhotoFragment();
        args.putParcelable(CAT_ENTITY, catEntity);
        fragment.setArguments(args);
        return fragment;
    }

    private CatEntity getCat() {
        Bundle bundle = this.getArguments();
        if (bundle != null)
            return bundle.getParcelable(CAT_ENTITY);
        else return null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo_fullscreen,
                container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        if (getCat() != null)
            setPhoto(getCat());
        return view;
    }

    private void setPhoto(CatEntity catEntity) {
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(this).load(catEntity.getUrl())
                .apply(glideOptions.centerCrop()).into(mPhotoView);
    }

    @Override
    public void displayCat(CatEntity catEntity) {
    }

    private void createPresenter() {
        FullScreenPhotoModel model = new FullScreenPhotoModelImpl();
        mFullScreenPhotoPresenter = new FullScreenPhotoPresenterImpl(this, model);
    }
}
