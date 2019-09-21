package com.example.vuivcfunnyapp.ui.profile;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;

import in.myinnos.gifimages.gif.GifView;
import in.myinnos.gifimages.model.Gif;

public class ProfileVideoTabViewHolder extends RecyclerView.ViewHolder {

    ImageView img_photos;
    private Activity context;

    private View clickZone;
    private GifView gifView;

    public ProfileVideoTabViewHolder(@NonNull View itemView) {
        super(itemView);
        img_photos = itemView.findViewById(R.id.img_photos);
        clickZone = itemView.findViewById(R.id.touch_effect);
        gifView = itemView.findViewById(R.id.gif_view);
    }

    public void Bind(final Gif gif) {
        Glide.with(itemView.getContext()).load(gif.getPreviewImageUrl()).centerCrop().into(img_photos);
        clickZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gifView.getVisibility() == View.VISIBLE) {
                    gifView.release();
                    gifView.setVisibility(View.GONE);
                } else {
                    gifView.setVisibility(View.VISIBLE);
                    gifView.start(gif.getPreviewMp4Url());
                }
            }
        });

    }
    public void stopPlayback() {
        if (gifView.getVisibility() == View.VISIBLE) {
            clickZone.performClick();
        }
    }

}

