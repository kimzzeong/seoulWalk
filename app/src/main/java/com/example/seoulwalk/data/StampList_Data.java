package com.example.seoulwalk.data;

import java.util.ArrayList;

public class StampList_Data {

    String stampTitle;
    String course_title;

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }
;
    public String getStampTitle() {
        return stampTitle;
    }

    public void setStampTitle(String stampTitle) {
        this.stampTitle = stampTitle;
    }


    public StampList_Data(String stampTitle, String course_title){
        this.stampTitle = stampTitle;
        this.course_title = course_title;
    }
}
