package com.example.seoulwalk.data;

public class Store_Data {


    String store_title;
    String img;
    String num;
    String point;

    public String getStore_title() {
        return store_title;
    }

    public void setStore_title(String store_title) {
        this.store_title = store_title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Store_Data(String store_title, String img, String num, String point){
        this.store_title = store_title;
        this.point = point;
        this.img = img;
        this.num = num;
    }
}
