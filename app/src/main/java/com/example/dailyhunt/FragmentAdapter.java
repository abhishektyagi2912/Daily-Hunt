package com.example.dailyhunt;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    private final String[] titles = new String[]{"Home", "Sports", "Health", "Technology", "Entertainment"};
    
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new Sports();
            case 2:
                return new Health();
            case 3:
                return new Technology();
            case 4:
                return new Entertainment();
        }
        return new Home();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
