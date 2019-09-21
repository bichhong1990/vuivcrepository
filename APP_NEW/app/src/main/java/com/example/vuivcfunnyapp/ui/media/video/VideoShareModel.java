package com.example.vuivcfunnyapp.ui.media.video;

public class VideoShareModel
{
    String icom_share_name,video_share_link,video_share_name;
    int icon_share_image;

    public VideoShareModel(String icom_share_name, String video_share_link, String video_share_name, int icon_share_image) {
        this.icom_share_name = icom_share_name;
        this.video_share_link = video_share_link;
        this.video_share_name = video_share_name;
        this.icon_share_image = icon_share_image;
    }

    public String getIcom_share_name() {
        return icom_share_name;
    }

    public void setIcom_share_name(String icom_share_name) {
        this.icom_share_name = icom_share_name;
    }

    public String getVideo_share_link() {
        return video_share_link;
    }

    public void setVideo_share_link(String video_share_link) {
        this.video_share_link = video_share_link;
    }

    public String getVideo_share_name() {
        return video_share_name;
    }

    public void setVideo_share_name(String video_share_name) {
        this.video_share_name = video_share_name;
    }

    public int getIcon_share_image() {
        return icon_share_image;
    }

    public void setIcon_share_image(int icon_share_image) {
        this.icon_share_image = icon_share_image;
    }
}
