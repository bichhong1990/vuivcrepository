package com.example.vuivcfunnyapp.ui.notifications;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    ArrayList<NotificationModel> notificationModelArrayList = new ArrayList<>();
    public NotificationAdapter(ArrayList<NotificationModel> notificationModelArrayList) {
        this.notificationModelArrayList = notificationModelArrayList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_notification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.SetData(notificationModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }
}
