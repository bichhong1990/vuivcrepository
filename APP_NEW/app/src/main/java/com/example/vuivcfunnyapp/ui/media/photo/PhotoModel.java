package com.example.vuivcfunnyapp.ui.media.photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoModel {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("ImageTitle")
    @Expose
    String ImageTitle;

    @SerializedName("ImageUrl")
    @Expose
    String ImageUrl;

    @SerializedName("UserId")
    @Expose
    int UserId;

    public PhotoModel(int id, String imageTitle, String imageUrl, int userId) {
        this.id = id;
        ImageTitle = imageTitle;
        ImageUrl = imageUrl;
        UserId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageTitle() {
        return ImageTitle;
    }

    public void setImageTitle(String imageTitle) {
        ImageTitle = imageTitle;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
