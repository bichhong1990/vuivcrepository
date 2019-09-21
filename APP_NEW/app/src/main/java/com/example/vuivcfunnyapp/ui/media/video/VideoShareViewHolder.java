package com.example.vuivcfunnyapp.ui.media.video;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

public class VideoShareViewHolder extends RecyclerView.ViewHolder {

    ImageView image_share_video_item;
    TextView tv_share_video_item;

    public VideoShareViewHolder(@NonNull View itemView) {
        super(itemView);
        image_share_video_item = itemView.findViewById(R.id.image_share_video_item);
        tv_share_video_item = itemView.findViewById(R.id.tv_share_video_item);
    }

    public void SetData(VideoShareModel model)
    {
        tv_share_video_item.setText(model.getIcom_share_name());
        image_share_video_item.setImageResource(model.getIcon_share_image());
    }
}
