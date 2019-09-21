package com.example.vuivcfunnyapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.vuivcfunnyapp.R;
import com.google.android.material.tabs.TabLayout;

public class ProfileVideoFragment extends Fragment {

    ViewPager viewPagerProfileVideo;
    TabLayout tabLayoutProfileVideo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_video,container,false);
        viewPagerProfileVideo = view.findViewById(R.id.viewPagerProfileVideo);
        tabLayoutProfileVideo = view.findViewById(R.id.tabLayoutProfileVideo);
        ProfileVideoViewPagerAdapter viewPager_adapter = new ProfileVideoViewPagerAdapter(getActivity().getSupportFragmentManager(),getActivity().getApplicationContext());
        viewPagerProfileVideo.setAdapter(viewPager_adapter);
        tabLayoutProfileVideo.setupWithViewPager(viewPagerProfileVideo);
        return view;
    }


}
