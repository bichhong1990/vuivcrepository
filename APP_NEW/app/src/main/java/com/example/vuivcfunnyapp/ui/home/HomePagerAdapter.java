package com.example.vuivcfunnyapp.ui.home;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.vuivcfunnyapp.ui.media.photo.PhotoVerticalFragment;
import com.example.vuivcfunnyapp.ui.media.video.VideoVerticalFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    public int TOTAL_FRAGMENT_ITEMS = 2;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position)
        {
            case 0: return new PhotoVerticalFragment();
            case 1: return new VideoVerticalFragment();
        }
        return new PhotoVerticalFragment();
    }

    @Override
    public int getCount() {
        return TOTAL_FRAGMENT_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0: return "ẢNH ĐỘNG";
            case 1: return "VIDEO";
        }
        return "";
    }
}
