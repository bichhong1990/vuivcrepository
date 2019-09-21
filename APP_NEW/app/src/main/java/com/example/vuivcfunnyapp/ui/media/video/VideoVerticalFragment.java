package com.example.vuivcfunnyapp.ui.media.video;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vuivcfunnyapp.R;
import com.example.vuivcfunnyapp.ui.media.photo.ApiUtils;
import com.example.vuivcfunnyapp.ui.media.photo.PhotoModel;
import com.example.vuivcfunnyapp.ui.media.photo.PhotoVerticalPagerAdapter;
import com.example.vuivcfunnyapp.ui.media.photo.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoVerticalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_vertical_fragment,container,false);

        final VerticalViewPager videoVerticalViewpager = root.findViewById(R.id.videoVerticalViewpager);

        ApiUtils.GetMediaService().GetVideoList().enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {
                if(response.isSuccessful()){
                    List<VideoModel> listVideo = response.body();
                    if(listVideo != null && listVideo.size() > 0)
                    {
                        videoVerticalViewpager.setAdapter(new VideoVerticalPagerAdapter(getChildFragmentManager(),listVideo));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {
                Log.d("Error","" + t.getMessage());
            }
        });

        return root;
    }
}
