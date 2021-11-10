package com.example.seoulwalk.data;

import java.util.ArrayList;

public class StampList_Data {

    String stampTitle;
    ArrayList<Stamp_Data> stampList;

    public String getStampTitle() {
        return stampTitle;
    }

    public void setStampTitle(String stampTitle) {
        this.stampTitle = stampTitle;
    }

    public ArrayList<Stamp_Data> getStampList() {
        return stampList;
    }

    public void setStampList(ArrayList<Stamp_Data> stampList) {
        this.stampList = stampList;
    }


    public StampList_Data(String stampTitle){ //   , ArrayList<Stamp_Data> stampList
        this.stampTitle = stampTitle;
      //  this.stampList = stampList;
    }
}
