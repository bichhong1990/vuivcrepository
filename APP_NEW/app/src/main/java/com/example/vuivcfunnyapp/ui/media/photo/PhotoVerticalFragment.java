package com.example.vuivcfunnyapp.ui.media.photo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoVerticalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vertical_viewpager,container,false);

        final VerticalViewPager verticalviewpager = root.findViewById(R.id.verticalviewpager);

        ApiUtils.GetMediaService().GetPhotoList().enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if(response.isSuccessful()){
                    List<PhotoModel> listPhoto = response.body();
                    if(listPhoto != null && listPhoto.size() > 0)
                    {
                        verticalviewpager.setAdapter(new PhotoVerticalPagerAdapter(getChildFragmentManager(),listPhoto));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Log.d("Error","" + t.getMessage());
            }
        });

        return root;
    }


}
