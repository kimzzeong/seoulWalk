package com.example.seoulwalk.data;

public class Stamp_Data {
    int stmap_img;
    String stamp_name;


    public Stamp_Data(int stmap_img, String stamp_name){
        this.stmap_img = stmap_img;
        this.stamp_name = stamp_name;

    }

    public int getStmap_img() {
        return stmap_img;
    }

    public void setStmap_img(int stmap_img) {
        this.stmap_img = stmap_img;
    }

    public String getStamp_name() {
        return stamp_name;
    }

    public void setStamp_name(String stamp_name) {
        this.stamp_name = stamp_name;
    }


}
