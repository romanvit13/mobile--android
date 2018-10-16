package com.vit.roman.roman_vit_app;

import com.vit.roman.roman_vit_app.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CatInterface {
    @GET("/v1/images/search?limit=20")
    @Headers({
            "x-api-key: fa0e4933-565b-4e42-a44d-dc1d45cca980",
            "Content-Type: application/json"
    })
    Call<List<Result>>imagesOfCats();
}
