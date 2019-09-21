package com.example.vuivcfunnyapp.ui.media.photo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    public static final String SERVER_URL = "http://5d0b095389166d00146e3337.mockapi.io/";
    private static Retrofit retrofit = null;

    // Khoi tao Retrofit
    public static MediaService GetMediaService()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(MediaService.class);
    }

}
