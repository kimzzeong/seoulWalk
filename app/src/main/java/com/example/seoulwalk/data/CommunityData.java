package com.example.seoulwalk.data;

public class CommunityData {
    private String titleStr ; // 제목
    private String contentStr; // 내용
    private String imageStr; // 내용의 이미지
    private String nickname; //닉네임
    private String date; //글을 쓴 날짜
    private String userId; // 글쓴 아이디

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        titleStr = title ;
    }

    public String getTitle() {
        return this.titleStr ;
    }

    public void setContent(String content){
        contentStr = content;
    }

    public String getContent(){
        return this.contentStr;
    }

    public void setImage(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getImage() {
        return this.imageStr;
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

    public CommunityData(String titleStr, String contentStr,String imageStr, String nickname, String date, String userId){
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.imageStr = imageStr;
        this.nickname = nickname;
        this.date = date;
        this.userId = userId;
    }
}
