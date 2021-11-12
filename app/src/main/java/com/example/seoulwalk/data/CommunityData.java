package com.example.seoulwalk.data;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static okhttp3.internal.http.HttpDate.parse;

public class CommunityData implements Comparable<CommunityData> {
    private String titleStr ; // 제목
    private String contentStr; // 내용
    private String imageStr; // 내용의 이미지
    private String nickname; //닉네임
    private String profile_photo; // 프로필사진
    private String date; //글을 쓴 날짜
    private String userId; // 글쓴 아이디
    private String post_content_list; // 내용 리스트
    private String write_spinner; //후기,전체 등 글 카테고리
    private String write_course_spinner; // 코스별 카테고리


    public CommunityData(String titleStr,String contentStr,String imageStr,String nickname,String date, String profile_photo ,String userId, String post_content_list, String write_spinner, String write_course_spinner){
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.imageStr = imageStr;
        this.nickname = nickname;
        this.date = date;
        this.profile_photo = profile_photo;
        this.userId = userId;
        this.post_content_list = post_content_list;
        this.write_spinner = write_spinner;
        this.write_course_spinner = write_course_spinner;
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

    public String getPost_content_list() {
        return post_content_list;
    }

    public void setPost_content_list(String post_content_list) {
        this.post_content_list = post_content_list;
    }

    public String getWrite_spinner() {
        return write_spinner;
    }

    public void setWrite_spinner(String write_spinner) {
        this.write_spinner = write_spinner;
    }

    public String getWrite_course_spinner() {
        return write_course_spinner;
    }

    public void setWrite_course_spinner(String write_course_spinner) {
        this.write_course_spinner = write_course_spinner;
    }


    @Override
    public int compareTo(CommunityData o) {
        SimpleDateFormat sdformat = new
                SimpleDateFormat("MM/dd HH:mm:ss");

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdformat.parse(o.date);
            date2 = sdformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println("Date-1: " + o.date);
//        System.out.println("Date-2: " + sdformat.format(date2));
        if (date2.after(date1)) {
            System.out.println(
                    "Date-1 is after Date-2");
            return 1;
        }else{
            return -1;
        }

    }
}
