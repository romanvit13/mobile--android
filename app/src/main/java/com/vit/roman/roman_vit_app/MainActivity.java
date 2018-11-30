package com.vit.roman.roman_vit_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.startscreen.StartScreenFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame_container)
    FrameLayout frameLayout;

    public static CatEntity getCatEntity() {
        return mCatEntity;
    }

    public static void setCatEntity(CatEntity mCatEntity) {
        MainActivity.mCatEntity = mCatEntity;
    }

    private static CatEntity mCatEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFragment(StartScreenFragment.newInstance());
    }

    public void setFragment(final Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
