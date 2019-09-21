package com.example.vuivcfunnyapp.ui.media.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class VideoModel
{
    @SerializedName("id")
    @Expose
    private int VideoId;

    @SerializedName("createdAt")
    @Expose
    private Date createdAt;

    @SerializedName("VideoName")
    @Expose
    private String VideoName;

    @SerializedName("VideoUrl")
    @Expose
    private String VideoUrl;

    @SerializedName("videoType")
    @Expose
    private VideoTypeEnum videoType;

    @SerializedName("VideoUploaderId")
    @Expose
    private int VideoUploaderId;

    @SerializedName("NumberOfLike")
    @Expose
    private int NumberOfLike;

    @SerializedName("NumberOfShare")
    @Expose
    private int NumberOfShare;

    @SerializedName("NumberOfComment")
    @Expose
    private int NumberOfComment;

    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;

    public VideoModel(int videoId, Date createdAt, String videoName, String videoUrl, VideoTypeEnum videoType, int videoUploaderId, int numberOfLike, int numberOfShare, int numberOfComment, String imageUrl) {
        VideoId = videoId;
        this.createdAt = createdAt;
        VideoName = videoName;
        VideoUrl = videoUrl;
        this.videoType = videoType;
        VideoUploaderId = videoUploaderId;
        NumberOfLike = numberOfLike;
        NumberOfShare = numberOfShare;
        NumberOfComment = numberOfComment;
        ImageUrl = imageUrl;
    }

    public int getVideoId() {
        return VideoId;
    }

    public void setVideoId(int videoId) {
        VideoId = videoId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public VideoTypeEnum getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoTypeEnum videoType) {
        this.videoType = videoType;
    }

    public int getVideoUploaderId() {
        return VideoUploaderId;
    }

    public void setVideoUploaderId(int videoUploaderId) {
        VideoUploaderId = videoUploaderId;
    }

    public int getNumberOfLike() {
        return NumberOfLike;
    }

    public void setNumberOfLike(int numberOfLike) {
        NumberOfLike = numberOfLike;
    }

    public int getNumberOfShare() {
        return NumberOfShare;
    }

    public void setNumberOfShare(int numberOfShare) {
        NumberOfShare = numberOfShare;
    }

    public int getNumberOfComment() {
        return NumberOfComment;
    }

    public void setNumberOfComment(int numberOfComment) {
        NumberOfComment = numberOfComment;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
