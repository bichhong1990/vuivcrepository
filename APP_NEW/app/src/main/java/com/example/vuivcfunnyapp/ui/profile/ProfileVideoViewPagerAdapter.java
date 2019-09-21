package com.example.vuivcfunnyapp.ui.profile;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProfileVideoViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    public ProfileVideoViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ProfileVideoTabFragment();
            case 1:
                return new ProfileVideoTabFragment();
        }
        return new ProfileVideoTabFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Videos";
            case 1:
                return "Like";
        }
        return "";
    }
}
