package com.example.seoulwalk.data;

public class Stamp_Data {
    int stmap_img;
    String stamp_name;


    boolean stamp_it;

    public Stamp_Data(int stmap_img, String stamp_name, boolean stamp_it){
        this.stmap_img = stmap_img;
        this.stamp_name = stamp_name;
        this.stamp_it = stamp_it;

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

    public boolean isStamp_it() {
        return stamp_it;
    }

    public void setStamp_it(boolean stamp_it) {
        this.stamp_it = stamp_it;
    }


}
