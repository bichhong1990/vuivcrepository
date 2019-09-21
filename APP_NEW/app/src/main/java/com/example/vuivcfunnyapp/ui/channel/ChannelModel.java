package com.example.vuivcfunnyapp.ui.channel;

import java.util.ArrayList;

public class ChannelModel {

    String Title, Hashtag;

    public ChannelModel(String title, String hashtag) {
        Title = title;
        Hashtag = hashtag;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getHashtag() {
        return Hashtag;
    }

    public void setHashtag(String hashtag) {
        Hashtag = hashtag;
    }
}
