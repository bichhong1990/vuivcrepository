package com.example.vuivcfunnyapp.ui.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class ChannelFragment extends Fragment {

    EditText edtSearchChannel;
    RecyclerView recycleViewChannelFullList;
    ArrayList<ChannelModel> lstChannel = new ArrayList<>();
    ChannelAdapter channelAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_channel,container,false);
        edtSearchChannel = view.findViewById(R.id.edtSearchChannel);
        recycleViewChannelFullList = view.findViewById(R.id.recycleViewChannelFullList);
        recycleViewChannelFullList.setLayoutManager(new GridLayoutManager(getContext(),2));
        channelAdapter = new ChannelAdapter(lstChannel);
        recycleViewChannelFullList.setAdapter(channelAdapter);
        LoadChannelList();
        return view;
    }

    public void LoadChannelList()
    {
        lstChannel.clear();
        lstChannel.add(new ChannelModel("bansaohoanhao","Hashtag HOT"));
        lstChannel.add(new ChannelModel("bansaohoanhao","Hashtag HOT"));
        lstChannel.add(new ChannelModel("bansaohoanhao","Hashtag HOT"));
        lstChannel.add(new ChannelModel("bansaohoanhao","Hashtag HOT"));
        channelAdapter.notifyDataSetChanged();
    }

}
