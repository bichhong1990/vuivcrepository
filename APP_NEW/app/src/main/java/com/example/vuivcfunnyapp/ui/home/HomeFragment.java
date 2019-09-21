package com.example.vuivcfunnyapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.vuivcfunnyapp.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    ViewPager viewPagerHome;
    HomePagerAdapter homePagerAdapter;
    TabLayout tabLayoutHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPagerHome = root.findViewById(R.id.viewPagerHome);
        tabLayoutHome = root.findViewById(R.id.tabLayoutHome);

        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        viewPagerHome.setAdapter(homePagerAdapter);
        tabLayoutHome.setupWithViewPager(viewPagerHome);
        homePagerAdapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPagerHome.setAdapter(homePagerAdapter);
    }
}