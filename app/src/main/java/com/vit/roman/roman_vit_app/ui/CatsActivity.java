package com.vit.roman.roman_vit_app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vit.roman.roman_vit_app.CatInterface;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatsActivity extends AppCompatActivity {

    private ImageView catImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        catImage = findViewById(R.id.imageView);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        CatInterface catInterface = retrofit.create(CatInterface.class);
        Call<List<Result>> call = catInterface.imagesOfCats();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                Log.i("Cats", "GOOD!");

                List<Result> results = response.body();
                Log.i("Cats", response.toString());
                Log.i("kotik", response.body().toString());
                if (results != null) {
                    for (Result result : results) {
                        Log.i("Kot", result.getId());
                        Picasso.get().load(result.getUrl()).into(catImage);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Log.i("Cats", "Something went wrong.");
                Log.i("Cats", t.toString());
            }
        });
    }
}
