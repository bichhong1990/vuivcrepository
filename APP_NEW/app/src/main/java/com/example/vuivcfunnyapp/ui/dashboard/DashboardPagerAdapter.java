package com.example.vuivcfunnyapp.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.vuivcfunnyapp.ui.upload.UploadPhotoFragment;
import com.example.vuivcfunnyapp.ui.upload.UploadPhotoInternetFragment;

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
            case 1: return new UploadPhotoInternetFragment();
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
            case 0: return "Ảnh tôi chế";
            case 1: return "Ảnh sưu tầm";
        }
        return "";
    }
}
