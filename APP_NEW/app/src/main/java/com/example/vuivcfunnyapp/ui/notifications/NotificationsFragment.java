package com.example.vuivcfunnyapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    RecyclerView recycleViewNotification;
    NotificationAdapter adapterNotification;
    ArrayList<NotificationModel> listNotification = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_notifications, container, false);
       recycleViewNotification = root.findViewById(R.id.recycleViewNotification);
        recycleViewNotification.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adapterNotification = new NotificationAdapter(listNotification);
        recycleViewNotification.setAdapter(adapterNotification);
        LoadNotificationList();
        return root;
    }

    public void LoadNotificationList()
    {
        listNotification.clear();
        listNotification.add(new NotificationModel("Administrator","Chao mung ban den voi Tiktok"));
        listNotification.add(new NotificationModel("Administrator","Chao mung ban den voi Tiktok"));
        listNotification.add(new NotificationModel("Administrator","Chao mung ban den voi Tiktok"));
        adapterNotification.notifyDataSetChanged();
    }

}