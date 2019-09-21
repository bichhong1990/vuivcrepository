package com.example.vuivcfunnyapp.ui.channel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class ChannelViewHolder extends RecyclerView.ViewHolder {

    View currentView;
    TextView tvChannelTitle, tvChannelHashtag;
    ImageView imv;

    public ChannelViewHolder(@NonNull View itemView) {
        super(itemView);
        currentView = itemView;
        tvChannelTitle = itemView.findViewById(R.id.tvChannelTitle);
        tvChannelHashtag = itemView.findViewById(R.id.tvChannelHashtag);
        imv = itemView.findViewById(R.id.imvChannel);
    }

    public void SetData(ChannelModel model)
    {
        tvChannelTitle.setText(model.getTitle());
        tvChannelHashtag.setText(model.getHashtag());
//        Glide
//                .with(currentView)
//                .load("https://media.giphy.com/media/duzpaTbCUy9Vu/giphy.gif")
//                .centerCrop()
//                .into(imv);
    }


}
