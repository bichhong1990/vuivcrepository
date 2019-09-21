package com.example.vuivcfunnyapp.ui.media.video;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class VideoShareAdapter extends RecyclerView.Adapter<VideoShareViewHolder> {

    ArrayList<VideoShareModel> listShareModel = new ArrayList<>();
    Activity activityShare;

    public VideoShareAdapter(ArrayList<VideoShareModel> listShareModel, Activity activityShare) {
        this.listShareModel = listShareModel;
        this.activityShare = activityShare;
    }

    @NonNull
    @Override
    public VideoShareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoShareViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_video_share,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoShareViewHolder holder, int i) {
        holder.SetData(listShareModel.get(i));
        if(listShareModel.get(i).getIcon_share_image() == R.drawable.icon_video_share_more)
        {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, listShareModel.get(i).getVideo_share_name());
            share.putExtra(Intent.EXTRA_TEXT, listShareModel.get(i).getVideo_share_link());
            activityShare.startActivity(Intent.createChooser(share, listShareModel.get(i).getVideo_share_name()));
        }
        else if(listShareModel.get(i).getIcon_share_image() == R.drawable.icon_video_share_download)
        {

        }
    }

    @Override
    public int getItemCount() {
        return listShareModel.size();
    }
}
