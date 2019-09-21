package com.example.vuivcfunnyapp.ui.profile;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileUserModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String Id;

    @SerializedName("numVideo")
    @Expose
    private int numVideo;

    @SerializedName("numFollower")
    @Expose
    private int numFollower;

    @SerializedName("numFollowing")
    @Expose
    private int numFollowing;

    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("nameUser")
    @Expose
    private String nameUser;

    @SerializedName("ngaySinh")
    @Expose
    private String ngaySinh;

    @SerializedName("photo")
    @Expose
    private Bitmap photo;

    @SerializedName("userType")
    @Expose
    private UserTypeEnum userType;

    public ProfileUserModel() {
    }

    public ProfileUserModel(String id, int numVideo, int numFollower, int numFollowing, String sex, String nameUser, String ngaySinh, Bitmap photo, UserTypeEnum userType) {
        Id = id;
        this.numVideo = numVideo;
        this.numFollower = numFollower;
        this.numFollowing = numFollowing;
        this.sex = sex;
        this.nameUser = nameUser;
        this.ngaySinh = ngaySinh;
        this.photo = photo;
        this.userType = userType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getNumVideo() {
        return numVideo;
    }

    public void setNumVideo(int numVideo) {
        this.numVideo = numVideo;
    }

    public int getNumFollower() {
        return numFollower;
    }

    public void setNumFollower(int numFollower) {
        this.numFollower = numFollower;
    }

    public int getNumFollowing() {
        return numFollowing;
    }

    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
