package com.example.vuivcfunnyapp.ui.notifications;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    TextView tvNotificationUserName,tvNotificationContent;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNotificationUserName = itemView.findViewById(R.id.tvNotificationUserName);
        tvNotificationContent = itemView.findViewById(R.id.tvNotificationContent);
    }

    public void SetData(NotificationModel model)
    {
        tvNotificationUserName.setText(model.getTitle());
        tvNotificationContent.setText(model.getContent());
    }
}
