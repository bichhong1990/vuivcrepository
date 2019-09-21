package com.example.vuivcfunnyapp.ui.media.photo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhotoVerticalPagerAdapter extends FragmentStatePagerAdapter {

    List<PhotoModel> listPhoto = new ArrayList<PhotoModel>();
    int TOTAL_PAGES = 0;

    public PhotoVerticalPagerAdapter(FragmentManager fm, List<PhotoModel> list_Photo) {
        super(fm);
        this.listPhoto = list_Photo;
        this.TOTAL_PAGES = listPhoto.size();
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoItemFragment.newInstance(listPhoto.get(position),position + 1);
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }

}
