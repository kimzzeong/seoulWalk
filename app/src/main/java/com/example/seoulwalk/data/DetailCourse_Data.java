package com.example.seoulwalk.data;

public class DetailCourse_Data {

    //난이도, 나와의 거리
    String course_level;
    double distance_to_me;


    //소요시간 , 걸음 수 , 이미지 경로
    int course_time;
    int course_stepcount;
    String course_image;


    //세부코스 이름, 전체코스 이름 ,코스 길이, 코스 난이도
    String course_name;
    String course_fullName;
    double course_length;
    String course_difficulty;

    public String getCourse_level() {
        return course_level;
    }

    public void setCourse_level(String course_level) {
        this.course_level = course_level;
    }

    public double getDistance_to_me() {
        return distance_to_me;
    }

    public void setDistance_to_me(double distance_to_me) {
        this.distance_to_me = distance_to_me;
    }

    public int getCourse_time() {
        return course_time;
    }

    public void setCourse_time(int course_time) {
        this.course_time = course_time;
    }

    public int getCourse_stepcount() {
        return course_stepcount;
    }

    public void setCourse_stepcount(int course_stepcount) {
        this.course_stepcount = course_stepcount;
    }

    public String getCourse_image() {
        return course_image;
    }

    public void setCourse_image(String course_image) {
        this.course_image = course_image;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_fullName() {
        return course_fullName;
    }

    public void setCourse_fullName(String course_fullName) {
        this.course_fullName = course_fullName;
    }

    public double getCourse_length() {
        return course_length;
    }

    public void setCourse_length(double course_length) {
        this.course_length = course_length;
    }

    public String getCourse_difficulty() {
        return course_difficulty;
    }

    public void setCourse_difficulty(String course_difficulty) {
        this.course_difficulty = course_difficulty;
    }
}
