package com.vit.roman.roman_vit_app.repository;

import android.content.Context;
import android.support.v4.app.Fragment;

public class ExpandedRepository {
    Context context;
    Fragment fragment;

    public ExpandedRepository(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
