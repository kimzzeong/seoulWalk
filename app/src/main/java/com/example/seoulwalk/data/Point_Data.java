package com.example.seoulwalk.data;

public class Point_Data {


    String point_title;
    String point;
    String date;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoint_title() {
        return point_title;
    }

    public void setPoint_title(String point_title) {
        this.point_title = point_title;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Point_Data(String point_title, String point, String date){
        this.point_title = point_title;
        this.point = point;
        this.date = date;
    }
}
