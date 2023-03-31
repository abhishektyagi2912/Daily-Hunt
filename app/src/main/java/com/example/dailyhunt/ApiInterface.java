package com.example.dailyhunt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {


    @GET
    Call<MainNews>getNews(
            @Url String url
    );
    @GET
    Call<MainNews>getCategoryNews(
            @Url String url
    );
}
