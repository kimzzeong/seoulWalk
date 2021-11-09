package com.example.seoulwalk.data;

public class History_Data {

    String history_name;
    String history_time;
    String history_review;

    public History_Data(String history_name, String history_time, String history_review){
        this.history_name = history_name;
        this.history_time = history_time;
        this.history_review = history_review;
    }

    public String getHistory_name() {
        return history_name;
    }

    public void setHistory_name(String history_name) {
        this.history_name = history_name;
    }

    public String getHistory_time() {
        return history_time;
    }

    public void setHistory_time(String history_time) {
        this.history_time = history_time;
    }

    public String getHistory_review() {
        return history_review;
    }

    public void setHistory_review(String history_review) {
        this.history_review = history_review;
    }

}
