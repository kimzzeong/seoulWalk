package com.example.seoulwalk.data;

public class Login {

    String user_idx;
    String nickname;

    public String getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(String user_idx) {
        this.user_idx = user_idx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Login(String user_idx, String nickname){
        this.user_idx = user_idx;
        this.nickname = nickname;
    }
}
