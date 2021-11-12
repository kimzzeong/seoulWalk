package com.example.seoulwalk.data;

public class History_Data {

    String history_name;
    String history_datetime;
    String history_course_name;
    String history_time;
    String history_distance;
    String history_calorie;
    String history_speed;

    public History_Data(String history_name, String history_datetime, String history_course_name, String history_time, String history_distance, String history_calorie, String history_speed) {
        this.history_name = history_name;
        this.history_datetime = history_datetime;
        this.history_course_name = history_course_name;
        this.history_time = history_time;
        this.history_distance = history_distance;
        this.history_calorie = history_calorie;
        this.history_speed = history_speed;
    }

    public String getHistory_name() {
        return history_name;
    }

    public void setHistory_name(String history_name) {
        this.history_name = history_name;
    }

    public String getHistory_datetime() {
        return history_datetime;
    }

    public void setHistory_datetime(String history_datetime) {
        this.history_datetime = history_datetime;
    }

    public String getHistory_course_name() {
        return history_course_name;
    }

    public void setHistory_course_name(String history_course_name) {
        this.history_course_name = history_course_name;
    }

    public String getHistory_time() {
        return history_time;
    }

    public void setHistory_time(String history_time) {
        this.history_time = history_time;
    }

    public String getHistory_distance() {
        return history_distance;
    }

    public void setHistory_distance(String history_distance) {
        this.history_distance = history_distance;
    }

    public String getHistory_calorie() {
        return history_calorie;
    }

    public void setHistory_calorie(String history_calorie) {
        this.history_calorie = history_calorie;
    }

    public String getHistory_speed() {
        return history_speed;
    }

    public void setHistory_speed(String history_speed) {
        this.history_speed = history_speed;
    }
}
