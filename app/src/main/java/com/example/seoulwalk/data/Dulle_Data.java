package com.example.seoulwalk.data;

public class Dulle_Data {
    String dulle_name_start;
    String dulle_time;
    String dulle_name_end;
    String Latlng;
    String Latlng_end;
    String img_item;

    public Dulle_Data(String dulle_name_start, String dulle_time, String dulle_name_end, String img_item,String LatLng,String LatLng_end) {
        this.dulle_name_start = dulle_name_start;
        this.dulle_time = dulle_time;
        this.dulle_name_end = dulle_name_end;
        this.img_item = img_item;
        this.Latlng = LatLng;
        this.Latlng_end = LatLng_end;
    }

    public String getImg_item() {
        return img_item;
    }

    public void setImg_item(String img_item) {
        this.img_item = img_item;
    }

    public String getLatlng() {
        return Latlng;
    }

    public void setLatlng(String latlng) {
        Latlng = latlng;
    }

    public String getLatlng_end() {
        return Latlng_end;
    }

    public void setLatlng_end(String latlng_end) {
        Latlng_end = latlng_end;
    }



//    public Dulle_Data(String dulle_name_start, String dulle_time, String dulle_name_end) {
//        this.dulle_name_start = dulle_name_start;
//        this.dulle_time = dulle_time;
//        this.dulle_name_end = dulle_name_end;
//    }

    public String getDulle_name_start() {
        return dulle_name_start;
    }

    public void setDulle_name_start(String dulle_name_start) {
        this.dulle_name_start = dulle_name_start;
    }

    public String getDulle_time() {
        return dulle_time;
    }

    public void setDulle_time(String dulle_time) {
        this.dulle_time = dulle_time;
    }

    public String getDulle_name_end() {
        return dulle_name_end;
    }

    public void setDulle_name_end(String dulle_name_end) {
        this.dulle_name_end = dulle_name_end;
    }
}
