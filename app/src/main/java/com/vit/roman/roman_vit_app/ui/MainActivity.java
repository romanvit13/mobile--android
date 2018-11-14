package com.vit.roman.roman_vit_app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.fragment.CatsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String PHOTO_URL = "cat_image_url";

    @BindView(R.id.frame_container)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFragment(new CatsListFragment());
    }

    public void setFragment(final Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
