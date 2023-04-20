package com.example.dailyhunt;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends Fragment {
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerViewHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, null);

        recyclerViewHome = v.findViewById(R.id.rv_home);
        shimmerFrameLayout = v.findViewById(R.id.shimmer1);
        swipeRefreshLayout = v.findViewById(R.id.swipeLayout);
        shimmerFrameLayout.startShimmer();
        modelClassArrayList = new ArrayList<>();

        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewHome.setAdapter(adapter);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findNews();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        findNews();
        return v;
    }

    private void findNews(){
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=3f3fe8154a364d35a28b87fce4eee68a";
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
                if(response.isSuccessful()){
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
