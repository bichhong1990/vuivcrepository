package com.example.vuivcfunnyapp.ui.media.comment;

import java.util.Date;

public class CommentModel {

    int Id, UserId;
    String UserAvatar, UserName,Content;
    Date PostedDate;
    int TotalLike;

    public CommentModel(int id, int userId, String userAvatar, String userName, String content, Date postedDate, int totalLike) {
        Id = id;
        UserId = userId;
        UserAvatar = userAvatar;
        UserName = userName;
        Content = content;
        PostedDate = postedDate;
        TotalLike = totalLike;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getPostedDate() {
        return PostedDate;
    }

    public void setPostedDate(Date postedDate) {
        PostedDate = postedDate;
    }

    public int getTotalLike() {
        return TotalLike;
    }

    public void setTotalLike(int totalLike) {
        TotalLike = totalLike;
    }
}
