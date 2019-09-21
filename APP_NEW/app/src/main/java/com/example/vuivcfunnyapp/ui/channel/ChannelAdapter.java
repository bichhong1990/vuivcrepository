package com.example.vuivcfunnyapp.ui.channel;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelViewHolder> {

    ArrayList<ChannelModel> channelModelArrayList = new ArrayList<>();
    public ChannelAdapter(ArrayList<ChannelModel> channelModelArrayList) {
        this.channelModelArrayList = channelModelArrayList;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChannelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_channel_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        holder.SetData(channelModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return channelModelArrayList.size();
    }
}
