package com.example.dailyhunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    AdView adView;

    FragmentAdapter fragmentAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    private final String[] titles = new String[]{"Home", "Sports", "Health", "Technology", "Entertainment"};
    String api = "3f3fe8154a364d35a28b87fce4eee68a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ads
        adView = findViewById(R.id.adview);
        MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // ads end
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE );
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if(firstStart){
            introActivity();
        }

        viewPager2 = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.include);

        fragmentAdapter = new FragmentAdapter(this);
        viewPager2.setAdapter(fragmentAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();

    }

    private void introActivity(){
        Intent intent = new Intent(MainActivity.this,IntroActivity.class);
        startActivity(intent);

        SharedPreferences prefs =  getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

}