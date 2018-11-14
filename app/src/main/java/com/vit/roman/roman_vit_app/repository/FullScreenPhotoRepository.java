package com.vit.roman.roman_vit_app.repository;

import android.support.v4.app.Fragment;

public class FullScreenPhotoRepository {
    Fragment fragment;

    public FullScreenPhotoRepository(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
