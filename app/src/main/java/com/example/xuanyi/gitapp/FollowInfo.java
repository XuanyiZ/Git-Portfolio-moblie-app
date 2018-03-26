package com.example.xuanyi.gitapp;

/**
 * Created by Administrator on 2018/3/25.
 */

/*
a class/struct for repo page list item information storing and using
 */
public class FollowInfo {
    String follow_username;
    String imgurl;
    String follow_link;

    public FollowInfo(String follow_username, String imgurl, String follow_link) {
        this.follow_username = follow_username;
        this.imgurl = imgurl;
        this.follow_link = follow_link;

    }



    public String getFollow_username() {
        return follow_username;
    }


    public String getImgurl() {
        return imgurl;
    }


    public String getFollow_link() {
        return follow_link;
    }

}
