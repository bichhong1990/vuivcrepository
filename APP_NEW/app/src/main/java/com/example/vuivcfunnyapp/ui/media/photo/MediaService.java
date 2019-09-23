package com.example.vuivcfunnyapp.ui.media.photo;

import com.example.vuivcfunnyapp.ui.media.video.VideoModel;
import com.example.vuivcfunnyapp.ui.profile.ProfileUserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MediaService {

    @GET("api/v1/VuiVCComment")
    Call<List<PhotoModel>> GetPhotoList();

    @GET("api/v1/VuiVCImage")
    Call<List<VideoModel>> GetVideoList();

    @GET("api/v1/VuiVCUser/{id}")
    Call<ProfileUserModel> GetProfileUser(@Path("id") int id);

}
