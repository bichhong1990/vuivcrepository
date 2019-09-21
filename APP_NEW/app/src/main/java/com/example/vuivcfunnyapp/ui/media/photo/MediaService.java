package com.example.vuivcfunnyapp.ui.media.photo;

import com.example.vuivcfunnyapp.ui.media.video.VideoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MediaService {

    @GET("api/v1/VuiVCComment")
    Call<List<PhotoModel>> GetPhotoList();

    @GET("api/v1/VuiVCImage")
    Call<List<VideoModel>> GetVideoList();

}
