package com.example.seoulwalk.data;

public class DetailCourse_Data {

    // 나와의 거리
    double distance_to_me;

    //소요시간 , 걸음 수 , 이미지 경로
    int course_time;
    int course_stepcount;
    String course_image;

    // 시작점, 끝점, 시작좌표, 끝좌표
    String start;
    String end;
    String LatLng;
    String LatLng_End;

    //세부코스 이름, 전체코스 이름 , 전체코스 몇코스인지 숫자, 코스 길이, 코스 난이도
    String course_name;
    String course_fullName;
    int original_course_number;
    double course_length;
    String course_difficulty;

    //걸은 상태인지 아닌지 확인
    int status;

    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLatLng() {
        return LatLng;
    }

    public void setLatLng(String latLng) {
        LatLng = latLng;
    }

    public String getLatLng_End() {
        return LatLng_End;
    }

    public void setLatLng_End(String latLng_End) {
        LatLng_End = latLng_End;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getOriginal_course_number() {
        return original_course_number;
    }

    public void setOriginal_course_number(int original_course_number) {
        this.original_course_number = original_course_number;
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
