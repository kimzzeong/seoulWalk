package com.example.seoulwalk.data;

import java.util.ArrayList;

public class CommunityData {
    private String titleStr ; // 제목
    private String contentStr; // 내용
    private String imageStr; // 내용의 이미지
    private String nickname; //닉네임
    private String profile_photo; // 프로필사진
    private String date; //글을 쓴 날짜
    private String userId; // 글쓴 아이디
    private ArrayList<PostData> post_content_list; // 내용 리스트


    public CommunityData(String titleStr,String contentStr,String imageStr,String nickname,String date, String profile_photo ,String userId,ArrayList<PostData> post_content_list){
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.imageStr = imageStr;
        this.nickname = nickname;
        this.date = date;
        this.profile_photo = profile_photo;
        this.userId = userId;
        this.post_content_list = post_content_list;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<PostData> getPost_content_list() {
        return post_content_list;
    }

    public void setPost_content_list(ArrayList<PostData> post_content_list) {
        this.post_content_list = post_content_list;
    }



}
