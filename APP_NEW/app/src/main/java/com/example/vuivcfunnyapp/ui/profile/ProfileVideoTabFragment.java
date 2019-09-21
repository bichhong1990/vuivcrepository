package com.example.vuivcfunnyapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

import in.myinnos.gifimages.model.Gif;

public class ProfileVideoTabFragment extends Fragment {

    RecyclerView recyclerViewProfilePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_video_list,container,false);
        recyclerViewProfilePhoto = root.findViewById(R.id.recyclerViewProfilePhoto);

        ProfileVideoTabAdapter recycleView_video_adapter = new ProfileVideoTabAdapter(getActivity(),new ArrayList<Gif>());
        recyclerViewProfilePhoto.setAdapter(recycleView_video_adapter);
        recyclerViewProfilePhoto.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerViewProfilePhoto.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                ProfileVideoTabViewHolder gifHolder = (ProfileVideoTabViewHolder) holder;
                gifHolder.stopPlayback();
            }
        });


        return root;
    }

}
