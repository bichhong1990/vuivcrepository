package com.example.vuivcfunnyapp.ui.media.video;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoVerticalPagerAdapter extends FragmentStatePagerAdapter {

    List<VideoModel> listVideo;
    int TOTAL_PAGES = 0;

    public VideoVerticalPagerAdapter(FragmentManager fm,List<VideoModel> list_Video) {
        super(fm);
        this.listVideo = list_Video;
        this.TOTAL_PAGES = listVideo.size();
    }

    @Override
    public Fragment getItem(int position) {
        return VideoItemFragment.newInstance(listVideo.get(position),position + 1);
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }
}
