package com.example.vuivcfunnyapp.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.vuivcfunnyapp.ui.upload.UploadPhotoFragment;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {

    public int TOTAL_FRAGMENT_ITEMS = 2;

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new UploadPhotoFragment();
            case 1: return new UploadPhotoFragment();
        }
        return new UploadPhotoFragment();
    }

    @Override
    public int getCount() {
        return TOTAL_FRAGMENT_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: return "Upload photo";
            case 1: return "Upload video";
        }
        return "";
    }
}
