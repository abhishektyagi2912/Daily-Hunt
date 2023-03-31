package com.example.dailyhunt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Technology extends Fragment {

    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    RecyclerView recyclerViewHome;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerViewHome = v.findViewById(R.id.rv_tech);
        shimmerFrameLayout = v.findViewById(R.id.shimmer3);
        shimmerFrameLayout.startShimmer();
        modelClassArrayList = new ArrayList<>();

        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewHome.setAdapter(adapter);

        findNews();
        return v;
    }
    private void findNews(){
        String url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=3f3fe8154a364d35a28b87fce4eee68a";
        String Base_url = "https://newsapi.org/v2/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<MainNews> call;
        call = apiInterface.getNews(url);

        call.enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerViewHome.setVisibility(View.VISIBLE);
                    MainNews mainNews = response.body();
                    ArrayList<ModelClass> articles = mainNews.getArticles();
                    for (int i = 0; i < articles.size(); i++) {
                        modelClassArrayList.add(new ModelClass(articles.get(i).getAuthor(), articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).getUrl(),
                                articles.get(i).getUrlToImage(), articles.get(i).getPublishedAt()));
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
            }
        });
    }
}