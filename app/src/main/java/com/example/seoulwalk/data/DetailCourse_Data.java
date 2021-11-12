package com.example.seoulwalk.data;

public class DetailCourse_Data {


   // 난이도 나와의거리 코스길이 코스소요시간 걸음수 이미지경로 코스이름 원래코스이름


    //난이도, 나와의 거리
    String course_level;
    String distance_to_me;


    //소요시간 , 걸음 수 , 이미지 경로
    String course_time;
    int course_stepcount;
    String course_image;


    //세부코스 이름, 전체코스 이름 ,코스 길이
    String course_name;
    String course_fullName;
    String course_length;


    public String getCourse_level() {
        return course_level;
    }

    public void setCourse_level(String course_level) {
        this.course_level = course_level;
    }

    public String getDistance_to_me() {
        return distance_to_me;
    }

    public void setDistance_to_me(String distance_to_me) {
        this.distance_to_me = distance_to_me;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
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

    public String getCourse_length() {
        return course_length;
    }

    public void setCourse_length(String course_length) {
        this.course_length = course_length;
    }
}
