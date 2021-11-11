package com.example.seoulwalk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StepCount {
    @Expose
    @SerializedName("idx") private int idx;

    @Expose
    @SerializedName("userid") private String userid;

    @Expose
    @SerializedName("steps") private int steps;

    @Expose
    @SerializedName("date") private String date;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
