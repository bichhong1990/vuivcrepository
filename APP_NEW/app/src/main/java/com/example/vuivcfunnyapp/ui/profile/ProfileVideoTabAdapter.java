package com.example.vuivcfunnyapp.ui.profile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.List;

import in.myinnos.gifimages.model.Gif;

public class ProfileVideoTabAdapter extends RecyclerView.Adapter<ProfileVideoTabViewHolder> {

    private final List<Gif> gifs;
    private final Activity context;


    public ProfileVideoTabAdapter(Activity context, List<Gif> gifs) {
        this.context = context;
        this.gifs = gifs;
    }

    @NonNull
    @Override
    public ProfileVideoTabViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_profile_video_item,viewGroup,false);
        return new ProfileVideoTabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileVideoTabViewHolder recycleView_photos_viewHolder, int i) {
        recycleView_photos_viewHolder.Bind(gifs.get(i));

    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    public void setGifs(List<Gif> gifs) {
        this.gifs.clear();
        this.gifs.addAll(gifs);

        notifyDataSetChanged();
    }

}
