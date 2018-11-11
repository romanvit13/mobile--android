package com.vit.roman.roman_vit_app.repository;

import android.content.Context;

public class FavouritesRepository {
    Context context;
    public FavouritesRepository(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
