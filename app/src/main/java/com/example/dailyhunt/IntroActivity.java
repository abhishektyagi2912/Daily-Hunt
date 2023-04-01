package com.example.dailyhunt;

import android.os.Bundle;
import androidx.annotation.Nullable;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableLastSlideAlphaExitTransition(true);
//        setSkipButtonVisible();

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.onBoarding)
                .image(R.drawable.news1)
                .title("Daily News")
                .buttonsColor(R.color.Boarding)
                .description("Welcome to Daily-hunt news app")
                .build());
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.onBoarding)
                .image(R.drawable.news22)
                .title("Instant and very fast news sender app")
                .buttonsColor(R.color.Boarding)
                .description("You are all set! Enjoy the app")
                .build());
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }
}
