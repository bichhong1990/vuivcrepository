package com.example.vuivcfunnyapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.vuivcfunnyapp.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardFragment extends Fragment {

    ViewPager viewPagerDashboard;
    DashboardPagerAdapter dashboardPagerAdapter;
    TabLayout tabLayoutDashboard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        viewPagerDashboard = root.findViewById(R.id.viewPagerDashboard);
        tabLayoutDashboard = root.findViewById(R.id.tabLayoutDashboard);

        dashboardPagerAdapter = new DashboardPagerAdapter(getChildFragmentManager());
        viewPagerDashboard.setAdapter(dashboardPagerAdapter);
        tabLayoutDashboard.setupWithViewPager(viewPagerDashboard);
        dashboardPagerAdapter.notifyDataSetChanged();
        return root;
    }
}